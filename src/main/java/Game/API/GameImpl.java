package Game.API;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.IllegalArgumentException;
import Collections.Exceptions.NoSuchElementException;
import Collections.HashTables.HashMap;
import Collections.HashTables.MapADT;
import Collections.HashTables.SetADT;
import Collections.HashTables.HashSet;
import Collections.Lists.LinkedOrderedList;
import Collections.Lists.OrderedListADT;
import Collections.Trees.HeapADT;
import Collections.Trees.LinkedHeap;
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
import java.util.Objects;

public class GameImpl implements Game {

    private final ExtendedNetworkADT<Local> network;
    private final MapADT<Integer, Local> locals;
    private final MapADT<Integer, Player> players;
    private final MapADT<String, Team> teams;
    private final MapADT<Integer, Local> playerPositions;

    public GameImpl() {
        network = new ExtendedNetwork<>();
        locals = new HashMap<>();
        players = new HashMap<>();
        teams = new HashMap<>();
        playerPositions = new HashMap<>();
    }

    protected void validateInteger(int value, String name) throws IllegalArgumentException {
        if (value < 0) {
            throw new IllegalArgumentException(name + " cannot be negative.");
        }
    }

    protected void validateString(String value, String name) throws IllegalArgumentException {
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new IllegalArgumentException(name + " cannot be null, empty or blank.");
        }
    }


    @Override
    public void addPortal(int id, String name, double latitude, double longitude, int energy, int maxEnergy) throws IllegalArgumentException, AlreadyExistsException {
        validateInteger(id, "ID");
        if (locals.containsKey(id)) {
            throw new AlreadyExistsException("Portal with id " + id + " already exists.");
        }
        Portal portal = new PortalImpl(id, name, latitude, longitude, energy, maxEnergy);
        locals.put(portal.getID(), portal);
        network.addVertex(portal);
    }

    @Override
    public void addConnector(int id, String name, double latitude, double longitude, int energy, int cooldown) throws IllegalArgumentException, AlreadyExistsException {
        validateInteger(id, "ID");
        if (locals.containsKey(id)) {
            throw new AlreadyExistsException("Connector with id " + id + " already exists.");
        }
        Connector connector = new ConnectorImpl(id, name, latitude, longitude, energy, cooldown);
        locals.put(connector.getID(), connector);
        network.addVertex(connector);
    }

    @Override
    public void updatePortal(int id, String name, double latitude, double longitude, int energy, int maxEnergy) throws IllegalArgumentException, NoSuchLocalException {
        validateInteger(id, "ID");
        if (!locals.containsKey(id)) {
            throw new NoSuchLocalException("Portal with id " + id + " does not exist.");
        }
        //Local local = locals.get(id);
        Portal portal = (Portal) locals.get(id);
        portal.setName(name);
        portal.setLatitude(latitude);
        portal.setLongitude(longitude);
        portal.setEnergy(energy);
        portal.setMaxEnergy(maxEnergy);
        //network.updateVertex(portal);
        //TODO: check if this is working if so remove updateVertex from ExtendedNetwork
    }

    @Override
    public void updateConnector(int id, String name, double latitude, double longitude, int energy, int cooldown) throws IllegalArgumentException, NoSuchLocalException {
        validateInteger(id, "ID");
        if (!locals.containsKey(id)) {
            throw new NoSuchLocalException("Connector with id " + id + " does not exist.");
        }
        //Local local = locals.get(id);
        Connector connector = (Connector) locals.get(id);
        connector.setName(name);
        connector.setLatitude(latitude);
        connector.setLongitude(longitude);
        connector.setEnergy(energy);
        connector.setCoolDownTime(cooldown);
        //network.updateVertex(connector);
        //TODO: check if this is working if so remove updateVertex from ExtendedNetwork
    }

    @Override
    public void removePortal(int id) throws IllegalArgumentException, NoSuchLocalException {
        validateInteger(id, "ID");
        if (!locals.containsKey(id)) {
            throw new NoSuchLocalException("Portal with id " + id + " does not exist.");
        }
        Local local = locals.get(id);
        network.removeVertex(local);
        locals.remove(id);
    }

    @Override
    public void removeConnector(int id) throws IllegalArgumentException, NoSuchLocalException {
        validateInteger(id, "ID");
        if (!locals.containsKey(id)) {
            throw new NoSuchLocalException("Connector with id " + id + " does not exist.");
        }
        Local local = locals.get(id);
        network.removeVertex(local);
        locals.remove(id);
    }

    @Override
    public Iterator<Local> listLocalsOrdered(LocalFilter filter) {
        //TODO: implement sorting
        OrderedListADT<Local> orderedList = new LinkedOrderedList<>();
        Iterator<Local> iterator = network.iteratorVertexes();
        while (iterator.hasNext()) {
            Local local = iterator.next();
            orderedList.add(local);
        }
        return orderedList.iterator();
    }

    @Override
    public void addRoute(int id1, int id2) throws IllegalArgumentException, NoSuchLocalException {
        validateInteger(id1, "ID1");
        if (!locals.containsKey(id1)) {
            throw new NoSuchLocalException("Local with id " + id1 + " does not exist.");
        }
        validateInteger(id2, "ID2");
        if (!locals.containsKey(id2)) {
            throw new NoSuchLocalException("Local with id " + id2 + " does not exist.");
        }
        Local local1 = locals.get(id1);
        Local local2 = locals.get(id2);
        network.addEdge(local1, local2);
    }

    @Override
    public void removeRoute(int id1, int id2) throws IllegalArgumentException, NoSuchLocalException {
        validateInteger(id1, "ID1");
        if (!locals.containsKey(id1)) {
            throw new NoSuchLocalException("Local with id " + id1 + " does not exist.");
        }
        validateInteger(id2, "ID2");
        if (!locals.containsKey(id2)) {
            throw new NoSuchLocalException("Local with id " + id2 + " does not exist.");
        }
        Local local1 = locals.get(id1);
        Local local2 = locals.get(id2);
        network.removeEdge(local1, local2);
    }

    @Override
    public Iterator<Local> getShortestPath(Local local1, Local local2) throws IllegalArgumentException, NoSuchLocalException {
        if (local1 == null || local2 == null) {
            throw new IllegalArgumentException("Local cannot be null.");
        }
        if (!locals.containsValue(local1)) {
            throw new NoSuchLocalException("Local " + local1.getName() + " does not exist.");
        }
        if (!locals.containsValue(local2)) {
            throw new NoSuchLocalException("Local " + local2.getName() + " does not exist.");
        }
        return network.iteratorShortestPath(local1, local2);
    }

    @Override
    public Iterator<Local> getShortestPath(Local vertex1, Local vertex2, boolean portals) throws IllegalArgumentException, NoSuchLocalException {
        return network.iteratorShortestPath(vertex1, vertex2, portals);
    }

    @Override
    public void exportShortestPath(Iterator<Local> iterator, String fileName) throws IllegalArgumentException, IOException {
        /*if (iterator == null) {
            throw new IllegalArgumentException("Iterator cannot be null.");
        }
        if (fileName == null) {
            throw new IllegalArgumentException("File name cannot be null.");
        }
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Iterator is empty.");
        }
        JSONObject json = new JSONObject();

        JSONArray array = new JSONArray();
        while (iterator.hasNext()) {
            Local local = iterator.next();
            JSONObject localJson = new JSONObject();
            localJson.put("id", local.getId());
            localJson.put("name", local.getName());
            localJson.put("latitude", local.getLatitude());
            localJson.put("longitude", local.getLongitude());
            array.add(localJson);
        }
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (PrintWriter writer = new PrintWriter(file)) {
            while (iterator.hasNext()) {
                Local local = iterator.next();
                writer.println(local.getName());
            }
        }*/
    }

    @Override
    public void addPlayer(int id, String name, String team) throws IllegalArgumentException, AlreadyExistsException, NoSuchTeamException {
        validateInteger(id, "ID");
        if (players.containsKey(id)) {
            throw new AlreadyExistsException("Player with id " + id + " already exists.");
        }
        validateString(team, "Team");
        if (team.equals("None")) {
            players.put(id, new PlayerImpl(id, name));
            return;
        }
        if (!teams.containsKey(team)) {
            throw new NoSuchTeamException("Team " + team + " does not exist.");
        }
        players.put(id, new PlayerImpl(id, name, teams.get(team)));
    }

    @Override
    public void updatePlayer(int id, String name, String team) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException {
        validateInteger(id, "ID");
        if (!players.containsKey(id)) {
            throw new NoSuchPlayerException("Player with id " + id + " does not exist.");
        }
        validateString(team, "Team");
        if (!teams.containsKey(team)) {
            throw new NoSuchTeamException("Team " + team + " does not exist.");
        }
        Player player = players.get(id);
        player.setName(name);
        player.setTeam(teams.get(team));
    }

    @Override
    public void removePlayer(int id) throws IllegalArgumentException, NoSuchPlayerException {
        validateInteger(id, "ID");
        if (!players.containsKey(id)) {
            throw new NoSuchPlayerException("Player with id " + id + " does not exist.");
        }
        players.remove(id);
    }

    @Override
    public void movePlayer(int player, int local) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        validateInteger(local, "Local");
        if (!locals.containsKey(local)) {
            throw new NoSuchLocalException("Local with id " + local + " does not exist.");
        }
        playerPositions.put(player, locals.get(local));
    }

    @Override
    public void addTeam(String name) throws IllegalArgumentException, AlreadyExistsException {
        if (teams.containsKey(name)) {
            throw new AlreadyExistsException("Team " + name + " already exists.");
        }
        Team team = new TeamImpl(name);
        teams.put(name, team);
    }

    @Override
    public void addPlayerToTeam(int player, String team) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        validateString(team, "Team");
        if (!teams.containsKey(team)) {
            throw new NoSuchTeamException("Team " + team + " does not exist.");
        }
        players.get(player).setTeam(teams.get(team));
    }

    @Override
    public void removePlayerFromTeam(int player, String team) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException, NoTeamException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        validateString(team, "Team");
        if (!teams.containsKey(team)) {
            throw new NoSuchTeamException("Team " + team + " does not exist.");
        }
        try {
            players.get(player).removeTeam();
        } catch (NoTeamException exception) {
            throw new NoTeamException(exception.getMessage());
        }
    }

    @Override
    public LinkedOrderedList<Player> listPlayersOrdered(PlayerFilter filter) {
        //TODO: implement sorting
        LinkedOrderedList<Player> list = new LinkedOrderedList<>();
        for (Player player : players.getValues()) {
            list.add(player);
        }
        return list;
    }

    @Override
    public void chargePlayer(int player, int local) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, WrongLocationException, CooldownNotOverException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        Player playerObject = players.get(player);
        validateInteger(local, "Local");
        if (!locals.containsKey(local)) {
            throw new NoSuchLocalException("Local with id " + local + " does not exist.");
        }
        if (!(locals.get(local) instanceof Connector)) {
            throw new InvalidLocalException("Local with id " + local + " is not a connector.");
        }
        Connector localObject = (Connector) locals.get(local);
        if (playerPositions.get(player) != localObject) {
            throw new WrongLocationException("Player is not at the local.");
        }
        if (localObject.isCoolDownOver(playerObject)) {
            playerObject.addEnergy((int) (localObject.getEnergy() * 0.1));
            localObject.addLastInteraction(playerObject);
        } else {
            throw new CooldownNotOverException("Cooldown is not over.");
        }
    }

    @Override
    public void acquirePortal(int player, int local) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, NoTeamException, WrongLocationException, NotEnoughEnergyException, AlreadyConqueredPortalException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        Player playerObject = players.get(player);
        validateInteger(local, "Local");
        if (!locals.containsKey(local)) {
            throw new NoSuchLocalException("Local with id " + local + " does not exist.");
        }
        if (!(locals.get(local) instanceof Portal)) {
            throw new InvalidLocalException("Local with id " + local + " is not a portal.");
        }
        Portal portalObject = (Portal) locals.get(local);
        try {
            if (playerObject.getTeam() == portalObject.getOwner().getTeam()) {
                throw new AlreadyConqueredPortalException("Portal is already owned by a player of the same team.");
            }
        } catch (NoAssociationException exception) {
            throw new NoTeamException(exception.getMessage());
        }
        if (playerPositions.get(player) != portalObject) {
            throw new WrongLocationException("Player is not at the local.");
        }
        //TODO: optimize this
        try {
            if (portalObject.getOwner().getTeam() != playerObject.getTeam()) {
                //equipa adversária
                // in order to conquer a portal of a different team, the player needs to discharge the portal and then charge with 25% of the max energy of the portal otherwise the portal won't have an owner
                if (playerObject.getCurrentEnergy() >= portalObject.getEnergy()) {
                    //enough energy
                    portalObject.removeOwner();
                    playerObject.removeEnergy(portalObject.getEnergy());
                    portalObject.removeEnergy(portalObject.getEnergy());

                    //now check if the player has enough energy to charge the portal with 25% of the max energy of the portal
                    if (playerObject.getCurrentEnergy() >= (portalObject.getMaxEnergy() * 0.25)) {
                        //enough energy
                        portalObject.setOwner(playerObject);
                        playerObject.removeEnergy((int) (portalObject.getMaxEnergy() * 0.25));
                        portalObject.addEnergy((int) (portalObject.getMaxEnergy() * 0.25));
                    } else {
                        portalObject.removeEnergy(playerObject.getCurrentEnergy());
                        playerObject.removeEnergy(playerObject.getCurrentEnergy());
                        //not enough energy
                        throw new NotEnoughEnergyException("Player " + playerObject.getName() + " does not have enough energy to conquer the portal");
                    }
                } else {
                    portalObject.removeEnergy(playerObject.getCurrentEnergy());
                    playerObject.removeEnergy(playerObject.getCurrentEnergy());
                    //not enough energy
                    throw new NotEnoughEnergyException("Player " + playerObject.getName() + " does not have enough energy to reset the portal");
                }
            } else {
                //mesma equipa
                throw new AlreadyConqueredPortalException("Portal " + portalObject.getName() + " is already conquered by " + portalObject.getOwner().getName());
            }
        } catch (NoAssociationException ignored) {
            //sem equipa

            //check if the player has enough energy to charge the portal with 25% of the max energy of the portal
            if (playerObject.getCurrentEnergy() >= (portalObject.getMaxEnergy() * 0.25)) {
                //enough energy
                portalObject.setOwner(playerObject);
                playerObject.removeEnergy((int) (portalObject.getMaxEnergy() * 0.25));
                portalObject.addEnergy((int) (portalObject.getMaxEnergy() * 0.25));
            } else {
                //not enough energy
                throw new NotEnoughEnergyException("Player " + playerObject.getName() + " does not have enough energy to conquer the portal");
            }
        }
    }

    @Override
    public void chargePortal(int player, int local, int energy) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, WrongLocationException, NoTeamException, NotEnoughEnergyException, NotConqueredPortalException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        Player playerObject = players.get(player);
        validateInteger(local, "Local");
        if (!locals.containsKey(local)) {
            throw new NoSuchLocalException("Local with id " + local + " does not exist.");
        }
        if (!(locals.get(local) instanceof Portal)) {
            throw new InvalidLocalException("Local with id " + local + " is not a portal.");
        }
        Portal portalObject = (Portal) locals.get(local);
        if (playerPositions.get(player) != portalObject) {
            throw new WrongLocationException("Player is not at the local.");
        }
        try {
            if (portalObject.getOwner().getTeam() != playerObject.getTeam()) {
                throw new NotConqueredPortalException("Portal is not owned by a player of the same team.");
            }
        } catch (NoAssociationException exception) {
            throw new NoTeamException(exception.getMessage());
        }
        if (playerObject.getCurrentEnergy() >= energy) {
            playerObject.removeEnergy(energy);
            portalObject.addEnergy(energy);
        } else {
            throw new NotEnoughEnergyException("Player does not have the specified energy to charge the portal.");
        }
    }

    @Override
    public void loadGameData(String fileName) throws IllegalArgumentException, IOException {
        //TODO: clear data structures before adding?
        validateString(fileName, "File name");
        try {
            JSONObject json = null;
            try {
                json = (JSONObject) new JSONParser().parse(new FileReader(fileName));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            // TEAMS

            JSONArray teamsArray = (JSONArray) json.get("teams");
            Iterator<JSONObject> iterator = teamsArray.iterator();
            while (iterator.hasNext()) {
                addTeam((String) iterator.next().get("name"));
            }

            // PLAYERS

            JSONArray playersArray = (JSONArray) json.get("players");
            iterator = playersArray.iterator();
            while (iterator.hasNext()) {
                JSONObject player = iterator.next();
                int id = ((Long) player.get("id")).intValue();
                String name = (String) player.get("name");
                String team = (String) player.get("team");
                int energy = ((Long) player.get("currentEnergy")).intValue();
                int experience = ((Long) player.get("experiencePoints")).intValue();
                addPlayer(id, name, team);
                Player playerObject = players.get(id);
                playerObject.addEnergy(energy);
                playerObject.addExperiencePoints(experience);
                if (!team.equals("None")) {
                    addPlayerToTeam(id, team);
                }
            }

            // LOCALS

            JSONArray localsArray = (JSONArray) json.get("locals");
            iterator = localsArray.iterator();
            while (iterator.hasNext()) {
                JSONObject local = iterator.next();
                int id = ((Long) local.get("id")).intValue();
                String type = (String) local.get("type");
                String name = (String) local.get("name");
                JSONObject coordinates = (JSONObject) local.get("coordinates");
                Double latitude = (Double) coordinates.get("latitude");
                Double longitude = (Double) coordinates.get("longitude");
                JSONObject gameSettings = (JSONObject) local.get("gameSettings");
                int energy = ((Long) gameSettings.get("energy")).intValue();

                if (type.equals("Connector")) {
                    int cooldown = ((Long) gameSettings.get("cooldown")).intValue();
                    addConnector(id, name, latitude, longitude, energy, cooldown);
                } else if (type.equals("Portal")) {
                    int maxEnergy = ((Long) gameSettings.get("maxEnergy")).intValue();
                    JSONObject ownership = (JSONObject) gameSettings.get("ownership");
                    int owner = ((Long) ownership.get("player")).intValue();
                    addPortal(id, name, latitude, longitude, energy, maxEnergy);
                    Portal portalObject = (Portal) locals.get(id);
                    if (owner != -1) {
                        portalObject.setOwner(players.get(owner));
                    }
                }
            }

            // ROUTES

            JSONArray routesArray = (JSONArray) json.get("routes");
            iterator = routesArray.iterator();
            while (iterator.hasNext()) {
                JSONObject route = iterator.next();
                int from = ((Long) route.get("from")).intValue();
                int to = ((Long) route.get("to")).intValue();
                addRoute(from, to);
            }
        } catch (Exception exception) {
            throw new IOException(exception.getMessage());
        }
    }

    @Override
    public void saveGameData(String fileName) throws IllegalArgumentException, IOException {
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
        for (Player player : players.getValues()) {
            playersArray.add(player.toJSON());
        }
        json.put("players", playersArray);

        JSONArray teamsArray = new JSONArray();
        for (Team team : teams.getValues()) {
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
