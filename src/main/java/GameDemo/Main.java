package GameDemo;

import Game.API.Game;
import Game.API.GameImpl;
import Game.Entities.*;
import GameDemo.GUI.MainMenu;

public class Main {
    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();
        Game game = new GameImpl();

        Portal place1 = new PortalImpl(0, "Torre dos Clérigos - Porto", 41.1456745, -8.6167864, 180, 200);
        Portal place2 = new PortalImpl(1, "Castelo de S.Jorge - Lisboa", 38.7136723, -9.1331109, 190, 200);
        Portal place3 = new PortalImpl(2, "Mosteiro da Batalha - Batalha", 39.657521, -8.8674718, 100, 250);
        Portal place4 = new PortalImpl(3, "Castelo de Bragança - Bragança", 41.8040229, -6.7499805, 75, 100);
        Portal place5 = new PortalImpl(4, "Sé Velha - Coimbra", 40.2087924, -8.4312746, 90, 100);
        Portal place6 = new PortalImpl(5, "Portugal dos Pequenitos - Coimbra", 40.2023015, -8.4365746, 75, 125);
        Portal place7 = new PortalImpl(7, "Dom Afonso Henriques - Guimarães", 41.446926, -8.2922936, 90, 130);

        Connector place8 = new ConnectorImpl(0, "Salinas Aveiro - Aveiro", 40.6448511, -8.6668641, 75, 5);
        Connector place9 = new ConnectorImpl(1, "Sé de Leiria", 39.74604335, -8.81005909, 50, 3);
        Connector place10 = new ConnectorImpl(2, "Universidade de Trás-os-Montes e Alto Douro - Vila Real", 41.2885442, -7.7412596, 50, 2);
        Connector place11 = new ConnectorImpl(3, "Instituto Politécnico da Guarda - Guarda", 40.53703093, -7.2762619, 50, 3);

        game.addPlace(place1);
        game.addPlace(place2);
        game.addPlace(place3);
        game.addPlace(place4);
        game.addPlace(place5);
        game.addPlace(place6);
        game.addPlace(place7);

        game.addPlace(place8);
        game.addPlace(place9);
        game.addPlace(place10);
        game.addPlace(place11);

        game.addRoute(place1, place8);
        game.addRoute(place8, place9);
        game.addRoute(place9, place10);
        game.addRoute(place10, place11);
        game.addRoute(place11, place7);


        Team team1 = new TeamImpl("Sparks");
        Team team2 = new TeamImpl("Giants");

        Player player1 = new PlayerImpl(0, "João", team1 ,400, 0, 0);
        Player player2 = new PlayerImpl(1, "Ana", team1 ,400, 0, 0);
        Player player3 = new PlayerImpl(2, "Pedro", team2 ,400, 0, 0);
        Player player4 = new PlayerImpl(3, "Maria", team2 ,400, 0, 0);
    }
}
