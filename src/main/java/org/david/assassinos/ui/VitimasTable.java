package org.david.assassinos.ui;

import org.david.assassinos.App;
import org.david.assassinos.db.Assassino;
import org.david.assassinos.db.Vitima;

import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VitimasTable extends EntityTable<Vitima> {
    class VitimasTableModel extends DefaultTableModel {
    private final String[] columnNames = {"ID", "ID Assassino", "Nome", "Sobrenome", "Data da Morte", "Arma Utilizada", "Local da Morte"};

    @Override
    public boolean isCellEditable(int row, int column) {
        if(column == 0) return false;

        return true;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}

    @Override
    public Vitima createEntity() {
        return new Vitima();
    }

    public VitimasTable(Frame owner) {
        super(owner, App.db.vitimas);
        setTableModel(new VitimasTableModel());
    }
}
