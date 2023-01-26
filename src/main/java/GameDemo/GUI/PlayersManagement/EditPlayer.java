package GameDemo.GUI.PlayersManagement;

import Collections.Exceptions.NoSuchElementException;
import Collections.HashTables.HashMap;
import Collections.HashTables.MapADT;
import Game.API.Game;
import Game.Entities.Player;
import Game.Enumerations.PlayerFilter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EditPlayer extends JFrame {
    public EditPlayer(Game game) {
        super("Edit Player");

        MapADT<Integer, Player> map = new HashMap<>();

        DefaultTableModel modelPlayer = new DefaultTableModel();

        modelPlayer.addColumn("ID");
        modelPlayer.addColumn("Name");
        modelPlayer.addColumn("Team");
        modelPlayer.addColumn("Current Energy");
        modelPlayer.addColumn("Experience Points");

        for (Player player : game.listPlayersOrdered(PlayerFilter.ID)) {
            map.put(player.getID(), player);
            modelPlayer.addRow(new Object[]{player.getID(), player.getName(), player.getTeam(), player.getCurrentEnergy(), player.getExperiencePoints()});
        }

        JTable table = new JTable(modelPlayer);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.setFillsViewportHeight(true);


        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

}
