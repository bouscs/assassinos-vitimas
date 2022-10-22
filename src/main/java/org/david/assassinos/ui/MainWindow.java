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

    protected JButton btnBusca = new JButton();

    protected JLabel labelAssassinos = new JLabel("Assassinos", null, SwingConstants.CENTER);
    protected JLabel labelVitimas = new JLabel("Vítimas", null, SwingConstants.CENTER);
    protected JButton btnCadastrar = new JButton();
    protected JButton btnAssassinos = new JButton("Assassinos");
    protected JButton btnVitimas = new JButton("Vítimas");

    private AssassinosTable assassinosTable;
    private VitimasTable vitimasTable;

    private JTabbedPane tabelasPane;

    public MainWindow() {
        super("Assassinos e Vítimas");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setPreferredSize(new Dimension(1000,1000));
        setMinimumSize(new Dimension(1200,800));
        setResizable(false);
        getContentPane().setBackground(Color.GRAY);


        tabelasPane = new JTabbedPane();
        add(tabelasPane);
        tabelasPane.setBounds(30,30,1120,700);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(e.getSource() == btnCadastrar)
            }
        });
        btnBusca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //add(btnBusca);
        btnBusca.setBounds(30,30,80,80);
        btnBusca.setBorderPainted(false);
        btnBusca.setContentAreaFilled(true);
        btnBusca.setBackground(Color.lightGray);
        btnBusca.setOpaque(true);

        //add(btnCadastrar);
        btnCadastrar.setBorderPainted(false);
        btnCadastrar.setContentAreaFilled(true);
        btnCadastrar.setBackground(Color.lightGray);
        btnCadastrar.setOpaque(true);
        btnCadastrar.setBounds(130,30,80,80);

//        add(btnAssassinos);
//        btnAssassinos.setBorderPainted(false);
//        btnAssassinos.setContentAreaFilled(true);
//        btnAssassinos.setBackground(Color.lightGray);
//        btnAssassinos.setOpaque(true);
//        btnAssassinos.setBounds(940,80,100,50);
//
//        add(btnVitimas);
//        btnVitimas.setBorderPainted(false);
//        btnVitimas.setContentAreaFilled(true);
//        btnVitimas.setBackground(Color.lightGray);
//        btnVitimas.setOpaque(true);
//        btnVitimas.setBounds(1050,80,100,50);

        //add(campoTabela1);
        //campoTabela1.setBounds(30,180,550,550);
        //campoTabela1.setBackground(Color.lightGray);

        //add(campoTabela2);
        //campoTabela2.setBounds(600,180,550,550);
        //campoTabela2.setBackground(Color.lightGray);

        assassinosTable = new AssassinosTable(this);
        assassinosTable.find();

        vitimasTable = new VitimasTable(this);
        vitimasTable.find();

        tabelasPane.addTab("Assassinos", assassinosTable);
        tabelasPane.addTab("Vítimas", vitimasTable);

        labelAssassinos.setBounds(30, 130, 550, 50);
        labelAssassinos.setOpaque(true);
        labelAssassinos.setBackground(Color.lightGray);
        labelAssassinos.setFont(new Font("Arial", Font.PLAIN, 25));
        //add(labelAssassinos);

        labelVitimas.setBounds(600, 130, 550, 50);
        labelVitimas.setFont(new Font("Arial", Font.PLAIN, 25));
        labelVitimas.setOpaque(true);
        labelVitimas.setBackground(Color.lightGray);
        //add(labelVitimas);

        try {
            ImageIcon searchIcon = new ImageIcon("src/resources/search_icon.png",
                    "Click to search");
            btnBusca.setIcon(searchIcon);
            ImageIcon addIcon = new ImageIcon("src/resources/add_icon.png",
                    "Click to add");
            btnCadastrar.setIcon(addIcon);
        } catch (Exception e) {
            System.out.println(e);
        }

        this.setSize(800, 600);
        this.setVisible(true);
    }
}
