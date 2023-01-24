package GameDemo;

import Collections.Trees.AVLTree;
import Collections.Trees.AVLTreeADT;
import Game.API.Game;
import Game.API.GameImpl;
import Game.Entities.*;
import GameDemo.GUI.MainMenu;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Game game = new GameImpl();
        new MainMenu(game);

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

        game.addTeam(team1);
        game.addTeam(team2);

        Player player1 = new PlayerImpl(0, "João");
        Player player2 = new PlayerImpl(1, "Ana");
        Player player3 = new PlayerImpl(2, "Pedro");
        Player player4 = new PlayerImpl(3, "Maria");
        Player player5 = new PlayerImpl(4, "Rui");
        Player player6 = new PlayerImpl(5, "Sara");
        Player player7 = new PlayerImpl(6, "Joana");
        Player player8 = new PlayerImpl(7, "Ricardo");
        Player player9 = new PlayerImpl(8, "Miguel");
        Player player10 = new PlayerImpl(9, "Inês");
        Player player11 = new PlayerImpl(10, "João");

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        game.addPlayer(player5);
        game.addPlayer(player6);
        game.addPlayer(player7);
        game.addPlayer(player8);
        game.addPlayer(player9);
        game.addPlayer(player10);
        game.addPlayer(player11);


        System.out.println("*********************");

        game.addPlayerToTeam(player6, team2);

        game.addPlayerToTeam(player11, team1);

        place7.setOwner(player11);

        game.movePlayer(player6, place7);

        System.out.println("Player6 energy before acquiring place7: " + player6.getCurrentEnergy());

        System.out.println("Place7 energy before acquiring: " + place7.getEnergy());

        game.acquirePortal(player6, place7);

        System.out.println("Player6 energy after acquiring place7: " + player6.getCurrentEnergy());

        System.out.println("Place7 energy after acquiring: " + place7.getEnergy());


        game.movePlayer(player6, place8);

        System.out.println("PLayer6 energy before charging in place8: " + player6.getCurrentEnergy());

        game.chargePlayer(player6, place8);

        System.out.println("Player6 after charge in place8: " + player6.getCurrentEnergy());


        /*try {
            game.saveGameData("gameData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        AVLTreeADT<Player> players = new AVLTree<>();
        players.addElement(player1);
        players.addElement(player2);
        players.addElement(player3);
        players.addElement(player4);
        players.addElement(player5);
        players.addElement(player6);
        players.addElement(player7);
        players.addElement(player8);
        players.addElement(player9);
        players.addElement(player10);
        players.addElement(player11);
        System.out.println(players);
        System.out.println("Iterator");
        Iterator<Player> iterator = players.iteratorPostOrder();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        Set<Player> playersSet = new HashSet<>();
        playersSet.add(player1);
        playersSet.add(player2);
        playersSet.add(player3);
        playersSet.add(player4);
        playersSet.add(player1);
        playersSet.add(player2);
        playersSet.add(player3);
        playersSet.add(player4);
        playersSet.add(player5);
        playersSet.add(player6);
        playersSet.add(player7);
        playersSet.add(player8);
        playersSet.add(player9);
        playersSet.add(player10);
        playersSet.add(player11);
        System.out.println(playersSet);
        playersSet.remove(player1);
        System.out.println(playersSet);*/


    }
}
