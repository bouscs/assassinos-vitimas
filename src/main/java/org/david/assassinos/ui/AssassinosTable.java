package org.david.assassinos.ui;

import com.mongodb.client.model.Filters;
import org.david.assassinos.App;
import org.david.assassinos.db.Assassino;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AssassinosTable extends EntityTable<Assassino> {

    class AssassinosTableFilterBy extends EntityTableFilter<Assassino> {
        public AssassinosTableFilterBy(String property, String value) {
            super(() -> App.db.assassinos.find(Filters.eq(property, value)).into(new ArrayList<>()));
        }
    }

    class AssassinosTableModel extends DefaultTableModel {
        private final String[] columnNames = {"ID", "Nome", "Sobrenome", "Data de Nascimento", "Arma Favorita", "Cidade Atual"};

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
    public Assassino createEntity() {
        return new Assassino();
    }

    public AssassinosTable(Frame owner) {
        super(owner, App.db.assassinos);
        setTableModel(new AssassinosTableModel());
    }
}
