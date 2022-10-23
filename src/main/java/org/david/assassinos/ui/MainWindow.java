package org.david.assassinos.ui;
import org.david.assassinos.App;
import org.david.assassinos.db.Assassino;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class MainWindow extends JFrame {
    protected static final long serialVersionUID = 1L;

    private AssassinosTable assassinosTable;
    private VitimasTable vitimasTable;
    private JTabbedPane tabelasPane;

    public MainWindow() {
        super("Assassinos e Vítimas");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1200,800));
        setResizable(false);
        getContentPane().setBackground(Color.GRAY);

        tabelasPane = new JTabbedPane();
        add(tabelasPane);
        tabelasPane.setBounds(30,30,1120,700);

        assassinosTable = new AssassinosTable(this);
        assassinosTable.refresh();

        vitimasTable = new VitimasTable(this);
        vitimasTable.refresh();

        tabelasPane.addTab("Assassinos", assassinosTable);
        tabelasPane.addTab("Vítimas", vitimasTable);

        this.setVisible(true);
    }
}
