package org.david.assassinos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.*;
public class Janela extends JFrame {
    protected static final long serialVersionUID = 1L;

    protected JButton btnLimpar = new JButton("Limpar");
    protected JButton btnOk = new JButton("Ok");

    private JTextField fixa, caixa;
    public Janela() {
        super("Assassinos e Vítimas");
        setLayout( new FlowLayout() );

        fixa = new JTextField("Nome do Assassino:");
        fixa.setEditable(false);
        add(fixa);

        caixa=new JTextField(20);
        add(caixa);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnOk)
                    JOptionPane.showMessageDialog(null, caixa.getText());
            }
        });
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnLimpar)
                    caixa.setText("");
            }
        });
        add(btnLimpar);
        add(btnOk);

        this.setSize(800, 600);
        this.setVisible(true);
    }
}
