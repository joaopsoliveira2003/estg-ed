package GameDemo.GUI;

import Game.API.Game;
import GameDemo.GUI.LocalsManagement.AddPortal;
import GameDemo.GUI.LocalsManagement.EditPortal;
import GameDemo.GUI.LocalsManagement.RemoveLocals;

import javax.swing.*;
import java.awt.*;

public class LocalsManagementMenu extends JFrame {
    public LocalsManagementMenu(Game game) {
        super("Locals Management");

        JButton addPortButton = new JButton("Add Portals");
        addPortButton.addActionListener(e -> {
            if (!addPortButton.isVisible()) {
                addPortButton.setVisible(true);
            }
        });

        JButton editPortButton = new JButton("Edit Portals");
        editPortButton.addActionListener(e -> {

        });

        RemoveLocals removeLocals = new RemoveLocals(game);
        JButton removePortButton = new JButton("Remove Portals/Connectors");
        removePortButton.addActionListener(e -> {
            if (!removeLocals.isVisible()) {
                removeLocals.setVisible(true);
            } else {
                removeLocals.toFront();
            }
        });

        JButton addConButton = new JButton("Add Connectors");
        addConButton.addActionListener(e -> {

        });

        JButton editConButton = new JButton("Edit Connectors");
        editConButton.addActionListener(e -> {

        });

        JButton listPortConButton = new JButton("List Portals/Connectors");
        listPortConButton.addActionListener(e -> {

        });

        JButton loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(e -> {

        });

        JButton emptyButton = new JButton("");
        emptyButton.setEnabled(false);

        JButton saveDataButton = new JButton("Export Data");
        saveDataButton.addActionListener(e -> {

        });


        setLayout(new GridLayout(3, 3));

        add(addPortButton);
        add(editPortButton);
        add(removePortButton);
        add(addConButton);
        add(editConButton);
        add(listPortConButton);
        add(loadDataButton);
        add(emptyButton);
        add(saveDataButton);

        setSize(450, 450);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);
    }
}
