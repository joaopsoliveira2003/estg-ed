package GameManagement.PlayersManagement;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;

public class PlayersManagementMenu extends JFrame {
    public PlayersManagementMenu(Game game) {
        super("Players Management");

        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(e -> {
            new AddPlayer(game);

        });

        JButton editPlayerButton = new JButton("Edit Player");
        editPlayerButton.addActionListener(e -> {
            new EditPlayerList(game);
        });

        JButton removePlayerButton = new JButton("Remove Player");
        removePlayerButton.addActionListener(e -> {
            new RemovePlayerList(game);
        });

        setLayout(new GridLayout(3, 3));

        add(addPlayerButton);
        add(editPlayerButton);
        add(removePlayerButton);


        setSize(450, 450);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);
    }
}

