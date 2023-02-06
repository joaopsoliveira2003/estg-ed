package GameManagement.GameManagement;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * GameManagementMenu is a JFrame for managing the game.
 */
public class GameManagementMenu extends JFrame {
    public GameManagementMenu(Game game) {
        super("Game Management");

        JButton loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(ignored -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to load data from");
            fileChooser.setCurrentDirectory(new java.io.File("."));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    game.loadGameData(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data loaded successfully");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ShortestPaths shortestPaths = new ShortestPaths(game);
        JButton shortestPathButton = new JButton("Shortest Path");
        shortestPathButton.addActionListener(ignored -> {
            if (!shortestPaths.isVisible()) {
                shortestPaths.setVisible(true);
            } else {
                shortestPaths.toFront();
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
                    game.exportGameData(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data saved successfully");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLayout(new GridLayout(3, 1));

        add(loadDataButton);
        add(shortestPathButton);
        add(saveDataButton);

        setSize(400, 400);

        setLocationRelativeTo(null);
    }
}
