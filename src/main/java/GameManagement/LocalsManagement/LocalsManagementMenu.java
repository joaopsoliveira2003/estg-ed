package GameManagement.LocalsManagement;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LocalsManagementMenu extends JFrame {
    public LocalsManagementMenu(Game game) {
        super("Locals Management");

        AddPortal addPortal = new AddPortal(game);
        JButton addPortalButton = new JButton("Add Portals");
        addPortalButton.addActionListener(e -> {
            if (!addPortal.isVisible()) {
                addPortal.setVisible(true);
            } else {
                addPortal.toFront();
            }
        });

        EditPortal editPortal = new EditPortal(game);
        JButton editPortalButton = new JButton("Edit Portals");
        editPortalButton.addActionListener(e -> {
            if (!editPortal.isVisible()) {
                editPortal.setVisible(true);
            } else {
                editPortal.toFront();
            }
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

        AddConnector addConnector = new AddConnector(game);
        JButton addConnectorButton = new JButton("Add Connectors");
        addConnectorButton.addActionListener(e -> {
            if (!addConnector.isVisible()) {
                addConnector.setVisible(true);
            } else {
                addConnector.toFront();
            }
        });

        EditConnector editConnector = new EditConnector(game);
        JButton editConButton = new JButton("Edit Connectors");
        editConButton.addActionListener(e -> {
            if (!editConnector.isVisible()) {
                editConnector.setVisible(true);
            } else {
                editConnector.toFront();
            }
        });

        ListLocals listLocals = new ListLocals(game);
        JButton listPortConButton = new JButton("List Portals/Connectors");
        listPortConButton.addActionListener(e -> {
            if (!listLocals.isVisible()) {
                listLocals.setVisible(true);
            } else {
                listLocals.toFront();
            }
        });


        JButton loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(ignored -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to load data from");
            fileChooser.setCurrentDirectory(new java.io.File("."));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    game.loadLocals(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data loaded successfully");
                } catch (IOException exception) {
                    new JOptionPane(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        JButton saveDataButton = new JButton("Save Data");
        saveDataButton.addActionListener(ignored -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to save data to");
            fileChooser.setCurrentDirectory(new java.io.File("."));
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    game.exportLocals(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Data saved successfully");
                } catch (IOException exception) {
                    new JOptionPane(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton emptyButton = new JButton("");
        emptyButton.setEnabled(false);




        setLayout(new GridLayout(4, 2));

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
