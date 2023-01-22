package GameDemo.GUI;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        super("Main Menu");

        JButton gestPortConButton = new JButton("Management of Portals and Connectors");
        gestPortConButton.addActionListener(e -> {
            new PortConMainMenu();
        });

        JButton gestRoutesButton = new JButton("Management of Routes");
        gestRoutesButton.addActionListener(e -> {

        });

        JButton gestPlayersButton = new JButton("Management of Players");
        gestPlayersButton.addActionListener(e -> {

        });

        JButton gestGameButton = new JButton("Management of Game");
        gestGameButton.addActionListener(e -> {

        });
        setLayout(new GridLayout(2, 2));

        add(gestPortConButton);
        add(gestRoutesButton);
        add(gestPlayersButton);
        add(gestGameButton);


        setSize(350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }
}
