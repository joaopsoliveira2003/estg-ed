package GameDemo.GUI.PlayersManagement;

import javax.swing.*;
import java.awt.*;
import Game.API.Game;
import Game.Entities.Player;
import Game.Entities.PlayerImpl;
import Game.Entities.Team;
import Game.Entities.TeamImpl;
import java.awt.event.*;

public class AddPlayer extends JFrame {

    private JPanel panelId;
    private JPanel panelNome;
    private JPanel panelTeam;
    private JPanel panelEnergy;
    private JPanel panelExperiencePoints;

    private JLabel labelId;
    private JLabel labelNome;
    private JLabel labelTeam;
    private JLabel labelEnergy;
    private JLabel labelExperiencePoints;

    private JTextField textFieldId;
    private JTextField textFieldNome;
    private JTextField textFieldTeam;
    private JTextField textFieldEnergy;
    private JTextField textFieldExperiencePoints;

    public AddPlayer(Game game) {
        super("Players Management");

        //instancia os componentes
        panelId = new JPanel();
        panelId.setLayout(new GridLayout(1, 0));

        panelNome = new JPanel();
        panelNome.setLayout(new GridLayout(1, 0));

        panelTeam = new JPanel();
        panelTeam.setLayout(new GridLayout(1, 0));

        panelEnergy = new JPanel();
        panelEnergy.setLayout(new GridLayout(1, 0));

        panelExperiencePoints = new JPanel();
        panelExperiencePoints.setLayout(new GridLayout(1, 0));

        labelId = new JLabel("ID: ");
        textFieldId = new JTextField(15);

        labelNome = new JLabel("NAME: ");
        textFieldNome = new JTextField(15);

        JLabel label = new JLabel("CHOOSE TEAM:");

        ButtonGroup group = new ButtonGroup();
        JRadioButton team1 = new JRadioButton("sparks");
        JRadioButton team2 = new JRadioButton("Giants");

        labelEnergy = new JLabel("ENERGY: ");
        textFieldEnergy = new JTextField(15);

        labelExperiencePoints = new JLabel("EXPERIENCE POINTS: ");
        textFieldExperiencePoints = new JTextField(15);

        //adiciona os componentes a janela
        panelId.add(labelId);
        panelId.add(textFieldId);

        //TODO:verificar se o textFieldId inserido se já não existe, se existir não deixa inserir o player

        panelNome.add(labelNome);
        panelNome.add(textFieldNome);

        panelEnergy.add(labelEnergy);
        panelEnergy.add(textFieldEnergy);

        panelExperiencePoints.add(labelExperiencePoints);
        panelExperiencePoints.add(textFieldExperiencePoints);

        add(panelId);
        add(panelNome);
        add(label);
        group.add(team1);
        group.add(team2);

        add(team1);
        add(team2);
        add(panelEnergy);
        add(panelExperiencePoints);

        JButton button = new JButton("CREATE");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(textFieldId.getText());
                String name = textFieldNome.getText();
                Team team = new TeamImpl(name);
                if (team1.isSelected()) {
                    team = new TeamImpl(team1.getText());
                }
                if (team2.isSelected()) {
                    team = new TeamImpl(team2.getText());
                }
                int energy = Integer.parseInt(textFieldEnergy.getText());
                int experiencePoints = Integer.parseInt(textFieldExperiencePoints.getText());

                Player player = new PlayerImpl(id, name, team, energy, experiencePoints);
                game.addPlayer(player);
            }
        });

        add(button);

        setLayout(new GridLayout(0, 1));
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(300, 300);
        setVisible(true);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // --> mostra a janela
        setVisible(true);
    }
}
