package org.david.assassinos.ui;

import org.david.assassinos.App;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class ToolbarButton extends JButton {

    public ToolbarButton(String imagePath,
                         String actionCommand,
                         String toolTipText,
                         String altText) {
        URL imageURL = App.getResource(imagePath);

        setActionCommand(actionCommand);
        setToolTipText(toolTipText);

        if (imageURL != null) {                      //image found
            setIcon(new ImageIcon(imageURL, altText));
        } else {                                     //no image found
            setText(altText);
            System.err.println("Resource not found: " + imagePath);
        }
    }
}
