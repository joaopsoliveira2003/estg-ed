package GameManagement.LocalsManagement;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;

/**
 * AddConnector is a JFrame that allows the user to add a connector to the game.
 */
public class AddConnector extends JFrame {

    public AddConnector(Game game) {
        super("Add Connector");

        JPanel panelId = new JPanel();
        panelId.setLayout(new GridLayout(1, 0));

        JPanel panelName = new JPanel();
        panelName.setLayout(new GridLayout(1, 0));

        JPanel panelLatitude = new JPanel();
        panelLatitude.setLayout(new GridLayout(1, 0));

        JPanel panelLongitude = new JPanel();
        panelLongitude.setLayout(new GridLayout(1, 0));

        JPanel panelEnergy = new JPanel();
        panelEnergy.setLayout(new GridLayout(1, 0));

        JPanel panelcoolDownTime = new JPanel();
        panelcoolDownTime.setLayout(new GridLayout(1, 0));


        JLabel labelId = new JLabel("ID: ");
        JTextField textFieldId = new JTextField(15);

        JLabel labelName = new JLabel("NAME: ");
        JTextField textFieldName = new JTextField(15);

        JLabel labelLatitude = new JLabel("LATITUDE: ");
        JTextField textFieldLatitude = new JTextField(15);

        JLabel labelLongitude = new JLabel("LONGITUDE: ");
        JTextField textFieldLongitude = new JTextField(15);

        JLabel labelEnergy = new JLabel("ENERGY: ");
        JTextField textFieldEnergy = new JTextField(15);

        JLabel labelcoolDownTime = new JLabel("COOL DOWN TIME: ");
        JTextField textFieldcoolDownTime = new JTextField(15);

        panelId.add(labelId);
        panelId.add(textFieldId);

        panelName.add(labelName);
        panelName.add(textFieldName);

        panelLatitude.add(labelLatitude);
        panelLatitude.add(textFieldLatitude);

        panelLongitude.add(labelLongitude);
        panelLongitude.add(textFieldLongitude);

        panelEnergy.add(labelEnergy);
        panelEnergy.add(textFieldEnergy);

        panelcoolDownTime.add(labelcoolDownTime);
        panelcoolDownTime.add(textFieldcoolDownTime);

        add(panelId);
        add(panelName);
        add(panelLatitude);
        add(panelLongitude);
        add(panelEnergy);
        add(panelcoolDownTime);

        JButton button = new JButton("CREATE");
        button.addActionListener(e -> {

            try {
                int id = Integer.parseInt(textFieldId.getText());
                String name = textFieldName.getText();
                double latitude = (double) Double.parseDouble(textFieldLatitude.getText());
                double longitude = (double) Double.parseDouble(textFieldLongitude.getText());
                int energy = Integer.parseInt(textFieldEnergy.getText());
                int coolDownTime = Integer.parseInt(textFieldcoolDownTime.getText());
                game.addConnector(id, name, latitude, longitude, energy, coolDownTime);
                JOptionPane.showMessageDialog(this, "Connector added successfully");
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
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
