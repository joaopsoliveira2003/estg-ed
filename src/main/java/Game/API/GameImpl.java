package Game.API;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.IllegalArgumentException;
import Collections.Exceptions.NoSuchElementException;
import Collections.HashTables.HashMap;
import Collections.HashTables.MapADT;
import Collections.HashTables.SetADT;
import Collections.HashTables.HashSet;
import Collections.Lists.LinkedOrderedList;
import Game.CustomCollections.ExtendedNetworkADT;
import Game.CustomCollections.ExtendedNetwork;
import Game.Entities.*;
import Game.Enumerations.LocalFilter;
import Game.Enumerations.PlayerFilter;
import Game.Exceptions.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

public class GameImpl implements Game {

    private final ExtendedNetworkADT<Local> network;
    private final SetADT<Player> players;
    private final SetADT<Team> teams;
    private final MapADT<Player, Local> playerPositions;

    public GameImpl() {
        network = new ExtendedNetwork<>();
        players = new HashSet<>();
        teams = new HashSet<>();
        playerPositions = new HashMap<>();
    }

    // LOCALS AND ROUTES

    @Override
    public void addLocal(Local local) throws IllegalArgumentException, AlreadyExistsException {
        if (local == null) {
            throw new IllegalArgumentException("Local cannot be null");
        }
        if (network.containsVertex(local)) {
            throw new AlreadyExistsException("Local " + local.getName() + " already exists");
        }
        network.addVertex(local);
    }

    @Override
    public void updateLocal(Local oldLocal, Local newLocal) throws NoSuchLocalException, IllegalArgumentException {
        if (oldLocal == null || newLocal == null) {
            throw new IllegalArgumentException("Local cannot be null");
        }
        if (network.containsVertex(oldLocal)) {
            throw new NoSuchLocalException("Local " + oldLocal.getName() + " does not exist");
        }
        network.updateVertex(oldLocal, newLocal);
    }

    @Override
    public void removeLocal(Local local) throws NoSuchLocalException, IllegalArgumentException {
        if (local == null) {
            throw new IllegalArgumentException("Local cannot be null");
        }
        if (!network.containsVertex(local)) {
            throw new NoSuchLocalException("Local " + local.getName() + " does not exist");
        }
        network.removeVertex(local);
    }

    @Override
    public LinkedOrderedList<Local> listPlacesOrdered(LocalFilter filter) {
        LinkedOrderedList<Local> list = new LinkedOrderedList<>();
        for (Iterator<Local> it = network.iteratorVertexes(); it.hasNext(); ) {
            Local local = it.next();
            list.add(local);
        }
        return list;
    }

    @Override
    public void addRoute(Local local1, Local local2) throws NoSuchLocalException, IllegalArgumentException {
        if (local1 == null || local2 == null) {
            throw new IllegalArgumentException("Local cannot be null");
        }
        if (!network.containsVertex(local1) || !network.containsVertex(local2)) {
            throw new NoSuchLocalException("Local " + local1.getName() + " or " + local2.getName() + " does not exist");
        }
        network.addEdge(local1, local2);
    }

    @Override
    public void removeRoute(Local local1, Local local2) throws NoSuchLocalException, IllegalArgumentException {
        if (local1 == null || local2 == null) {
            throw new IllegalArgumentException("Local cannot be null");
        }
        if (!network.containsVertex(local1) || !network.containsVertex(local2)) {
            throw new NoSuchLocalException("Local " + local1.getName() + " or " + local2.getName() + " does not exist");
        }
        network.removeEdge(local1, local2);
    }

    @Override
    public Iterator<Local> getShortestPath(Local local1, Local local2) throws NoSuchLocalException, IllegalArgumentException {
        return network.iteratorShortestPath(local1, local2);
    }

    @Override
    public Iterator<Local> getShortestPath(Local vertex1, Local vertex2, boolean portals) throws NoSuchLocalException, IllegalArgumentException {
        return network.iteratorShortestPath(vertex1, vertex2, portals);
    }

