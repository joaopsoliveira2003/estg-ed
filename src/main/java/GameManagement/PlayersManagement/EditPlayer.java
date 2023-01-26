package GameManagement.PlayersManagement;

import Game.API.Game;
import Game.Entities.Player;
import Game.Enumerations.PlayerFilter;
import Game.Exceptions.NoAssociationException;
import Game.Exceptions.NoSuchPlayerException;
import Game.Exceptions.NoSuchTeamException;

import javax.swing.*;
import java.awt.*;

public class EditPlayer extends JFrame {

    public EditPlayer(Player player, Game game) {
        super("Edit Player");
        JPanel panelId = new JPanel();
        panelId.setLayout(new GridLayout(1, 0));

        JPanel panelNome = new JPanel();
        panelNome.setLayout(new GridLayout(1, 0));

        JPanel panelTeam = new JPanel();
        panelTeam.setLayout(new GridLayout(1, 0));

        JPanel panelEnergy = new JPanel();
        panelEnergy.setLayout(new GridLayout(1, 0));

        JPanel panelExperiencePoints = new JPanel();
        panelExperiencePoints.setLayout(new GridLayout(1, 0));

        JLabel labelId = new JLabel("ID: ");
        JTextField textFieldId = new JTextField(15);
        textFieldId.setText(String.valueOf(player.getID()));

        JLabel labelNome = new JLabel("NAME: ");
        JTextField textFieldNome = new JTextField(15);
        textFieldNome.setText(player.getName());

        JLabel label = new JLabel("CHOOSE TEAM:");

        ButtonGroup group = new ButtonGroup();
        JRadioButton team1 = new JRadioButton("Sparks");
        JRadioButton team2 = new JRadioButton("Giants");
        try {
            if (player.getTeam().getName().equals("Sparks")) {
                team1.setSelected(true);
            } else {
                team2.setSelected(true);
            }
        } catch (NoAssociationException ignored) {}

        JLabel labelEnergy = new JLabel("ENERGY: ");
        JTextField textFieldEnergy = new JTextField(15);
        textFieldEnergy.setText(String.valueOf(player.getCurrentEnergy()));

        JLabel labelExperiencePoints = new JLabel("EXPERIENCE POINTS: ");
        JTextField textFieldExperiencePoints = new JTextField(15);
        textFieldExperiencePoints.setText(String.valueOf(player.getExperiencePoints()));


        JButton button = new JButton("UPDATE");
        button.addActionListener(e -> {
            try {
                String team = "None";
                if (team1.isSelected()) {
                    team = "Sparks";
                } else if (team2.isSelected()) {
                    team = "Giants";
                }
                game.updatePlayer(player.getID(), textFieldNome.getText(), team);
                JOptionPane.showMessageDialog(this, "Player updated successfully");
                dispose();
            } catch (NoSuchTeamException exception) {
                JOptionPane.showMessageDialog(this, "Player must be associated to a team");
            } catch (NoSuchPlayerException exception) {
                JOptionPane.showMessageDialog(this, "Player does not exist");
            }
        });

        setLayout(new GridLayout(game.listPlayersOrdered(PlayerFilter.ID).size(), 5));

        //adiciona os componentes a janela
        panelId.add(labelId);
        panelId.add(textFieldId);
        add(panelId);

        panelNome.add(labelNome);
        panelNome.add(textFieldNome);
        add(panelNome);

        add(label);
        group.add(team1);
        group.add(team2);
        panelTeam.add(team1);
        panelTeam.add(team2);
        add(panelTeam);

        panelEnergy.add(labelEnergy);
        panelEnergy.add(textFieldEnergy);
        add(panelEnergy);

        panelExperiencePoints.add(labelExperiencePoints);
        panelExperiencePoints.add(textFieldExperiencePoints);
        add(panelExperiencePoints);

        add(button);





        setSize(400, 400);

        pack();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setVisible(true);





    }
}
