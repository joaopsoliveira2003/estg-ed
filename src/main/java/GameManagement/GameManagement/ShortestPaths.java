package GameManagement.GameManagement;

import javax.swing.*;

import Game.API.Game;
import Game.Entities.Local;

import java.awt.*;
import java.util.Iterator;

/**
 * ShortestPaths is a window that allows the user to calculate and export the shortest path between two locals.
 */
public class ShortestPaths extends JFrame {
    public ShortestPaths(Game game){
        super("Shortest Paths");

        JPanel panelId1 = new JPanel();
        panelId1.setLayout(new GridLayout(1, 0));

        JLabel labelId1 = new JLabel("INICIAL POINT: ");
        JTextField textFieldId1 = new JTextField(15);

        JPanel panelId2 = new JPanel();
        panelId2.setLayout(new GridLayout(1, 0));

        JLabel labelId2 = new JLabel("FINAL POINT: ");
        JTextField textFieldId2 = new JTextField(15);

        panelId1.add(labelId1);
        panelId1.add(textFieldId1);

        panelId2.add(labelId2);
        panelId2.add(textFieldId2);

        add(panelId1);
        add(panelId2);

        JRadioButton portals = new JRadioButton("Portals");
        JRadioButton connectors = new JRadioButton("Connectors");
        JRadioButton both = new JRadioButton("Both");
        ButtonGroup group = new ButtonGroup();
        group.add(portals);
        group.add(connectors);
        group.add(both);
        add(portals);
        add(connectors);
        add(both);

        JCheckBox export = new JCheckBox("Export");
        add(export);

        JButton button = new JButton("CALCULATE");
        button.addActionListener((ignored) -> {
            try {
                int id1 = Integer.parseInt(textFieldId1.getText());
                int id2 = Integer.parseInt(textFieldId2.getText());
                Iterator<Local> path;
                if (portals.isSelected()) {
                    path = game.getShortestPath(id1, id2, true);
                } else if (connectors.isSelected()) {
                    path = game.getShortestPath(id1, id2, false);
                } else if (both.isSelected()) {
                    path = game.getShortestPath(id1, id2);
                } else {
                    throw new Exception("You must choose a path type!");
                }
                if (export.isSelected()) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Specify where to save the file");
                    fileChooser.setCurrentDirectory(new java.io.File("."));
                    int userSelection = fileChooser.showSaveDialog(this);
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        game.exportShortestPath(path, fileChooser.getSelectedFile().getAbsolutePath());
                        JOptionPane.showMessageDialog(this, "File saved successfully!");
                    }
                } else {
                    String result = "Shortest path:";
                    while (path.hasNext()) {
                        result += "\n" + path.next().getName();
                    }
                    JOptionPane.showMessageDialog(this, result);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });

        add(button);

        setLayout(new GridLayout(0, 1));

        setSize(400, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        pack();
    }
}