    @Override
    public void exportShortestPath(Iterator<Local> path, String fileName) throws IOException, IllegalArgumentException {
        if (fileName == null) {
            throw new IllegalArgumentException("File name cannot be null");
        }
        JSONObject main = new JSONObject();
        JSONArray array = new JSONArray();
        while (path.hasNext()) {
            array.add(path.next().getName());
        }
        main.put("path", array);
        File file = new File(fileName);
        FileOutputStream stream = new FileOutputStream(file);
        stream.write(main.toJSONString().getBytes());
        stream.close();
    }

    @Override
    public void addPlayer(Player player) throws IllegalArgumentException, AlreadyExistsException {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (players.contains(player)) {
            throw new AlreadyExistsException("Player " + player.getName() + " already exists");
        }
        players.add(player);
    }

    @Override
    public void updatePlayer(Player oldPlayer, Player newPlayer) throws NoSuchPlayerException, IllegalArgumentException {
        if (oldPlayer == null || newPlayer == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (!players.contains(oldPlayer)) {
            throw new NoSuchPlayerException("Player " + oldPlayer.getName() + " does not exist");
        }
        players.remove(oldPlayer);
        try {
            playerPositions.remove(oldPlayer);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {}
        players.add(newPlayer);
    }

    @Override
    public void removePlayer(Player player) throws NoSuchPlayerException, IllegalArgumentException {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player.getName() + " does not exist");
        }
        players.remove(player);
        try {
            playerPositions.remove(player);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {}
    }

    @Override
    public void movePlayer(Player player, Local local) throws NoSuchPlayerException, NoSuchLocalException, IllegalArgumentException {
        if (player == null || local == null) {
            throw new IllegalArgumentException("Player or local cannot be null");
        }
        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player.getName() + " does not exist");
        }
        if (!network.containsVertex(local)) {
            throw new NoSuchLocalException("Local " + local.getName() + " does not exist");
        }
        playerPositions.put(player, local);
    }

    @Override
    public void addTeam(Team team) throws IllegalArgumentException, AlreadyExistsException {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        if (teams.contains(team)) {
            throw new AlreadyExistsException("Team " + team.getName() + " already exists");
        }
        teams.add(team);
    }

    @Override
    public void addPlayerToTeam(Player player, Team team) throws NoSuchPlayerException, NoSuchTeamException, IllegalArgumentException {
        if (player == null || team == null) {
            throw new IllegalArgumentException("Player or team cannot be null");
        }
        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player.getName() + " does not exist");
        }
        player.setTeam(team);
    }

    @Override
    public void removePlayerFromTeam(Player player, Team team) throws NoSuchPlayerException, NoSuchTeamException, IllegalArgumentException {
        if (player == null || team == null) {
            throw new IllegalArgumentException("Player or team cannot be null");
        }
        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player + " does not exist");
        }
        //player.setTeam(null);
    }

    @Override
    public LinkedOrderedList<Player> listPlayersOrdered(PlayerFilter filter) {
        LinkedOrderedList<Player> list = new LinkedOrderedList<>();
        for (Iterator<Player> it = players.iterator(); it.hasNext(); ) {
            Player player = it.next();
            list.add(player);
        }
        return list;
    }

    @Override
    public void chargePlayer(Player player, Local local) throws NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, CooldownNotOverException, IllegalArgumentException {
        if (player == null || local == null) {
            throw new IllegalArgumentException("Player or local cannot be null");
        }

        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player.getName() + " does not exist");
        }

        if (!network.containsVertex(local)) {
            throw new NoSuchLocalException("Local " + local.getName() + " does not exist");
        }

        try {
            if (playerPositions.get(player) != local) {
                throw new NoSuchElementException();
            }
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new InvalidLocalException("Player " + player.getName() + " is not in local " + local.getName() + "!");
        }

        if (local instanceof Connector) {
            //cast local to connector
            Connector connector = (Connector) local;
            if (connector.isCoolDownOver(player)) {
                //charge player with 10% of the energy of the connector
                player.addEnergy((int) (connector.getEnergy() * 0.1));
                connector.addLastInteraction(player);
            } else {
                throw new CooldownNotOverException("Cooldown for player " + player.getName() + " is not over!");
            }
        } else {
            throw new InvalidLocalException("Local " + local.getName() + " is not a connector!");
        }
    }

    @Override
    public void acquirePortal(Player player, Local local) throws NoSuchPlayerException, NoSuchLocalException, NoTeamException, NotEnoughEnergyException, AlreadyConqueredPortalException, IllegalArgumentException {
        if (player == null || local == null) {
            throw new IllegalArgumentException("Player or local cannot be null");
        }
        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player.getName() + " does not exist");
        }
        if (!network.containsVertex(local)) {
            throw new NoSuchLocalException("Local " + local.getName() + " does not exist");
        }
        try {
            if (playerPositions.get(player) != local) {
                throw new NoSuchElementException();
            }
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new InvalidLocalException("Player " + player.getName() + " is not in local " + local.getName() + "!");
        }
        //check if the player has a team
        if (player.getTeam() == null) {
            throw new NoTeamException("Player " + player.getName() + " does not have a team!");
        }
        if (local instanceof Portal) {
            Portal portal = (Portal) local;
            try {

                if (portal.getOwner().getTeam() != player.getTeam()) {
                    //equipa adversária
                    // in order to conquer a portal of a different team, the player needs to discharge the portal and then charge with 25% of the max energy of the portal otherwise the portal won't have an owner
                    if (player.getCurrentEnergy() >= portal.getEnergy()) {
                        //enough energy
                        portal.removeOwner();
                        player.removeEnergy(portal.getEnergy());
                        portal.removeEnergy(portal.getEnergy());

                        //now check if the player has enough energy to charge the portal with 25% of the max energy of the portal
                        if (player.getCurrentEnergy() >= (portal.getMaxEnergy() * 0.25)) {
                            //enough energy
                            portal.setOwner(player);
                            player.removeEnergy((int) (portal.getMaxEnergy() * 0.25));
                            portal.addEnergy((int) (portal.getMaxEnergy() * 0.25));
                        } else {
                            portal.removeEnergy(player.getCurrentEnergy());
                            player.removeEnergy(player.getCurrentEnergy());
                            //not enough energy
                            throw new NotEnoughEnergyException("Player " + player.getName() + " does not have enough energy to conquer the portal");
                        }
                    } else {
                        portal.removeEnergy(player.getCurrentEnergy());
                        player.removeEnergy(player.getCurrentEnergy());
                        //not enough energy
                        throw new NotEnoughEnergyException("Player " + player.getName() + " does not have enough energy to reset the portal");
                    }
                } else {
                    //mesma equipa
                    throw new AlreadyConqueredPortalException("Portal " + portal.getName() + " is already conquered by " + portal.getOwner());
                }
            } catch (NoAssociationException ignored) {
                //sem equipa

                //check if the player has enough energy to charge the portal with 25% of the max energy of the portal
                if (player.getCurrentEnergy() >= (portal.getMaxEnergy() * 0.25)) {
                    //enough energy
                    portal.setOwner(player);
                    player.removeEnergy((int) (portal.getMaxEnergy() * 0.25));
                    portal.addEnergy((int) (portal.getMaxEnergy() * 0.25));
                } else {
                    //not enough energy
                    throw new NotEnoughEnergyException("Player " + player.getName() + " does not have enough energy to conquer the portal");
                }
            }
        }
    }

    @Override
    public void chargePortal(Player player, Local local, int energy) throws NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, WrongLocationException, NoTeamException, NotEnoughEnergyException, NotConqueredPortalException, IllegalArgumentException {
        if (player == null || local == null) {
            throw new IllegalArgumentException("Player or local cannot be null");
        }
        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player.getName() + " does not exist");
        }
        if (!network.containsVertex(local)) {
            throw new NoSuchLocalException("Local " + local.getName() + " does not exist");
        }
        try {
            if (playerPositions.get(player) != local) {
                throw new NoSuchElementException();
            }
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new WrongLocationException("Player " + player.getName() + " is not in local " + local.getName() + "!");
        }
        //check if the portal has a owner and if yes get the team of the owner and compare with the team of the player
        if (local instanceof Portal) {
            Portal portal = (Portal) local;
            try {
                if (portal.getOwner().getTeam() != player.getTeam()) {
                    throw new NotConqueredPortalException("Portal " + portal.getName() + " is not conquered by " + player.getName());
                }
            } catch (NoAssociationException ignored) {
                throw new NotConqueredPortalException("Portal " + portal.getName() + " is not conquered by " + player.getName());
            }
            //check if the player has enough energy to charge the portal
            if (player.getCurrentEnergy() >= energy) {
                //enough energy
                player.removeEnergy(energy);
                portal.addEnergy(energy);
            } else {
                //not enough energy
                throw new NotEnoughEnergyException("Player " + player.getName() + " does not have enough energy to charge the portal");
            }
        } else {
            throw new InvalidLocalException("Local " + local.getName() + " is not a portal!");
        }

    }

    @Override
    public void loadGameData(String fileName) throws IOException, IllegalArgumentException {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray teamsArray = (JSONArray) jsonObject.get("teams");
            Iterator<JSONObject> iterator = teamsArray.iterator();
            while (iterator.hasNext()) {
                JSONObject team = iterator.next();
                String name = (String) team.get("name");
                Team t = new TeamImpl(name);
                addTeam(t);
            }
            JSONArray playersArray = (JSONArray) jsonObject.get("players");
            iterator = playersArray.iterator();
            while (iterator.hasNext()) {
                JSONObject player = iterator.next();
                int id = ((Long) player.get("id")).intValue();
                String name = (String) player.get("name");
                String team = (String) player.get("team");
                int energy = ((Long) player.get("energy")).intValue();
                Player p = new PlayerImpl(id, name);
                p.addEnergy(energy);
                addPlayer(p);
            }
            JSONArray localsArray = (JSONArray) jsonObject.get("locals");
            iterator = localsArray.iterator();
            while (iterator.hasNext()) {
                JSONObject local = iterator.next();
                int id = ((Long) local.get("id")).intValue();
                String type = (String) local.get("type");
                String name = (String) local.get("name");
                int energy = ((Long) local.get("energy")).intValue();
                Double latitude = (Double) local.get("latitude");
                Double longitude = (Double) local.get("longitude");
                JSONObject gameSettings = (JSONObject) jsonObject.get("gameSettings");
                int maxEnergy = ((Long) gameSettings.get("maxEnergy")).intValue();
                int cooldown = ((Long) gameSettings.get("cooldown")).intValue();
                JSONObject ownership = (JSONObject) gameSettings.get("ownership");
                String owner = (String) ownership.get("owner");

                if (type.equals("Connector")) {
                    Connector connector = new ConnectorImpl(id, name, latitude, longitude, energy, cooldown);
                    network.addVertex(connector);
                } else if (type.equals("portal")) {
                    Portal portal = new PortalImpl(id, name, latitude, longitude, energy, maxEnergy);
                    network.addVertex(portal);
                }
            }
            JSONArray routesArray = (JSONArray) jsonObject.get("routes");
            iterator = routesArray.iterator();
            while (iterator.hasNext()) {
                JSONObject route = iterator.next();
                String from = (String) route.get("from");
                String to = (String) route.get("to");
            }
        } catch (ParseException ignored) {
            throw new IOException("Error parsing file");
        }
    }

    @Override
    public void saveGameData(String fileName) throws IOException, IllegalArgumentException {
        JSONObject json = new JSONObject();
        JSONArray localsArray = new JSONArray();
        Iterator<Local> iterator = network.iteratorVertexes();
        while (iterator.hasNext()) {
            Local place = iterator.next();
            localsArray.add(place.getJSON());
        }
        json.put("locals", localsArray);
        JSONArray routesArray = new JSONArray();
        iterator = network.iteratorRoutes();
        while (iterator.hasNext()) {
            JSONObject route = new JSONObject();
            route.put("from", iterator.next().getID());
            route.put("to", iterator.next().getID());
            routesArray.add(route);
        }
        json.put("routes", routesArray);

        JSONArray playersArray = new JSONArray();
        Iterator<Player> iterator1 = players.iterator();
        while (iterator1.hasNext()) {
            Player player = iterator1.next();
            playersArray.add(player.toJSON());
        }
        json.put("players", playersArray);

        JSONArray teamsArray = new JSONArray();
        Iterator<Team> iterator2 = teams.iterator();
        while (iterator2.hasNext()) {
            Team team = iterator2.next();
            teamsArray.add(team.toJSON());
        }
        json.put("teams", teamsArray);

        //write to a file with fileName
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        //write to text file
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(json.toJSONString().getBytes());
        fileOutputStream.close();
    }
}
