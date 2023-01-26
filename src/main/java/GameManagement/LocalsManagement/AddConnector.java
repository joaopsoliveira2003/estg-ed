package GameManagement.LocalsManagement;

import Game.API.Game;
import Game.Entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddConnector extends JFrame {
    //
    private JPanel panelId;
    private JPanel panelName;
    private JPanel panelLatitude;
    private JPanel panelLongitude;
    private JPanel panelEnergy;
    private JPanel panelcoolDownTime;

    private JLabel labelId;
    private JLabel labelName;
    private JLabel labelLatitude;
    private JLabel labelLongitude;
    private JLabel labelEnergy;
    private JLabel labelcoolDownTime;

    private JTextField textFieldId;
    private JTextField textFieldName;
    private JTextField textFieldLatitude;
    private JTextField textFieldLongitude;
    private JTextField textFieldEnergy;
    private JTextField textFieldcoolDownTime;


    public AddConnector(Game game) {
        super("Add Connector");

        panelId = new JPanel();
        panelId.setLayout(new GridLayout(1, 0));

        panelName = new JPanel();
        panelName.setLayout(new GridLayout(1, 0));

        panelLatitude = new JPanel();
        panelLatitude.setLayout(new GridLayout(1, 0));

        panelLongitude = new JPanel();
        panelLongitude.setLayout(new GridLayout(1, 0));

        panelEnergy = new JPanel();
        panelEnergy.setLayout(new GridLayout(1, 0));

        panelcoolDownTime = new JPanel();
        panelcoolDownTime.setLayout(new GridLayout(1, 0));


        labelId = new JLabel("ID: ");
        textFieldId = new JTextField(15);

        labelName = new JLabel("NAME: ");
        textFieldName = new JTextField(15);

        labelLatitude = new JLabel("LATITUDE: ");
        textFieldLatitude = new JTextField(15);

        labelLongitude = new JLabel("LONGITUDE: ");
        textFieldLongitude = new JTextField(15);

        labelEnergy = new JLabel("ENERGY: ");
        textFieldEnergy = new JTextField(15);

        labelcoolDownTime = new JLabel("COOL DOWN TIME: ");
        textFieldcoolDownTime = new JTextField(15);

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
        add(panelcoolDownTime);

        JButton button = new JButton("CREATE");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //int id, String name, double latitude, double longitude, int energy, int maxEnergy

                int id = Integer.parseInt(textFieldId.getText());
                String name = textFieldName.getText();
                double latitude = Double.parseDouble(textFieldLatitude.getText());
                double longitude = Double.parseDouble(textFieldLongitude.getText());
                int energy = Integer.parseInt(textFieldEnergy.getText());
                int coolDownTime = Integer.parseInt(textFieldcoolDownTime.getText());

                try {
                    game.addConnector(id, name, latitude, longitude, energy, coolDownTime);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }

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
