package GameDemo.GUI;

import Game.API.Game;
import GameDemo.GUI.PlayersManagement.AddPlayer;
import GameDemo.GUI.PlayersManagement.EditPlayer;
import GameDemo.GUI.PlayersManagement.RemovePlayer;
import javax.swing.*;
import java.awt.*;

public class PlayersManagementMenu extends JFrame {
    public PlayersManagementMenu(Game game) {
        super("Players Management");

        AddPlayer addPlayer = new AddPlayer(game);
        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(e -> {
            if (!addPlayer.isVisible()) {
                addPlayer.setVisible(true);
            } else {
                addPlayer.toFront();
            }
        });

        EditPlayer editPlayer = new EditPlayer(game);
        JButton editPlayerButton = new JButton("Edit Player");
        editPlayerButton.addActionListener(e -> {
            if (!editPlayer.isVisible()) {
                editPlayer.setVisible(true);
            } else {
                editPlayer.toFront();
            }
        });

        RemovePlayer removePlayer = new RemovePlayer(game);
        JButton removePlayerButton = new JButton("Remove Player");
        removePlayerButton.addActionListener(e -> {
            if (!removePlayer.isVisible()) {
                removePlayer.setVisible(true);
            } else {
                removePlayer.toFront();
            }
        });

        setLayout(new GridLayout(3, 3));

        setSize(450, 450);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);
    }

}

