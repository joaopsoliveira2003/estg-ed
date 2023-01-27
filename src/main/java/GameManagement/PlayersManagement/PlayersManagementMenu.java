package GameManagement.PlayersManagement;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlayersManagementMenu extends JFrame {
    public PlayersManagementMenu(Game game) {
        super("Players Management");

        AddPlayer addPlayer = new AddPlayer(game);
        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(e -> {
            if (!addPlayer.isVisible()) {
                addPlayer.setVisible(true);
            } else {
                addPlayer.toFront();
            }
        });

        EditPlayerList editPlayerList = new EditPlayerList(game);
        JButton editPlayerButton = new JButton("Edit Player");
        editPlayerButton.addActionListener(e -> {
            if (!editPlayerList.isVisible()) {
                editPlayerList.setVisible(true);
            } else {
                editPlayerList.toFront();
            }
        });

        RemovePlayerList removePlayerList = new RemovePlayerList(game);
        JButton removePlayerButton = new JButton("Remove Player");
        removePlayerButton.addActionListener(e -> {
            if (!removePlayerList.isVisible()) {
                removePlayerList.setVisible(true);
            } else {
                removePlayerList.toFront();
            }
        });

        AssPlayTeam assPlayTeam = new AssPlayTeam(game);
        JButton assPlayTeamButton = new JButton("Associate Player - Team");
        assPlayTeamButton.addActionListener(e -> {
            if (!assPlayTeam.isVisible()) {
                assPlayTeam.setVisible(true);
            } else {
                assPlayTeam.toFront();
            }
        });

        DessPlayTeam dessPlayTeam = new DessPlayTeam(game);
        JButton dessPlayTeamButton = new JButton("Desassociate Player - Team");
        assPlayTeamButton.addActionListener(e -> {
            if (!dessPlayTeam.isVisible()) {
                dessPlayTeam.setVisible(true);
            } else {
                dessPlayTeam.toFront();
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
                    game.loadPlayers(fileChooser.getSelectedFile().getAbsolutePath());
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
                    game.exportPlayers(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data saved successfully");
                } catch (IOException exception) {
                    new JOptionPane(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLayout(new GridLayout(3, 3));

        add(addPlayerButton);
        add(editPlayerButton);
        add(removePlayerButton);
        add(assPlayTeamButton);
        add(dessPlayTeamButton);
        add(loadDataButton);
        add(saveDataButton);

        setSize(450, 450);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);
    }
}

