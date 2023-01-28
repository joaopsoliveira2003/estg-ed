package GameManagement;

import Game.API.Game;
import GameManagement.GameManagement.GameManagementMenu;
import GameManagement.LocalsManagement.LocalsManagementMenu;
import GameManagement.PlayersManagement.PlayersManagementMenu;
import GameManagement.RoutesManagement.RoutesManagementMenu;

import javax.swing.*;
import java.awt.*;

/**
 * MainMenu is a JFrame that allows the user to access the different menus of the game.
 */
public class MainMenu extends JFrame {
    public MainMenu(Game game) {

        super("Main Menu");

        LocalsManagementMenu localsManagementMenu = new LocalsManagementMenu(game);
        JButton localsManagementButton = new JButton("Locals Management");
        localsManagementButton.addActionListener(e -> {
            if (!localsManagementMenu.isVisible()) {
                localsManagementMenu.setLocationRelativeTo(this);
                localsManagementMenu.setVisible(true);
            } else {
                localsManagementMenu.toFront();
            }
        });

        RoutesManagementMenu routesManagementMenu = new RoutesManagementMenu(game);
        JButton routesManagementButton = new JButton("Routes Management");
        routesManagementButton.addActionListener(e -> {
            if (!routesManagementMenu.isVisible()) {
                routesManagementMenu.setLocationRelativeTo(this);
                routesManagementMenu.setVisible(true);
            } else {
                routesManagementMenu.toFront();
            }
        });

        PlayersManagementMenu playersManagementMenu = new PlayersManagementMenu(game);
        JButton playersManagementButton = new JButton("Players Management");
        playersManagementButton.addActionListener(e -> {
            if (!playersManagementMenu.isVisible()) {
                playersManagementMenu.setLocationRelativeTo(this);
                playersManagementMenu.setVisible(true);
            } else {
                playersManagementMenu.toFront();
            }
        });

        GameManagementMenu gameManagementMenu = new GameManagementMenu(game);
        JButton gameManagementButton = new JButton("Game Management");
        gameManagementButton.addActionListener(e -> {
            if (!gameManagementMenu.isVisible()) {
                gameManagementMenu.setLocationRelativeTo(this);
                gameManagementMenu.setVisible(true);
            } else {
                gameManagementMenu.toFront();
            }
        });

        setLayout(new GridLayout(2, 2));

        add(localsManagementButton);
        add(routesManagementButton);
        add(playersManagementButton);
        add(gameManagementButton);

        setSize(400, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation(500, 250);

        setResizable(false);

        setVisible(true);
    }
}
