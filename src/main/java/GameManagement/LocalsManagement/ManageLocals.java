package GameManagement.LocalsManagement;

import Collections.Exceptions.IllegalArgumentException;
import Collections.Exceptions.NoSuchElementException;
import Game.API.Game;
import Game.Entities.Connector;
import Game.Entities.Local;
import Game.Entities.Portal;
import Game.Enumerations.SortLocals;
import Game.Exceptions.NoAssociationException;
import Game.Exceptions.NoSuchLocalException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Iterator;

/**
 * ManagePortal is a JFrame that allows the user to manage the portals and connectors of the game.
 */
public class ManageLocals extends JFrame {
    public ManageLocals(Game game) {
        super("Manage Locals");

        class CustomTableModel extends DefaultTableModel {
            @Override
            public void setValueAt(Object aValue, int row, int column) {
                try {
                    super.setValueAt(aValue, row, column);
                    String type = (String) getValueAt(row, 2);
                    if (type.equals("Portal")) {
                        if (column == 7) return;
                        game.updatePortal(Integer.parseInt(String.valueOf(getValueAt(row, 0))), String.valueOf(getValueAt(row, 1)), (double) Double.parseDouble(String.valueOf(getValueAt(row, 3))), (double) Double.parseDouble(String.valueOf(getValueAt(row, 4))), Integer.parseInt(String.valueOf(getValueAt(row, 5))), Integer.parseInt(String.valueOf(getValueAt(row, 6))));
                    } else if (type.equals("Connector")) {
                        if (column == 6) return;
                        game.updateConnector(Integer.parseInt(String.valueOf(getValueAt(row, 0))), String.valueOf(getValueAt(row, 1)), (double) Double.parseDouble(String.valueOf(getValueAt(row, 3))), (double) Double.parseDouble(String.valueOf(getValueAt(row, 4))), Integer.parseInt(String.valueOf(getValueAt(row, 5))), Integer.parseInt(String.valueOf(getValueAt(row, 7))));
                    }
                    JOptionPane.showMessageDialog(ManageLocals.this, "Local edited successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalArgumentException | NoSuchLocalException exception) {
                    JOptionPane.showMessageDialog(ManageLocals.this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    super.setValueAt("Error", row, column);
                } catch (NumberFormatException ignored) {
                    JOptionPane.showMessageDialog(ManageLocals.this, "Invalid value", "Error", JOptionPane.ERROR_MESSAGE);
                    super.setValueAt("Error", row, column);
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 2 && column != 8;
            }
        }

        CustomTableModel model = new CustomTableModel();

        model.addColumn("ID"); // 0
        model.addColumn("Name"); // 1
        model.addColumn("Type"); // 2
        model.addColumn("Latitude"); // 3
        model.addColumn("Longitude"); // 4
        model.addColumn("Energy"); // 5
        model.addColumn("Max Energy"); // 6
        model.addColumn("Cooldown"); // 7
        model.addColumn("Owner"); // 8

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        AddPortal addPortal = new AddPortal(game);
        AddConnector addConnector = new AddConnector(game);

        JButton updateListButton = new JButton("Update / Sort List");
        updateListButton.addActionListener(e -> {
            //custom confirmation dialog
            String[] options = new String[]{SortLocals.ID.toString(), SortLocals.TYPE.toString(), SortLocals.LATITUDE.toString(), SortLocals.LONGITUDE.toString(), SortLocals.ENERGY.toString()};
            int option = JOptionPane.showOptionDialog(this, "Sort by:", "Sort Locals", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (option == -1) return;
            model.setRowCount(0);
            for (Iterator<Local> it = game.listLocalsOrdered(SortLocals.valueOf(options[option].toUpperCase())); it.hasNext(); ) {
                Local local = it.next();
                if (local instanceof Portal) {
                    String owner;
                    try {
                        owner = ((Portal) local).getOwner().getName();
                    } catch (NoAssociationException ignored) {
                        owner = "None";
                    }
                    model.addRow(new Object[]{local.getID(), local.getName(), "Portal", local.getLatitude(), local.getLongitude(), local.getEnergy(), ((Portal) local).getMaxEnergy(), "Not Applicable", owner});
                } else {
                    model.addRow(new Object[]{local.getID(), local.getName(), "Connector", local.getLatitude(), local.getLongitude(), local.getEnergy(), "Not Applicable", ((Connector) local).getCoolDownTime(), "Not Applicable"});
                }
            }
        });

        JButton addLocalButton = new JButton("Add");
        addLocalButton.addActionListener(e -> {
            //open a confirmation dialog but with custom options
            String[] options = new String[]{"Portal", "Connector"};
            int dialogResult = JOptionPane.showOptionDialog(this, "What type of local do you want to add?", "Add Local", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (dialogResult == 0) {
                if (addPortal.isShowing()) {
                    addPortal.toFront();
                } else {
                    addPortal.setVisible(true);
                }
            } else if (dialogResult == 1) {
                if (addConnector.isShowing()) {
                    addConnector.toFront();
                } else {
                    addConnector.setVisible(true);
                }
            }
        });

        JButton removeLocalButton = new JButton("Remove");
        removeLocalButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                //ask for confirmation
                int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this local?", "Warning", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    try {
                        //check if it is a portal or a connector based on the type
                        if (table.getValueAt(row, 2).equals("Portal")) {
                            game.removePortal((int) table.getValueAt(row, 0));
                        } else {
                            game.removeConnector((int) table.getValueAt(row, 0));
                        }
                        model.removeRow(row);
                        model.fireTableDataChanged();
                        JOptionPane.showMessageDialog(this, "Local removed");
                    } catch (NoSuchElementException ignored) {
                        JOptionPane.showMessageDialog(this, "Local already removed");
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
