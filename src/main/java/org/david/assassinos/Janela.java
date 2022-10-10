package org.david.assassinos;

import javax.swing.*;

public class Janela extends JFrame {
    protected static final long serialVersionUID = 1L;

    protected JButton btnPonto = new JButton("Ponto");

    public Janela() {
        super("Assassinos e Vítimas");

        this.setSize(800, 600);
        this.setVisible(true);
    }
}
