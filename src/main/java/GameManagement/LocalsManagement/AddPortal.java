package GameManagement.LocalsManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.API.Game;
public class AddPortal extends JFrame {

    private JPanel panelId;
    private JPanel panelName;
    private JPanel panelLatitude;
    private JPanel panelLongitude;
    private JPanel panelEnergy;
    private JPanel panelMaxEnergy;

    private JLabel labelId;
    private JLabel labelName;
    private JLabel labelLatitude;
    private JLabel labelLongitude;
    private JLabel labelEnergy;
    private JLabel labelMaxEnergy;

    private JTextField textFieldId;
    private JTextField textFieldName;
    private JTextField textFieldLatitude;
    private JTextField textFieldLongitude;
    private JTextField textFieldEnergy;
    private JTextField textFieldMaxEnergy;

    public AddPortal(Game game) {
        super("Add Portal");

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

        panelMaxEnergy = new JPanel();
        panelMaxEnergy.setLayout(new GridLayout(1, 0));

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

        labelMaxEnergy = new JLabel("MAX ENERGY: ");
        textFieldMaxEnergy = new JTextField(15);

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

        panelMaxEnergy.add(labelMaxEnergy);
        panelMaxEnergy.add(textFieldMaxEnergy);


        add(panelId);
        add(panelName);
        add(panelLatitude);
        add(panelLongitude);
        add(panelEnergy);
        add(panelMaxEnergy);

        JButton button = new JButton("CREATE");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textFieldId.getText());
                String name = textFieldName.getText();
                double latitude = Double.parseDouble(textFieldLatitude.getText());
                double longitude = Double.parseDouble(textFieldLongitude.getText());
                int energy = Integer.parseInt(textFieldEnergy.getText());
                int maxEnergy = Integer.parseInt(textFieldMaxEnergy.getText());

                try {
                    game.addPortal(id, name, latitude, longitude, energy, maxEnergy);
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
