package GameManagement.LocalsManagement;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;

public class LocalsManagementMenu extends JFrame {
    public LocalsManagementMenu(Game game) {
        super("Locals Management");

        JButton addPortalButton = new JButton("Add Portals");
        addPortalButton.addActionListener(e -> {

        });

        JButton editPortalButton = new JButton("Edit Portals");
        editPortalButton.addActionListener(e -> {

        });

        RemoveLocals removeLocals = new RemoveLocals(game);
        JButton removePortalButton = new JButton("Remove Portals/Connectors");
        removePortalButton.addActionListener(e -> {
            if (!removeLocals.isVisible()) {
                removeLocals.setVisible(true);
            } else {
                removeLocals.toFront();
            }
        });

        JButton addConnectorButton = new JButton("Add Connectors");
        addConnectorButton.addActionListener(e -> {

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

        add(addPortalButton);
        add(editPortalButton);
        add(removePortalButton);
        add(addConnectorButton);
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
