import Game.API.Game;
import Game.API.GameImpl;
import GameManagement.MainMenu;
import PlayerSimulator.PlayerDemo;

/**
 * Main class is the entry point of the program.
 */
public class Main {
    public static void main(String[] args) {

        Game game = new GameImpl();

        new MainMenu(game);

        new PlayerDemo(game);

    }
}
