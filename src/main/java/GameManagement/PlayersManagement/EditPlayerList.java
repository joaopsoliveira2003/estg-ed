package GameManagement.PlayersManagement;

import Collections.HashTables.HashMap;
import Collections.HashTables.MapADT;
import Game.API.Game;
import Game.Entities.Player;
import Game.Enumerations.PlayerFilter;
import Game.Exceptions.NoAssociationException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EditPlayerList extends JFrame {
    public EditPlayerList(Game game) {
        super("Edit Player");

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("NAME");
        model.addColumn("TEAM");

        MapADT<Integer,Player> players = new HashMap<>();

        for (Player player : game.listPlayersOrdered(PlayerFilter.ID)) {
            players.put(player.getID(), player);
            String team;
            try {
                team = player.getTeam().getName();
            } catch (NoAssociationException ignored){
                team = "None";
            }
            model.addRow(new Object[]{player.getID(), player.getName(), team});

        }

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                int row = table.rowAtPoint(event.getPoint());
                int col = table.columnAtPoint(event.getPoint());
                if (row >= 0 && col >= 0) {
                    new EditPlayer(players.get((int) table.getValueAt(row, 0)), game);
                }
            }
        });

        setLayout(new BorderLayout());

        add(scrollPane);

        pack();

        setSize(500, 500);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setVisible(true);
    }

}
