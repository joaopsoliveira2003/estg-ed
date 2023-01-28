package PlayerSimulator;

import javax.swing.*;

import Game.API.Game;
import Game.Exceptions.NoSuchPlayerException;

/**
 * PlayerDemo is a class that allows the user to simulate the actions of a player
 */
public class PlayerDemo extends JFrame {

    public void updateFields(Game game, JTextField id, JTextField level, JTextField exp, JTextField pos, JTextField energy) throws NoSuchPlayerException {
        level.setText(String.valueOf(game.getPlayerLevel(Integer.parseInt(id.getText()))));
        exp.setText(String.valueOf(game.getPlayerExperiencePoints(Integer.parseInt(id.getText()))));
        try {
            pos.setText(String.valueOf(game.getPlayerPosition(Integer.parseInt(id.getText()))));
        } catch (Exception exception) {
            pos.setText("None");
        }
        energy.setText(String.valueOf(game.getPlayerEnergy(Integer.parseInt(id.getText()))));
    }
    
    public PlayerDemo(Game game) {
        super("Player Simulator");

        JPanel idPanel = new JPanel();
        JLabel idLabel = new JLabel("Player ID: ");
        JTextField idField = new JTextField(10);
        idPanel.add(idLabel);
        idPanel.add(idField);

        JPanel levelPanel = new JPanel();
        JLabel levelLabel = new JLabel("Level: ");
        JTextField levelField = new JTextField(10);
        levelField.setEditable(false);
        levelPanel.add(levelLabel);
        levelPanel.add(levelField);

        JPanel expPanel = new JPanel();
        JLabel expLabel = new JLabel("Experience Points: ");
        JTextField expField = new JTextField(10);
        expField.setEditable(false);
        expPanel.add(expLabel);
        expPanel.add(expField);

        JPanel posPanel = new JPanel();
        JLabel posLabel = new JLabel("Position: ");
        JTextField posField = new JTextField(10);
        posField.setEditable(false);
        posPanel.add(posLabel);
        posPanel.add(posField);

        JPanel energyPanel = new JPanel();
        JLabel energyLabel = new JLabel("Energy: ");
        JTextField energyField = new JTextField(10);
        energyField.setEditable(false);
        energyPanel.add(energyLabel);
        energyPanel.add(energyField);

        JPanel energyToAddPanel = new JPanel();
        JLabel energyToAddLabel = new JLabel("Energy to Add: ");
        JTextField energyToAddField = new JTextField(10);
        energyToAddPanel.add(energyToAddLabel);
        energyToAddPanel.add(energyToAddField);

        JPanel movePanel = new JPanel();
        JLabel moveLabel = new JLabel("Move to: ");
        JTextField moveField = new JTextField(10);
        movePanel.add(moveLabel);
        movePanel.add(moveField);

        JButton showInfoButton = new JButton("Show Info");
        showInfoButton.addActionListener(e -> {
            try {
                updateFields(game, idField, levelField, expField, posField, energyField);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });

        JPanel buttonPanel = new JPanel();
        JButton moveButton = new JButton("Move Player");
        moveButton.addActionListener(e -> {

            try {
                game.movePlayer(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()));
                updateFields(game, idField, levelField, expField, posField, energyField);
                JOptionPane.showMessageDialog(null, "Player moved");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });

        JButton chargeButton = new JButton("Charge Player");
        chargeButton.addActionListener(e -> {

            try {
                game.chargePlayer(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()));
                updateFields(game, idField, levelField, expField, posField, energyField);
                JOptionPane.showMessageDialog(null, "Player charged");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });

        JButton chargePortalButton = new JButton("Charge Portal");
        chargePortalButton.addActionListener(e -> {

            try {
                game.chargePortal(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()), Integer.parseInt(energyToAddField.getText()));
                updateFields(game, idField, levelField, expField, posField, energyField);
                JOptionPane.showMessageDialog(null, "Portal charged");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });

        JButton acquirePortalButton = new JButton("Acquire Portal");
        acquirePortalButton.addActionListener(e -> {

            try {
                game.acquirePortal(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()));
                updateFields(game, idField, levelField, expField, posField, energyField);
                JOptionPane.showMessageDialog(null, "Portal acquired");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });

        JButton shortestPathButton = new JButton("Shortest Path");
        shortestPathButton.addActionListener(e -> {

            try {
                throw new Exception("Not implemented yet");
                //game.getShortestPath(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()));
                //updateFields(game, idField, levelField, expField, posField, energyField);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });

        buttonPanel.add(showInfoButton);
        buttonPanel.add(moveButton);
        buttonPanel.add(chargeButton);
        buttonPanel.add(chargePortalButton);
        buttonPanel.add(acquirePortalButton);
        buttonPanel.add(shortestPathButton);

        JPanel mainPanel = new JPanel();
        mainPanel.add(idPanel);
        mainPanel.add(levelPanel);
        mainPanel.add(expPanel);
        mainPanel.add(posPanel);
        mainPanel.add(energyPanel);
        mainPanel.add(energyToAddPanel);
        mainPanel.add(movePanel);
        mainPanel.add(buttonPanel);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1250, 500);

        setLocationRelativeTo(null);

        setVisible(true);
    }
}
