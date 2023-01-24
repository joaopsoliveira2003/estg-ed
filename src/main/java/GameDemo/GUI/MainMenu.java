package GameDemo.GUI;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu(Game game) {
        super("Main Menu");


        LocalsManagementMenu localsManagementMenu = new LocalsManagementMenu(game);
        JButton localsManagement = new JButton("Locals Management");
        localsManagement.addActionListener(e -> {
            if (!localsManagementMenu.isVisible()) {
                localsManagementMenu.setVisible(true);
            } else {
                localsManagementMenu.toFront();
            }
        });

        RoutesManagementMenu routesManagementMenu = new RoutesManagementMenu(game);
        JButton routesManagement = new JButton("Routes Management");
        routesManagement.addActionListener(e -> {
            if (!routesManagementMenu.isVisible()) {
                routesManagementMenu.setVisible(true);
            } else {
                routesManagementMenu.toFront();
            }
        });

        PlayersManagementMenu playersManagementMenu = new PlayersManagementMenu(game);
        JButton playersManagement = new JButton("Players Management");
        playersManagement.addActionListener(e -> {
            if (!playersManagementMenu.isVisible()) {
                playersManagementMenu.setVisible(true);
            } else {
                playersManagementMenu.toFront();
            }
        });

        GameManagementMenu gameManagementMenu = new GameManagementMenu(game);
        JButton gameManagement = new JButton("Game Management");
        gameManagement.addActionListener(e -> {
            if (!gameManagementMenu.isVisible()) {
                gameManagementMenu.setVisible(true);
            } else {
                gameManagementMenu.toFront();
            }
        });

        setLayout(new GridLayout(2, 2));

        add(localsManagement);
        add(routesManagement);
        add(playersManagement);
        add(gameManagement);

        setSize(400, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        setVisible(true);
    }
}
