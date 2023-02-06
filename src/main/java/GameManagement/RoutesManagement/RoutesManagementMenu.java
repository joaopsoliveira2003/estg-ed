package GameManagement.RoutesManagement;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * RoutesManagementMenu is a JFrame that allows the user to manage the routes of the game.
 */
public class RoutesManagementMenu extends JFrame {
    public RoutesManagementMenu(Game game) {
        super("Routes Management");

        AddRoutes addRoutes = new AddRoutes(game);
        JButton addRoutesButton = new JButton("Add Routes");
        addRoutesButton.addActionListener(e -> {
            if (!addRoutes.isVisible()) {
                addRoutes.setVisible(true);
            } else {
                addRoutes.toFront();
            }
        });

        RemoveRoutes removeRoutes = new RemoveRoutes(game);
        JButton removeRoutesButton = new JButton("Remove Routes");
        removeRoutesButton.addActionListener(e -> {
            if (!removeRoutes.isVisible()) {
                removeRoutes.setVisible(true);
            } else {
                removeRoutes.toFront();
            }
        });

        JButton loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(ignored -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to load data from");
            fileChooser.setCurrentDirectory(new java.io.File("."));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    game.loadRoutesLocals(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data loaded successfully");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        JButton saveDataButton = new JButton("Save Data");
        saveDataButton.addActionListener(ignored -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to save data to");
            fileChooser.setCurrentDirectory(new java.io.File("."));
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    game.exportRoutesLocals(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data saved successfully");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLayout(new GridLayout(2, 2));

        add(addRoutesButton);
        add(removeRoutesButton);
        add(loadDataButton);
        add(saveDataButton);

        setSize(300, 300);

        setLocationRelativeTo(null);
    }
}

