package GameManagement.GameManagement;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
                } catch (IOException exception) {
                    new JOptionPane(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
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
                    game.saveGameData(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data saved successfully");
                } catch (IOException exception) {
                    new JOptionPane(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLayout(new GridLayout(0, 1));

        add(loadDataButton);
        add(saveDataButton);

        setSize(300, 300);

        setLocationRelativeTo(null);
    }
}
