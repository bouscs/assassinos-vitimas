package org.david.assassinos.ui;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.david.assassinos.db.Entity;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public abstract class EntityTable<T extends Entity> extends JPanel implements ActionListener, TableModelListener {
    protected JTable table;
    protected DefaultTableModel tableModel;
    protected JScrollPane scrollPane;

    protected JToolBar toolBar;

    protected MongoCollection<T> collection;

    public static final String CREATE = "CREATE", REFRESH = "REFRESH", SAVE = "SAVE", DUPLICATE = "DUPLICATE", COPY = "COPY", FIND = "FIND", DELETE = "DELETE";

    protected ToolbarButton findButton, refreshButton, addButton, duplicateButton, saveButton, deleteButton;

    protected EntityTableFilter<T> filter;

    public EntityTable(MongoCollection<T> collection) {
        this.collection = collection;
        filter = new EntityTableFilter<T>(collection);

        setLayout(new BorderLayout());

        toolBar = new JToolBar();

        toolBar.setFloatable(false);

        findButton = new ToolbarButton("images/search_icon.png", FIND, "Busca", "Busca");
        findButton.addActionListener(this);
        toolBar.add(findButton);

        refreshButton = new ToolbarButton("images/refresh_icon.png", REFRESH, "Recarregar dados do banco", "Recarregar");
        refreshButton.addActionListener(this);
        toolBar.add(refreshButton);

        addButton = new ToolbarButton("images/add_icon.png", CREATE, "Adicionar nova fileira", "Criar");
        addButton.addActionListener(this);
        toolBar.add(addButton);

        saveButton = new ToolbarButton("images/save_icon.png", SAVE, "Salvar mudan??as", "Salvar");
        saveButton.addActionListener(this);
        saveButton.setEnabled(false);
        toolBar.add(saveButton);

        duplicateButton = new ToolbarButton("images/duplicate_icon.png", DUPLICATE, "Duplicar fileira", "Duplicar");
        duplicateButton.addActionListener(this);
        toolBar.add(duplicateButton);

        deleteButton = new ToolbarButton("images/delete_icon.png", DELETE, "Excluir fileira", "Deletar");
        deleteButton.addActionListener(this);
        toolBar.add(deleteButton);

        add(toolBar, BorderLayout.PAGE_START);

        table = new JTable();

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(() -> {
                    int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), table));
                    int columnAtPoint = table.columnAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), table));
                    if (rowAtPoint > -1 && columnAtPoint > -1) {
                        table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        table.setColumnSelectionInterval(columnAtPoint, columnAtPoint);
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
        JMenuItem deleteItem = new JMenuItem("Copiar");
        deleteItem.addActionListener(e -> copyLeadSelection());
        popupMenu.add(deleteItem);
        table.setComponentPopupMenu(popupMenu);

        add(scrollPane, BorderLayout.CENTER);
    }

    public abstract T createEntity();

    public void setFilter(EntityTableFilter<T> filter) {
        this.filter = filter;
    }

    public void refresh() {
        table.setEnabled(false);
        List<T> entities = filter.find();

        setEntities(entities);
        saveButton.setEnabled(false);
        table.setEnabled(true);
    }

    public void setTableModel(DefaultTableModel tableModel) {
        table.setModel(tableModel);
        this.tableModel = tableModel;
        tableModel.addTableModelListener(this);
    }

    public List<T> getEntities() {
        List<T> entities = new ArrayList<>();
        Object[][] rows = getAllRows();

        for(Object[] row : rows) {
            T e = createEntity();
            try {
                e.fromRow(row);
            } catch (Exception error) {
                System.out.println(error);
            }

            entities.add(e);
        }

        return entities;
    }

    public void setEntities(List<T> entities) {
        tableModel.setRowCount(entities.size());

        for (int i = 0; i < entities.size(); i++) {
            Object[] row = entities.get(i).toRow();

            for (int j = 0; j < row.length; j++) {
                table.setValueAt(row[j], i, j);
            }
        }
    }

    public T getSelectedEntity() {
        int index = table.getSelectionModel().getLeadSelectionIndex();

        Object[] row = getRow(index);

        T entity = createEntity();

        try {
            entity.fromRow(row);
        } catch(Exception e) {
            System.out.println(e);
        }

        return entity;
    }

    public Object[] getRow(int index) {
        Object[] row = new Object[table.getColumnCount()];

        for (int i = 0; i < table.getColumnCount(); i++) {
            row[i] = table.getValueAt(index, i);
        }

        return row;
    }

    public Object[][] getAllRows() {
        Object[][] rows = new Object[table.getRowCount()][table.getColumnCount()];

        for (int rowIndex = 0; rowIndex < table.getRowCount(); rowIndex++) {
            rows[rowIndex] = getRow(rowIndex);
        }

        return rows;
    }

    public Object getLeadSelection() {
        int row = table.getSelectionModel().getLeadSelectionIndex();
        int col = table.getColumnModel().getSelectionModel().getLeadSelectionIndex();

        return table.getValueAt(row, col);
    }

    public List<T> getSelectedEntities() {
        int[] selectedRows = table.getSelectedRows();

        List<T> entities = new ArrayList<>();

        for (int rowIndex : selectedRows) {
            Object[] row = getRow(rowIndex);

            T entity = createEntity();
            try {
                entity.fromRow(row);
            } catch (Exception error) {
                System.out.println(error);
            }

            entities.add(entity);
        }

        return entities;
    }

    public void addNewRow(T entity) {
        table.setEnabled(false);
        tableModel.addRow(entity.toRow());
        try {
            entity.save();
        } catch(Exception error) {
            System.out.println(error);
        }
        saveButton.setEnabled(false);
        table.setEnabled(true);
    }

    public void saveChanges() {
        table.setEnabled(false);
        List<T> entities = getEntities();
        for(T entity : entities) {
            try {
                entity.save();
            } catch(Exception e) {
                JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(this), "Erro ao salvar", true);
                dialog.setVisible(true);
            }
        }
        saveButton.setEnabled(false);
        table.setEnabled(true);
    }

    public void copyLeadSelection() {
        String leadSelection = (String) getLeadSelection();

        StringSelection stringSelection = new StringSelection(leadSelection);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public void deleteSelection() {
        table.setEnabled(false);

        List<T> selected = getSelectedEntities();

        for(T e : selected) {
            try {
                e.delete();
            } catch(Exception error) {
                System.out.println(error);
            }
        }

        refresh();

        table.setEnabled(true);
    }

    public void duplicateSelectedRow() {
        T selected = getSelectedEntity();
        T duplicate = (T)selected.clone();

        addNewRow(duplicate);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch(command) {
            case FIND:
                setFilter(FilterDialog.showDialog(this, collection));
                refresh();
                break;
            case REFRESH:
                refresh();
                break;
            case CREATE:
                addNewRow(createEntity());
                break;
            case DUPLICATE:
                duplicateSelectedRow();
                break;
            case SAVE:
                saveChanges();
                break;
            case DELETE:
                deleteSelection();
                break;
            default:
                break;
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        saveButton.setEnabled(true);
    }
}
