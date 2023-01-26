package GameDemo.GUI.LocalsManagement;

import Collections.Exceptions.NoSuchElementException;
import Collections.HashTables.HashMap;
import Collections.HashTables.MapADT;
import Game.API.Game;
import Game.Entities.Local;
import Game.Entities.Portal;
import Game.Enumerations.LocalFilter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class RemoveLocals extends JFrame {
    public RemoveLocals(Game game) {
        super("Remove Locals");

        MapADT<Integer, Local> map = new HashMap<>();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Type");
        model.addColumn("Latitude");
        model.addColumn("Longitude");
        model.addColumn("Energy");

        for (Local local : game.listLocalsOrdered(LocalFilter.ID)) {
            String type;
            if (local instanceof Portal) {
                type = "Portal";
            } else {
                type = "Connector";
            }
            model.addRow(new Object[]{local.getID(), local.getName(), type, local.getLatitude(), local.getLongitude(), local.getEnergy()});
            map.put(local.getID(), local);
        }

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                int row = table.rowAtPoint(event.getPoint());
                int col = table.columnAtPoint(event.getPoint());
                if (row >= 0 && col >= 0) {
                    //ask for confirmation
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this local?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        try {
                            game.removeLocal(map.get((int) table.getValueAt(row, 0)));
                            model.removeRow(row);
                            model.fireTableDataChanged();
                            JOptionPane.showMessageDialog(null, "Local removed");
                        } catch (NoSuchElementException ignored) {
                            JOptionPane.showMessageDialog(null, "Local already removed");
                        }
                    }
                }
             }
        });

        add(scrollPane);

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
