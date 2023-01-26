package GameManagement.PlayersManagement;

import Collections.Exceptions.NoSuchElementException;
import Collections.HashTables.HashMap;
import Collections.HashTables.MapADT;
import Game.API.Game;
import Game.Entities.Player;
import Game.Enumerations.PlayerFilter;
import Game.Exceptions.NoAssociationException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RemovePlayerList extends JFrame {
    public RemovePlayerList(Game game) {
        super("Remove Player");

        MapADT<Integer, Player> map = new HashMap<>();

        DefaultTableModel modelPlayer = new DefaultTableModel();

        modelPlayer.addColumn("ID");
        modelPlayer.addColumn("Name");
        modelPlayer.addColumn("Team");
        modelPlayer.addColumn("Current Energy");
        modelPlayer.addColumn("Experience Points");

        for(Player player : game.listPlayersOrdered(PlayerFilter.ID)){
            map.put(player.getID(), player);
            String team;
            try {
                team = player.getTeam().getName();
            } catch (NoAssociationException ignored){
                team = "None";
            }
            modelPlayer.addRow(new Object[]{player.getID(),player.getName(),team,player.getCurrentEnergy(),player.getExperiencePoints()});
        }

        JTable table = new JTable(modelPlayer);
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
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this player?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        try {
                            //game.removePlayer(map.get((int) table.getValueAt(row, 0)));
                            modelPlayer.removeRow(row);
                            modelPlayer.fireTableDataChanged();
                            JOptionPane.showMessageDialog(null, "Player removed");
                        } catch (NoSuchElementException ignored) {
                            JOptionPane.showMessageDialog(null, "Player already removed");
                        }
                    }
                }
            }
        });

        add(scrollPane);

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }
}
