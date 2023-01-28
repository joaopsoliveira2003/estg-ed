package PlayerSimulator;

import javax.swing.*;

import Game.API.Game;

public class PlayerDemo extends JFrame {
    public PlayerDemo(Game game) {
        super("Player Simulator");

        //the objective of this window is to have a textbox where we can enter the id of the player and simulate actions of the player
        //we also need a textbox or a label where we can see the level of the player, another for the experience points, and the position of the player
        //there should be a place to simulate the player moving to a new location, one to charge the player, one to charge the portal, one to acquire the portal
        //one to calculate the shortest path

        JPanel idPanel = new JPanel();
        JLabel idLabel = new JLabel("Player ID: ");
        JTextField idField = new JTextField(10);
        idPanel.add(idLabel);
        idPanel.add(idField);

        JPanel levelPanel = new JPanel();
        JLabel levelLabel = new JLabel("Level: ");
        JTextField levelField = new JTextField(10);
        levelField.setEnabled(false);
        levelPanel.add(levelLabel);
        levelPanel.add(levelField);

        JPanel expPanel = new JPanel();
        JLabel expLabel = new JLabel("Experience Points: ");
        JTextField expField = new JTextField(10);
        expField.setEnabled(false);
        expPanel.add(expLabel);
        expPanel.add(expField);

        JPanel posPanel = new JPanel();
        JLabel posLabel = new JLabel("Position: ");
        JTextField posField = new JTextField(10);
        posField.setEnabled(false);
        posPanel.add(posLabel);
        posPanel.add(posField);

        JPanel energyPanel = new JPanel();
        JLabel energyLabel = new JLabel("Energy to Add: ");
        JTextField energyField = new JTextField(10);
        energyPanel.add(energyLabel);
        energyPanel.add(energyField);

        JPanel movePanel = new JPanel();
        JLabel moveLabel = new JLabel("Move to: ");
        JTextField moveField = new JTextField(10);
        movePanel.add(moveLabel);
        movePanel.add(moveField);

        JPanel buttonPanel = new JPanel();
        JButton moveButton = new JButton("Move Player");
        moveButton.addActionListener(e -> {
            //get the id from the idField
            //get the position from the moveField
            //call the movePlayer method from the game class
            //update the levelField, expField, and posField
            try {
                game.movePlayer(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()));
                JOptionPane.showMessageDialog(null, "Player moved");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
        JButton chargeButton = new JButton("Charge Player");
        chargeButton.addActionListener(e -> {
            //get the id from the idField
            //call the chargePlayer method from the game class
            //update the levelField, expField, and posField

            try {
                game.chargePlayer(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()));
                JOptionPane.showMessageDialog(null, "Player charged");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
        JButton chargePortalButton = new JButton("Charge Portal");
        chargePortalButton.addActionListener(e -> {
            //get the id from the idField
            //call the chargePortal method from the game class
            //update the levelField, expField, and posField
            try {
                game.chargePortal(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()), Integer.parseInt(energyField.getText()));
                JOptionPane.showMessageDialog(null, "Portal charged");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
        JButton acquirePortalButton = new JButton("Acquire Portal");
        acquirePortalButton.addActionListener(e -> {
            //get the id from the idField
            //call the acquirePortal method from the game class
            //update the levelField, expField, and posField
            try {
                game.acquirePortal(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()));
                JOptionPane.showMessageDialog(null, "Portal acquired");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
        JButton shortestPathButton = new JButton("Shortest Path");
        shortestPathButton.addActionListener(e -> {
            //get the id from the idField
            //call the shortestPath method from the game class
            //update the levelField, expField, and posField
            try {
                throw new Exception("Not implemented yet");
                //game.getShortestPath(Integer.parseInt(idField.getText()), Integer.parseInt(moveField.getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
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
        mainPanel.add(movePanel);
        mainPanel.add(buttonPanel);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1250, 500);

        setLocationRelativeTo(null);

        setVisible(true);
    }
}
