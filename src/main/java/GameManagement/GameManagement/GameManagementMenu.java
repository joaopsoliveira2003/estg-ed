package GameManagement.GameManagement;

import Collections.Lists.OrderedListADT;
import Collections.Lists.UnorderedListADT;
import Game.API.Game;
import Game.Enumerations.SortLocals;

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
                    game.exportGameData(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data saved successfully");
                } catch (IOException exception) {
                    new JOptionPane(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton exportRouteButton = new JButton("Export Route");
        exportRouteButton.addActionListener(ignored -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to save data to");
            fileChooser.setCurrentDirectory(new java.io.File("."));
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    throw new IOException("Not implemented yet");
                    //game.exportShortestPath(game.getShortestPath(), fileChooser.getSelectedFile().getAbsolutePath());
                    //JOptionPane.showMessageDialog(this, "Data saved successfully");
                } catch (IOException exception) {
                    new JOptionPane(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLayout(new GridLayout(1, 2));

        add(loadDataButton);
        add(saveDataButton);

        setSize(300, 300);

        setLocationRelativeTo(null);
    }
}
