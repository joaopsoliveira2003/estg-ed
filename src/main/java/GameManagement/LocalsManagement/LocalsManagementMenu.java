package GameManagement.LocalsManagement;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * LocalsManagementMenu is a JFrame that allows the user to manage the locals of the game.
 */
public class LocalsManagementMenu extends JFrame {
    public LocalsManagementMenu(Game game) {
        super("Locals Management");

        JButton loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(ignored -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to load data from");
            fileChooser.setCurrentDirectory(new java.io.File("."));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    game.loadLocals(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data loaded successfully");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ManageLocals manageLocals = new ManageLocals(game);
        JButton manageDataButton = new JButton("Manage Data");
        manageDataButton.addActionListener(e -> {
            if (!manageLocals.isVisible()) {
                manageLocals.setLocationRelativeTo(this);
                manageLocals.setVisible(true);
            } else {
                manageLocals.toFront();
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
                    game.exportLocals(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data saved successfully");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLayout(new GridLayout(3, 2));

        add(loadDataButton);
        add(manageDataButton);
        add(saveDataButton);

        setSize(450, 450);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);
    }
}
