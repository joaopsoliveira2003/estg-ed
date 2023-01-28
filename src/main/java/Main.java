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

        new PlayerDemo(game);

        new MainMenu(game);


        /*boolean load = true;

        if (load) {
            try {
                game.loadGameData("gameData.json");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            game.addPortal(7, "Dom Afonso Henriques - Guimarães", 41.446926, -8.2922936, 90, 130);
            game.addPortal(1, "Castelo de S.Jorge - Lisboa", 38.7136723, -9.1331109, 190, 200);
            game.addPortal(4, "Castelo de Bragança - Bragança", 41.8040229, -6.7499805, 75, 100);
            game.addPortal(0, "Torre dos Clérigos - Porto", 41.1456745, -8.6167864, 180, 200);
            game.addPortal(3, "Mosteiro da Batalha - Batalha", 39.657521, -8.8674718, 100, 250);
            game.addConnector(10, "Universidade de Trás-os-Montes e Alto Douro - Vila Real", 41.2885442, -7.7412596, 50, 2);
            game.addPortal(2, "Castelo de S.Jorge - Lisboa", 38.7136723, -9.1331109, 190, 200);
            game.addConnector(11, "Instituto Politécnico da Guarda - Guarda", 40.53703093, -7.2762619, 50, 3);
            game.addPortal(5, "Sé Velha - Coimbra", 40.2087924, -8.4312746, 90, 100);
            game.addPortal(6, "Portugal dos Pequenitos - Coimbra", 40.2023015, -8.4365746, 75, 125);

            game.addConnector(9, "Sé de Leiria", 39.74604335, -8.81005909, 50, 3);
            game.addConnector(8, "Salinas Aveiro - Aveiro", 40.6448511, -8.6668641, 75, 5);

            game.addRoute(0, 1);
            game.addRoute(1, 8);
            game.addRoute(8, 9);
            game.addRoute(8, 10);
            game.addRoute(10, 6);


            game.addTeam("Sparks");
            game.addTeam("Giants");

            game.addPlayer(0, "João", "Sparks");
            game.addPlayer(1, "Ana", "Giants");
            game.addPlayer(2, "Mariana", "Giants");
            game.addPlayer(3, "Pedro", "Giants");
            game.addPlayer(4, "Maria", "Sparks");
            game.addPlayer(5, "Rui", "Sparks");
            game.addPlayer(6, "Sara", "None");
            game.addPlayer(7, "Joana", "Giants");
            game.addPlayer(8, "Ricardo", "None");
            game.addPlayer(9, "Miguel", "Giants");
            game.addPlayer(10, "Inês", "Giants");
            game.addPlayer(11, "João", "None");
        }

        System.out.println("Locals:");
        game.listLocalsOrdered(SortLocals.ID).forEachRemaining(System.out::println);

        System.out.println("Players:");
        game.listPlayersOrdered(SortPlayers.ID).forEachRemaining(System.out::println);*/

        /*try {
            game.saveGameData("gameData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
