package GameManagement.RoutesManagement;

import Game.API.Game;

import javax.swing.*;
import java.awt.*;

/**
 * AddRoutes is a JFrame that allows the user to remove a route from the game.
 */
public class RemoveRoutes extends JFrame {
    public RemoveRoutes(Game game) {
        super("Remove Route");

        JPanel panelId1 = new JPanel();
        panelId1.setLayout(new GridLayout(1, 0));

        JLabel labelId1 = new JLabel("INICIAL POINT: ");
        JTextField textFieldId1 = new JTextField(15);

        JPanel panelId2 = new JPanel();
        panelId2.setLayout(new GridLayout(1, 0));

        JLabel labelId2 = new JLabel("FINAL POINT: ");
        JTextField textFieldId2 = new JTextField(15);

        panelId1.add(labelId1);
        panelId1.add(textFieldId1);

        panelId2.add(labelId2);
        panelId2.add(textFieldId2);

        add(panelId1);
        add(panelId2);

        JButton button = new JButton("REMOVE");
        button.addActionListener((ignored) -> {

            try {
                int id1 = Integer.parseInt(textFieldId1.getText());
                int id2 = Integer.parseInt(textFieldId2.getText());
                game.removeRoute(id1,id2);
                JOptionPane.showMessageDialog(null, "Route removed successfully!");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });

        add(button);

        setLayout(new GridLayout(0, 1));

        setSize(400, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pack();

        setLocationRelativeTo(null);

    }
}
