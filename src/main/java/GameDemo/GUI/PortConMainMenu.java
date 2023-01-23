package GameDemo.GUI;

import javax.swing.*;
import java.awt.*;

public class PortConMainMenu extends JFrame {
    public PortConMainMenu() {
        super("Management of Portals and Connectors");

        JButton addPortButton = new JButton("Add Portals");
        addPortButton.addActionListener(e -> {

        });

        JButton editPortButton = new JButton("Edit Portals");
        editPortButton.addActionListener(e -> {
        });

        JButton removePortButton = new JButton("Remove Portals");
        removePortButton.addActionListener(e -> {

        });

        JButton addConButton = new JButton("Add Connectors");
        addConButton.addActionListener(e -> {

        });

        JButton addConRegistButton = new JButton("Add Connectors Registration");
        addConRegistButton.addActionListener(e -> {

        });

        JButton editConButton = new JButton("Edit Connectors");
        editConButton.addActionListener(e -> {

        });

        JButton removeConButton = new JButton("Remove Connectors");
        removeConButton.addActionListener(e -> {

        });

        JButton removeConRegistButton = new JButton("Remove Connectors Registration");
        removeConRegistButton.addActionListener(e -> {

        });

        JButton listPortConButton = new JButton("List Portals/Connectors");
        listPortConButton.addActionListener(e -> {

        });

        JButton loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(e -> {

        });

        JButton saveDataButton = new JButton("Export Data");
        saveDataButton.addActionListener(e -> {

        });



        setLayout(new GridLayout(4, 3));

        add(addPortButton);
        add(editPortButton);
        add(removePortButton);
        add(addConButton);
        add(editConButton);
        add(removeConButton);
        add(addConRegistButton);
        add(removeConRegistButton);
        add(listPortConButton);
        add(loadDataButton);
        add(saveDataButton);

        setSize(450, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }
}
