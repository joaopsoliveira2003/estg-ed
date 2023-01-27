package GameManagement.PlayersManagement;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlayersManagementMenu extends JFrame {
    public PlayersManagementMenu(Game game) {
        super("Players Management");

        JButton loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(ignored -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to load data from");
            fileChooser.setCurrentDirectory(new java.io.File("."));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    game.loadPlayers(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data loaded successfully");
                } catch (IOException exception) {
                    new JOptionPane(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ManagePlayers managePlayers = new ManagePlayers(game);
        JButton manageDataButton = new JButton("Manage Data");
        manageDataButton.addActionListener(e -> {
            if (!managePlayers.isVisible()) {
                managePlayers.setVisible(true);
            } else {
                managePlayers.toFront();
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
                    game.exportPlayers(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data saved successfully");
                } catch (IOException exception) {
                    new JOptionPane(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLayout(new GridLayout(3, 3));

        add(loadDataButton);
        add(manageDataButton);
        add(saveDataButton);

        setSize(450, 450);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);
    }
}

