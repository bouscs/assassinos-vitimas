package org.david.assassinos.ui;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.david.assassinos.db.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FilterDialog<T extends Entity> extends JDialog implements ActionListener {
    public static FilterDialog dialog;
    public static EntityTableFilter value;

    private static MongoCollection collection;

    private JTextField propertyField;
    private JComboBox compareModeField;
    private JTextField valueField;

    public static <T extends Entity> EntityTableFilter<T> showDialog(EntityTable<T> comp, MongoCollection<T> collection) {
        FilterDialog.collection = collection;

        Frame frame = JOptionPane.getFrameForComponent(comp);
        dialog = new FilterDialog<T>(frame, collection);
        FilterDialog.value = new EntityTableFilter<>(collection);

        dialog.setVisible(true);

        return (EntityTableFilter<T>) value;
    }

    public FilterDialog(Frame frame, MongoCollection<T> collection) {
        super(frame, "Query", true);

        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.GRAY);
        setMinimumSize(new Dimension(500,150));
        setResizable(false);

        propertyField = new JTextField();
        propertyField.setBounds(20, 20, 150, 24);
        compareModeField = new JComboBox(new String[] {"EQUALS"});
        compareModeField.setBounds(190, 20, 100, 24);
        valueField = new JTextField();
        valueField.setBounds(310, 20, 150, 24);
        JButton searchButton = new JButton("Buscar");
        searchButton.setBounds(188, 64, 96, 32);
        searchButton.addActionListener(this);

        add(propertyField);
        add(compareModeField);
        add(valueField);
        add(searchButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String property = propertyField.getText();
        String value = valueField.getText();
        if(property.isEmpty()) {
            FilterDialog.value = new EntityTableFilter<>((MongoCollection<T>) collection);
        } else {
            FilterDialog.value = new EntityTableFilterEquals<>((MongoCollection<T>) collection, property, value);
        }

        FilterDialog.dialog.setVisible(false);
    }
}
