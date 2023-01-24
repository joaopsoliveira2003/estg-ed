package GameDemo.GUI.LocalsManagement;

import javax.swing.*;
import java.awt.*;
import org.json.simple.JSONObject;
public class AddPortal extends JFrame {

    public AddPortal() {
       System.out.println("*********** ADD PORTAL ***********");
        this.setSize(500, 500);
        this.setLayout(new GridLayout(0, 1));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JTextField id = new JTextField("id");
        JTextField name = new JTextField("name");
        JTextField longitude = new JTextField("longitude");
        JTextField latitude = new JTextField("latitude");
        JTextField energy = new JTextField("energy");
        JTextField maxEnergy = new JTextField("maxEnergy");
        JTextField owner = new JTextField("owner");

        JButton add = new JButton("Add");

        this.add(id);
        this.add(name);
        this.add(longitude);
        this.add(latitude);
        this.add(energy);
        this.add(maxEnergy);
        this.add(owner);
        this.add(add);

        add.addActionListener(e -> {
            JSONObject portal = new JSONObject();
            portal.put("id", id.getText());
            portal.put("type", "Portal");
            portal.put("name", name.getText());
            JSONObject coordinates = new JSONObject();
            coordinates.put("longitude", longitude.getText());
            coordinates.put("latitude", latitude.getText());
            portal.put("coordinates", coordinates);
            JSONObject gameSettings = new JSONObject();
            gameSettings.put("energy", energy.getText());
            gameSettings.put("maxEnergy", maxEnergy.getText());
            JSONObject ownership = new JSONObject();
            ownership.put("player", owner.getText());
            gameSettings.put("ownership", ownership);
            portal.put("gameSettings", gameSettings);
            System.out.println(portal);
            this.setVisible(false);
        });

    }

}
