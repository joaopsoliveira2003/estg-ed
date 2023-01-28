package GameManagement.PlayersManagement;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;

/**
 * AddPlayer is a JFrame that allows the user to add a player to the game.
 */
public class AddPlayer extends JFrame {

    public AddPlayer(Game game) {
        super("Add Player");

        JPanel panelId = new JPanel();
        panelId.setLayout(new GridLayout(1, 0));

        JLabel labelId = new JLabel("ID: ");
        JTextField textFieldId = new JTextField(15);

        JPanel panelName = new JPanel();
        panelName.setLayout(new GridLayout(1, 0));

        JLabel labelName = new JLabel("NAME: ");
        JTextField textFieldName = new JTextField(15);

        JLabel labelTeam = new JLabel("CHOOSE TEAM:");

        ButtonGroup radioTeam = new ButtonGroup();
        JRadioButton team1 = new JRadioButton("Sparks");
        JRadioButton team2 = new JRadioButton("Giants");


        panelId.add(labelId);
        panelId.add(textFieldId);
        add(panelId);

        panelName.add(labelName);
        panelName.add(textFieldName);
        add(panelName);

        add(labelTeam);
        radioTeam.add(team1);
        radioTeam.add(team2);

        add(team1);
        add(team2);

        JButton button = new JButton("CREATE");
        button.addActionListener((ignored) -> {
            int id = Integer.parseInt(textFieldId.getText());
            String name = textFieldName.getText();
            String team = "None";
            if (team1.isSelected()) {
                team = "Sparks";
            } else if (team2.isSelected()) {
                team = "Giants";
            }

            try {
                game.addPlayer(id, name, team);
                JOptionPane.showMessageDialog(null, "Player added successfully!");
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });

        add(button);

        setLayout(new GridLayout(0, 1));

        setSize(400, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pack();

        setLocationRelativeTo(null);

    }
}
