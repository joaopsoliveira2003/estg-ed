package GameManagement.LocalsManagement;

import Collections.HashTables.HashMap;
import Collections.HashTables.MapADT;
import Game.API.Game;
import Game.Entities.Local;
import Game.Entities.Portal;
import Game.Enumerations.SortLocals;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Iterator;

public class ListLocals extends JFrame {
    public ListLocals(Game game) {
        super("List Locals");

        MapADT<Integer, Local> map = new HashMap<>();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Type");
        model.addColumn("Latitude");
        model.addColumn("Longitude");
        model.addColumn("Energy");

        for (Iterator<Local> it = game.listLocalsOrdered(SortLocals.ID); it.hasNext(); ) {
            Local local = it.next();
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

        add(scrollPane);

        pack();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
