package org.david.assassinos;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.*;
public class Janela extends JFrame {
    protected static final long serialVersionUID = 1L;

    protected JButton btnBusca = new JButton();
    protected JButton btnCadastrar = new JButton();
    protected JButton btnAssassinos = new JButton("Assassinos");
    protected JButton btnVitimas = new JButton("Vítimas");
    protected JPanel campoTabelas = new JPanel();
    public Janela() {
        super("Assassinos e Vítimas");
        setLayout(null);
        //getContentPane().setSize(1000,1000);
        setResizable(false);
        getContentPane().setBackground(Color.DARK_GRAY);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(e.getSource() == btnCadastrar)
            }
        });
        btnBusca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(e.getSource() == btnBusca)
            }
        });
        add(btnBusca);
        btnBusca.setBounds(30,30,80,80);
        btnBusca.setBorderPainted(false);
        btnBusca.setContentAreaFilled(true);
        btnBusca.setBackground(Color.lightGray);
        btnBusca.setOpaque(true);

        add(btnCadastrar);
        btnCadastrar.setBorderPainted(false);
        btnCadastrar.setContentAreaFilled(true);
        btnCadastrar.setBackground(Color.lightGray);
        btnCadastrar.setOpaque(true);
        btnCadastrar.setBounds(130,30,80,80);

        add(btnAssassinos);
        btnAssassinos.setBorderPainted(false);
        btnAssassinos.setContentAreaFilled(true);
        btnAssassinos.setBackground(Color.lightGray);
        btnAssassinos.setOpaque(true);
        btnAssassinos.setBounds(550,80,100,50);

        add(btnVitimas);
        btnVitimas.setBorderPainted(false);
        btnVitimas.setContentAreaFilled(true);
        btnVitimas.setBackground(Color.lightGray);
        btnVitimas.setOpaque(true);
        btnVitimas.setBounds(660,80,100,50);

        add(campoTabelas);
        campoTabelas.setBounds(30,130,730,420);
        campoTabelas.setBackground(Color.lightGray);

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
