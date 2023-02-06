import Game.API.Game;
import Game.API.GameImpl;
import Game.Entities.Connector;
import Game.Entities.Local;
import Game.Entities.Portal;
import Game.Enumerations.SortLocals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

/*
 * Test for the Game class
 */
public class GameTest {
    Game game;

    @BeforeEach
    public void setUp() {
        game = new GameImpl();
    }

    @Test
    public void testAddPortal() {
        game.addPortal(0, "Portal 0", 40, 20, 100, 100);
        Iterator<Local> iterator = game.listLocalsOrdered(SortLocals.ID);
        Portal portal = (Portal) iterator.next();
        Assertions.assertEquals(0, portal.getID());
        Assertions.assertEquals("Portal 0", portal.getName());
        Assertions.assertEquals(40, portal.getLatitude());
        Assertions.assertEquals(20, portal.getLongitude());
        Assertions.assertEquals(100, portal.getEnergy());
        Assertions.assertEquals(100, portal.getMaxEnergy());
    }

    @Test
    public void testAddConnector() {
        game.addPortal(0, "Portal 0", 40, 20, 100, 100);
        game.addPortal(1, "Portal 1", 40, 20, 100, 100);
        game.addConnector(2, "Connector 0", 40, 20, 100, 2);
        Iterator<Local> iterator = game.listLocalsOrdered(SortLocals.ID);
        Portal portal0 = (Portal) iterator.next();
        Portal portal1 = (Portal) iterator.next();
        Connector connector = (Connector) iterator.next();
        Assertions.assertEquals(0, portal0.getID());
        Assertions.assertEquals(1, portal1.getID());
        Assertions.assertEquals("Connector 0", connector.getName());
        Assertions.assertEquals(40, connector.getLatitude());
        Assertions.assertEquals(20, connector.getLongitude());
        Assertions.assertEquals(100, connector.getEnergy());
        Assertions.assertEquals(2, connector.getCoolDownTime());
    }
}
