package GameManagement.PlayersManagement;

import Collections.Exceptions.IllegalArgumentException;
import Collections.Exceptions.NoSuchElementException;
import Collections.Lists.UnorderedListADT;
import Game.API.Game;
import Game.Entities.Player;
import Game.Entities.Portal;
import Game.Enumerations.SortPlayers;
import Game.Exceptions.NoAssociationException;
import Game.Exceptions.NoSuchPlayerException;
import Game.Exceptions.NoSuchTeamException;
import GameManagement.LocalsManagement.AddConnector;
import GameManagement.LocalsManagement.AddPortal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Iterator;

/**
 * ManagePortal is a JFrame that allows the user to manage the players of the game.
 */
public class ManagePlayers extends JFrame {
    public ManagePlayers(Game game) {
        super("Manage Players");

        class CustomTableModel extends DefaultTableModel {
            @Override
            public void setValueAt(Object aValue, int row, int column) {
                try {
                    super.setValueAt(aValue, row, column);
                    game.updatePlayer(Integer.parseInt(String.valueOf(getValueAt(row, 0))), String.valueOf(getValueAt(row, 1)), String.valueOf(getValueAt(row, 2)));
                    JOptionPane.showMessageDialog(ManagePlayers.this, "Player edited successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalArgumentException | NoSuchPlayerException | NoSuchTeamException exception) {
                    JOptionPane.showMessageDialog(ManagePlayers.this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    super.setValueAt("Error", row, column);
                } catch (NumberFormatException ignored) {
                    JOptionPane.showMessageDialog(ManagePlayers.this, "Invalid value", "Error", JOptionPane.ERROR_MESSAGE);
                    super.setValueAt("Error", row, column);
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column == 2;
            }
        }

        CustomTableModel model = new CustomTableModel();

        model.addColumn("ID"); // 0
        model.addColumn("Name"); // 1
        model.addColumn("Team"); // 2
        model.addColumn("Portals"); // 3
        model.addColumn("Energy"); // 5
        model.addColumn("Level"); // 6
        model.addColumn("Experience Points"); // 7

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        AddPortal addPortal = new AddPortal(game);
        AddConnector addConnector = new AddConnector(game);

        JButton updateListButton = new JButton("Update / Sort List");
        updateListButton.addActionListener(e -> {
            //custom confirmation dialog
            String[] options = new String[]{SortPlayers.ID.toString(), SortPlayers.TEAM.toString(), SortPlayers.LEVEL.toString(), SortPlayers.PORTALS.toString()};
            int option = JOptionPane.showOptionDialog(this, "Sort by:", "Sort Players", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (option == -1) return;
            model.setRowCount(0);
            for (Iterator<Player> it = game.listPlayersOrdered(SortPlayers.valueOf(options[option].toUpperCase())); it.hasNext(); ) {
                Player player = it.next();
                String team;
                try {
                    team = player.getTeam().getName();
                } catch (NoAssociationException ignored) {
                    team = "None";
                }
                int portals;
                try {
                    UnorderedListADT<Portal> locals = player.getPortals();
                    portals = locals.size();
                } catch (NoAssociationException ignored) {
                    portals = 0;
                }
                model.addRow(new Object[]{player.getID(), player.getName(), team, portals, player.getCurrentEnergy(), player.getLevel(), player.getExperiencePoints()});
            }
        });

        AddPlayer addPlayer = new AddPlayer(game);
        JButton addLocalButton = new JButton("Add");
        addLocalButton.addActionListener(e -> {
            if (addPlayer.isVisible()) {
                addPlayer.toFront();
            } else {
                addPlayer.setVisible(true);
            }
        });

        JButton removeLocalButton = new JButton("Remove");
        removeLocalButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                //ask for confirmation
                int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this player?", "Warning", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    try {
                        game.removePlayer((int) model.getValueAt(row, 0));
                        model.removeRow(row);
                        model.fireTableDataChanged();
                        JOptionPane.showMessageDialog(this, "PLayer removed");
                    } catch (NoSuchElementException ignored) {
                        JOptionPane.showMessageDialog(this, "Player already removed");
                    }
                }
            }
        });

        add(scrollPane, BorderLayout.NORTH);
        add(updateListButton, BorderLayout.CENTER);
        add(addLocalButton, BorderLayout.WEST);
        add(removeLocalButton, BorderLayout.EAST);

        pack();

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
