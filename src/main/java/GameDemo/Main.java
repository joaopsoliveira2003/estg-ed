package GameDemo;

import Collections.Implementations.Graphs.NetworkADT;
import Collections.Implementations.Trees.LinkedHeap;
import GameAPI.Implementations.*;
import GameAPI.Interfaces.*;
import GameDemo.GUI.MainMenu;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        //MainMenu mainMenu = new MainMenu();

        Team team1 = new TeamImpl("Team 1");
        Team team2 = new TeamImpl("Team 2");

        Player player1 = new PlayerImpl(0, "João", team1 ,400, 1, 0);
        Player player2 = new PlayerImpl(1, "Ana", team1 ,400, 1, 0);
        Player player3 = new PlayerImpl(2, "Pedro", team2 ,400, 1, 0);
        Player player4 = new PlayerImpl(3, "Maria", team2 ,400, 1, 0);

        Portal place1 = new PortalImpl(0, "Torre dos Clérigos - Porto", 41.1456745, -8.6167864, 50, 100);
        Portal place2 = new PortalImpl(1, "Castelo de S.Jorge - Lisboa", 38.7136723, -9.1331109, 50, 100);
        Portal place3 = new PortalImpl(2, "Mosteiro da Batalha - Batalha", 39.657521, -8.8674718, 50, 100);
        Portal place4 = new PortalImpl(3, "Castelo de Bragança - Bragança", 41.8040229, -6.7499805, 50, 100);
        Portal place5 = new PortalImpl(4, "Sé Velha - Coimbra", 40.2087924, -8.4312746, 50, 100);
        Portal place6 = new PortalImpl(5, "Portugal dos Pequenitos - Coimbra", 40.2023015, -8.4365746, 50, 100);
        Portal place7 = new PortalImpl(7, "Dom Afonso Henriques - Guimarães", 41.446926, -8.2922936, 50, 100);

        Connector place8 = new ConnectorImpl(0, "Salinas Aveiro - Aveiro", 40.6448511, -8.6668641, 50, 5);
        Connector place9 = new ConnectorImpl(1, "Sé de Leiria", 39.74604335, -8.81005909, 50, 5);
        Connector place10 = new ConnectorImpl(2, "Universidade de Trás-os-Montes e Alto Douro - Vila Real", 41.2885442, -7.7412596, 50, 5);
        Connector place11 = new ConnectorImpl(3, "Instituto Politécnico da Guarda - Guarda", 40.53703093, -7.2762619, 50, 5);

        ExtendedNetwork<Place> network = new ExtendedNetwork<>();

        network.addVertex(place1);
        network.addVertex(place2);
        network.addVertex(place3);
        network.addVertex(place4);
        network.addVertex(place5);
        network.addVertex(place6);
        network.addVertex(place7);
        network.addVertex(place8);
        network.addVertex(place9);
        network.addVertex(place10);
        network.addVertex(place11);

        network.addEdge(place1, place8);
        network.addEdge(place8, place9);
        network.addEdge(place9, place10);
        network.addEdge(place10, place11);
        network.addEdge(place11, place7);

        System.out.println("Shortest Path");
        Iterator<Place> iterator = network.iteratorShortestPath(place1, place11);
        while (iterator.hasNext()) {
            Place place = iterator.next();
            System.out.println(place.getName());
        }

        System.out.println("\nShortest Path w/o portals");
        iterator = network.iteratorShortestPath(place1, place11, false);
        while (iterator.hasNext()) {
            Place place = iterator.next();
            System.out.println(place.getName());
        }

        System.out.println(network);
    }
}
