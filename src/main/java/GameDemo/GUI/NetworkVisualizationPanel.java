package GameDemo.GUI;

import Collections.Lists.OrderedListADT;
import Game.API.Game;
import Game.Entities.Local;
import Game.Enumerations.LocalFilter;

import javax.swing.*;
import java.awt.*;

public class NetworkVisualizationPanel extends JPanel {

    private final Game game;

    public NetworkVisualizationPanel(Game game) {
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        OrderedListADT<Local> locals = game.listLocalsOrdered(LocalFilter.ID);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

}
