package PlayerSimulator;

import javax.swing.*;

import Game.API.Game;
import Game.Exceptions.NoSuchPlayerException;
import GameManagement.GameManagement.ShortestPaths;

import java.awt.*;

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
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });

        JPanel buttonPanel = new JPanel();
        JButton moveButton = new JButton("Move Player");
        moveButton.addActionListener(e -> {

            try {
                game.movePlayer(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()));
                updateFields(game, idField, levelField, expField, posField, energyField);
                JOptionPane.showMessageDialog(this, "Player moved");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });

        JButton chargeButton = new JButton("Charge Player");
        chargeButton.addActionListener(e -> {

            try {
                game.chargePlayer(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()));
                updateFields(game, idField, levelField, expField, posField, energyField);
                JOptionPane.showMessageDialog(this, "Player charged");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });

        JButton chargePortalButton = new JButton("Charge Portal");
        chargePortalButton.addActionListener(e -> {

            try {
                String energyToAdd = JOptionPane.showInputDialog(this, "Enter energy to add");
                if (energyToAdd == null) {
                    throw new Exception("Energy to add not entered");
                }
                int energyToAddInt = Integer.parseInt(energyToAdd);
                game.chargePortal(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()), energyToAddInt);
                updateFields(game, idField, levelField, expField, posField, energyField);
                JOptionPane.showMessageDialog(this, "Portal charged");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });

        JButton acquirePortalButton = new JButton("Acquire Portal");
        acquirePortalButton.addActionListener(e -> {

            try {
                game.acquirePortal(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()));
                updateFields(game, idField, levelField, expField, posField, energyField);
                JOptionPane.showMessageDialog(this, "Portal acquired");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });

        JButton shortestPathButton = new JButton("Shortest Path");
        shortestPathButton.addActionListener(e -> {
            ShortestPaths shortestPaths = new ShortestPaths(game);
            shortestPaths.setLocationRelativeTo(this);
            shortestPaths.setVisible(true);
        });

        //add player to team
        JButton addPlayerToTeamButton = new JButton("Add Player to Team");
        addPlayerToTeamButton.addActionListener(e -> {
            try {
                String teamName = JOptionPane.showInputDialog(this, "Enter team name");
                if (teamName == null || teamName.isEmpty()) {
                    throw new Exception("Team name cannot be empty");
                }
                game.addPlayerToTeam(Integer.parseInt(idField.getText()), teamName);
                updateFields(game, idField, levelField, expField, posField, energyField);
                JOptionPane.showMessageDialog(this, "Player added to team");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });

        //remove player from team
        JButton removePlayerFromTeamButton = new JButton("Remove Player from Team");
        removePlayerFromTeamButton.addActionListener(e -> {
            try {
                game.removePlayerFromTeam(Integer.parseInt(idField.getText()));
                updateFields(game, idField, levelField, expField, posField, energyField);
                JOptionPane.showMessageDialog(this, "Player removed from team");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.add(idPanel);
        mainPanel.add(movePanel);
        mainPanel.add(levelPanel);
        mainPanel.add(expPanel);
        mainPanel.add(posPanel);
        mainPanel.add(energyPanel);

        mainPanel.add(showInfoButton);
        mainPanel.add(moveButton);
        mainPanel.add(chargeButton);
        mainPanel.add(chargePortalButton);
        mainPanel.add(acquirePortalButton);
        mainPanel.add(shortestPathButton);
        mainPanel.add(addPlayerToTeamButton);
        JButton emptyButton = new JButton("");
        emptyButton.setEnabled(false);
        mainPanel.add(emptyButton);
        mainPanel.add(removePlayerFromTeamButton);

        mainPanel.setLayout(new GridLayout(5, 3));


        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(600, 600);

        setLocation(1000, 250);

        setVisible(true);
    }
}
