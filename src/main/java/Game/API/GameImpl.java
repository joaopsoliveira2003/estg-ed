package Game.API;

import Collections.Exceptions.IllegalArgumentException;
import Collections.HashTables.HashMap;
import Collections.HashTables.MapADT;
import Collections.Lists.DoublyLinkedOrderedList;
import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.OrderedListADT;
import Game.CustomCollections.ExtendedNetworkADT;
import Game.CustomCollections.ExtendedNetwork;
import Game.Entities.*;
import Game.Enumerations.SortLocals;
import Game.Enumerations.SortPlayers;
import Game.Exceptions.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

import static Game.Utilities.Validations.validateInteger;
import static Game.Utilities.Validations.validateString;

/**
 * GameImpl class implements the Game interface.
 */
public class GameImpl implements Game {

    public static SortLocals sortLocals = SortLocals.ID;
    public static SortPlayers sortPlayers = SortPlayers.ID;

    private final ExtendedNetworkADT<Local> network;
    private final MapADT<Integer, Local> locals;
    private final MapADT<Integer, Player> players;
    private final MapADT<String, Team> teams;
    private final MapADT<Integer, Local> playerPositions;

    private int movePlayerPoints;
    private int acquirePortalPoints;
    private int chargePortalPoints;
    private int chargePlayerPoints;

    public GameImpl() {
        network = new ExtendedNetwork<>();
        locals = new HashMap<>();
        players = new HashMap<>();
        teams = new HashMap<>();
        playerPositions = new HashMap<>();
        movePlayerPoints = 5;
        acquirePortalPoints = 250;
        chargePortalPoints = 100;
        chargePlayerPoints = 15;
    }

    // LOCALS MANAGEMENT

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
        Local local = locals.get(id);
        local.setName(name);
        local.setLatitude(latitude);
        local.setLongitude(longitude);
        ((Portal) local).setMaxEnergy(maxEnergy);
        local.setEnergy(energy);
    }

    @Override
    public void updateConnector(int id, String name, double latitude, double longitude, int energy, int cooldown) throws IllegalArgumentException, NoSuchLocalException {
        validateInteger(id, "ID");
        if (!locals.containsKey(id)) {
            throw new NoSuchLocalException("Connector with id " + id + " does not exist.");
        }
        Connector connector = (Connector) locals.get(id);
        connector.setName(name);
        connector.setLatitude(latitude);
        connector.setLongitude(longitude);
        connector.setEnergy(energy);
        connector.setCoolDownTime(cooldown);
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
    public Iterator<Local> listLocalsOrdered(SortLocals sort) {
        sortLocals = sort;
        OrderedListADT<Local> orderedList = new DoublyLinkedOrderedList<>();
        for (Local local : locals.getValues()) {
            orderedList.add(local);
        }
        return orderedList.iterator();
    }

    @Override
    public void loadLocals(String fileName) throws IllegalArgumentException, IOException {
        try {
            JSONObject json = loadFile(fileName);

            // LOCALS
            loadLocals((JSONArray) json.get("locals"));

        } catch (Exception exception) {
            throw new IOException(exception.getMessage());
        }
    }

    @Override
    public void exportLocals(String fileName) throws IllegalArgumentException, IOException {
        validateString(fileName, "File name");

        JSONObject json = new JSONObject();

        json.put("locals", saveLocals());

        writeFile(fileName, json);
    }

    // ROUTES MANAGEMENT

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
    public void loadRoutesLocals(String fileName) throws IllegalArgumentException, IOException {
        try {
            JSONObject json = loadFile(fileName);

            // LOCALS
            loadLocals((JSONArray) json.get("locals"));

            // ROUTES
            loadRoutes((JSONArray) json.get("routes"));

        } catch (Exception exception) {
            throw new IOException(exception.getMessage());
        }
    }

    @Override
    public void exportRoutesLocals(String fileName) throws IllegalArgumentException, IOException {
        validateString(fileName, "File name");

        JSONObject json = new JSONObject();

        json.put("locals", saveLocals());

        json.put("routes", saveRoutes());

        writeFile(fileName, json);
    }

    // PLAYERS MANAGEMENT

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
        Player player = players.get(id);
        player.setName(name);
        if (!teams.containsKey(team)) {
            if (team.equals("None")) {
                try {
                    player.removeTeam();
                } catch (NoAssociationException ignored) {}
            } else {
                throw new NoSuchTeamException("Team " + team + " does not exist.");
            }
        } else {
            try {
                Team currentTeam = players.get(id).getTeam();
                if (currentTeam.getName().equals(team)) {
                    throw new IllegalArgumentException("Player is already in team " + team + ".");
                }
            } catch (NoAssociationException ignored) {}
            player.setTeam(teams.get(team));
        }
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
        if (playerPositions.containsKey(player)) {
            Local oldLocal = playerPositions.get(player);
            if (oldLocal.getID() == local) {
                throw new IllegalArgumentException("Player is already in local " + local + ".");
            } else {
                playerPositions.remove(player);
            }
        }
        playerPositions.put(player, locals.get(local));
        players.get(player).addExperiencePoints(movePlayerPoints);
    }

    @Override
    public void addTeam(String name) throws IllegalArgumentException, AlreadyExistsException {
        validateString(name, "Name");
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
        try {
            Team currentTeam = players.get(player).getTeam();
            if (currentTeam.getName().equals(team)) {
                throw new IllegalArgumentException("Player is already in team " + team + ".");
            }
        } catch (NoAssociationException ignored) {}
        players.get(player).setTeam(teams.get(team));
    }

    @Override
    public void removePlayerFromTeam(int player) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException, NoTeamException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        try {
            players.get(player).removeTeam();
        } catch (NoTeamException exception) {
            throw new NoTeamException(exception.getMessage());
        }
    }

    @Override
    public Iterator<Player> listPlayersOrdered(SortPlayers sort) {
        sortPlayers = sort;
        OrderedListADT<Player> list = new DoublyLinkedOrderedList<>();
        for (Player player : players.getValues()) {
            list.add(player);
        }
        return list.iterator();
    }

    @Override
    public void loadPlayersTeams(String fileName) throws IllegalArgumentException, IOException {
        try {
            players.clear();
            teams.clear();
            playerPositions.clear();

            JSONObject json = loadFile(fileName);

            // TEAMS
            loadTeams((JSONArray) json.get("teams"));

            // PLAYERS
            loadPlayers((JSONArray) json.get("players"));

        } catch (Exception exception) {
            throw new IOException(exception.getMessage());
        }
    }

    @Override
    public void exportPlayersTeams(String fileName) throws IllegalArgumentException, IOException {
        validateString(fileName, "File name");

        JSONObject json = new JSONObject();

        json.put("teams", saveTeams());

        json.put("players", savePlayers());

        writeFile(fileName, json);
    }

    // GAME MANAGEMENT

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
            playerObject.addExperiencePoints(chargePlayerPoints);
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
            playerObject.getTeam();
        } catch (NoTeamException exception) {
            throw new NoTeamException(exception.getMessage());
        }
        try {
            if (playerObject.getTeam() == portalObject.getOwner().getTeam()) {
                throw new AlreadyConqueredPortalException("Portal is already owned by the same team.");
            }
        } catch (NoAssociationException ignored) {}
        if (playerPositions.get(player) != portalObject) {
            throw new WrongLocationException("Player is not at the local.");
        }
        try {
            if (portalObject.getOwner().getTeam() != playerObject.getTeam()) {
                //equipa adversária
                // in order to conquer a portal of a different team, the player needs to discharge the portal and then charge with 25% of the max energy of the portal otherwise the portal won't have an owner
                if (playerObject.getCurrentEnergy() >= portalObject.getEnergy()) {
                    //enough energy
                    portalObject.removeOwner();
                    playerObject.removeEnergy(portalObject.getEnergy());
                    portalObject.removeEnergy(portalObject.getEnergy());
                    playerObject.addExperiencePoints(acquirePortalPoints);

                    //now check if the player has enough energy to charge the portal with 25% of the max energy of the portal
                    if (playerObject.getCurrentEnergy() >= (portalObject.getMaxEnergy() * 0.25)) {
                        //enough energy
                        portalObject.setOwner(playerObject);
                        playerObject.removeEnergy((int) (portalObject.getMaxEnergy() * 0.25));
                        portalObject.addEnergy((int) (portalObject.getMaxEnergy() * 0.25));
                        playerObject.addExperiencePoints(chargePortalPoints);
                    } else {
                        portalObject.removeEnergy(playerObject.getCurrentEnergy());
                        playerObject.removeEnergy(playerObject.getCurrentEnergy());
                        playerObject.addExperiencePoints(chargePortalPoints / 2);
                        //not enough energy
                        throw new NotEnoughEnergyException("Player " + playerObject.getName() + " does not have enough energy to conquer the portal");
                    }
                } else {
                    portalObject.removeEnergy(playerObject.getCurrentEnergy());
                    playerObject.removeEnergy(playerObject.getCurrentEnergy());
                    playerObject.addExperiencePoints(chargePortalPoints / 4);

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
                playerObject.addExperiencePoints(chargePortalPoints);
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
            playerObject.addExperiencePoints(chargePortalPoints);
        } else {
            throw new NotEnoughEnergyException("Player does not have the specified energy to charge the portal.");
        }
    }

    @Override
    public Iterator<Local> getShortestPath(int local1, int local2) throws IllegalArgumentException, NoSuchLocalException {
        validateInteger(local1, "Local 1");
        validateInteger(local2, "Local 2");
        if (!locals.containsKey(local1)) {
            throw new NoSuchLocalException("Local with id " + local1 + " does not exist.");
        }
        if (!locals.containsKey(local2)) {
            throw new NoSuchLocalException("Local with id " + local2 + " does not exist.");
        }
        return network.iteratorShortestPath(locals.get(local1), locals.get(local2));
    }

    @Override
    public Iterator<Local> getShortestPath(int local1, int local2, boolean portals) throws IllegalArgumentException, NoSuchLocalException {
        validateInteger(local1, "Local 1");
        validateInteger(local2, "Local 2");
        if (!locals.containsKey(local1)) {
            throw new NoSuchLocalException("Local with id " + local1 + " does not exist.");
        }
        if (!locals.containsKey(local2)) {
            throw new NoSuchLocalException("Local with id " + local2 + " does not exist.");
        }
        return network.iteratorShortestPath(locals.get(local1), locals.get(local2), portals);
    }

    @Override
    public void exportShortestPath(Iterator<Local> iterator, String fileName) throws IllegalArgumentException, IOException {
        validateString(fileName, "File name");

        JSONObject json = new JSONObject();

        JSONArray path = new JSONArray();

        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Iterator is empty.");
        }
        Local first = iterator.next();
        JSONObject local = new JSONObject();
        local.put("id", first.getID());
        local.put("name", first.getName());
        local.put("latitude", first.getLatitude());
        local.put("longitude", first.getLongitude());
        path.add(local);
        Local last = first;
        while (iterator.hasNext()) {
            last = iterator.next();
            local = new JSONObject();
            local.put("id", last.getID());
            local.put("name", last.getName());
            local.put("latitude", last.getLatitude());
            local.put("longitude", last.getLongitude());
            path.add(local);
        }
        json.put("from", first.getName());
        json.put("to", last.getName());
        json.put("path", path);

        writeFile(fileName, json);
    }

    @Override
    public void loadGameData(String fileName) throws IllegalArgumentException, IOException {
        try {
            JSONObject json = loadFile(fileName);

            // TEAMS
            loadTeams((JSONArray) json.get("teams"));

            // PLAYERS
            loadPlayers((JSONArray) json.get("players"));

            // LOCALS
            loadLocals((JSONArray) json.get("locals"));

            // ROUTES
            loadRoutes((JSONArray) json.get("routes"));


            // POINTS
            JSONObject points = (JSONObject) json.get("points");
            acquirePortalPoints = Integer.parseInt(points.get("acquirePortal").toString());
            validateInteger(acquirePortalPoints, "Acquire portal points");
            chargePortalPoints = Integer.parseInt(points.get("chargePortal").toString());
            validateInteger(chargePortalPoints, "Charge portal points");
            chargePlayerPoints = Integer.parseInt(points.get("chargePlayer").toString());
            validateInteger(chargePlayerPoints, "Charge player points");
            movePlayerPoints = Integer.parseInt(points.get("movePlayer").toString());
            validateInteger(movePlayerPoints, "Move player points");

        } catch (Exception exception) {
            throw new IOException(exception.getMessage());
        }
    }

    @Override
    public void exportGameData(String fileName) throws IllegalArgumentException, IOException {
        validateString(fileName, "File name");

        JSONObject json = new JSONObject();

        json.put("locals", saveLocals());

        json.put("routes", saveRoutes());

        json.put("players", savePlayers());

        json.put("teams", saveTeams());

        JSONObject points = new JSONObject();
        points.put("acquirePortal", acquirePortalPoints);
        points.put("chargePortal", chargePortalPoints);
        points.put("chargePlayer", chargePlayerPoints);
        points.put("movePlayer", movePlayerPoints);
        json.put("points", points);

        writeFile(fileName, json);
    }

    @Override
    public int getPlayerEnergy(int player) throws IllegalArgumentException, NoSuchPlayerException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        return players.get(player).getCurrentEnergy();
    }

    @Override
    public int getPlayerPosition(int player) throws IllegalArgumentException, NoSuchPlayerException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        if (!playerPositions.containsKey(player)) {
            throw new IllegalArgumentException("Player is not at a local.");
        }
        return playerPositions.get(player).getID();
    }

    @Override
    public String getPlayerTeam(int player) throws IllegalArgumentException, NoSuchPlayerException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        if (players.get(player).getTeam() == null) {
            throw new IllegalArgumentException("Player is not in a team.");
        }
        try {
            return players.get(player).getTeam().getName();
        } catch (NoAssociationException ignored) {
            return "None";
        }
    }

    @Override
    public int getPlayerExperiencePoints(int player) throws IllegalArgumentException, NoSuchPlayerException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        return players.get(player).getExperiencePoints();
    }

    @Override
    public int getPlayerLevel(int player) throws IllegalArgumentException, NoSuchPlayerException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        return players.get(player).getLevel();
    }

    @Override
    public Iterator<Portal> getConqueredPortals(int player) throws IllegalArgumentException, NoSuchPlayerException {
        validateInteger(player, "Player");
        if (!players.containsKey(player)) {
            throw new NoSuchPlayerException("Player with id " + player + " does not exist.");
        }
        try {
            return players.get(player).getPortals().iterator();
        } catch (NoAssociationException ignored) {
            return new LinkedUnorderedList<Portal>().iterator();
        }
    }

    @Override
    public Iterator<Local> getLocalsInRange(int local) throws IllegalArgumentException, NoSuchLocalException {
        validateInteger(local, "Local");
        if (!locals.containsKey(local)) {
            throw new NoSuchLocalException("Local with id " + local + " does not exist.");
        }
        return network.iteratorBFS(locals.get(local));
    }

    //JSON

    protected void writeFile(String fileName, JSONObject json) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(json.toJSONString().getBytes());
        fileOutputStream.close();
    }

    protected JSONObject loadFile(String fileName) throws IOException {
        validateString(fileName, "File name");
        JSONObject json;
        try {
            json = (JSONObject) new JSONParser().parse(new FileReader(fileName));
        } catch (ParseException e) {
            throw new IOException("File is not a valid JSON");
        }
        return json;
    }

    protected void loadTeams(JSONArray teamsArray) throws IOException {
        try {
            teams.clear();

            for (JSONObject jsonObject : (Iterable<JSONObject>) teamsArray) {
                addTeam((String) jsonObject.get("name"));
            }
        } catch (Exception exception) {
            throw new IOException(exception.getMessage());
        }
    }

    protected JSONArray saveTeams() {
        JSONArray teamsArray = new JSONArray();
        for (Team team : teams.getValues()) {
            teamsArray.add(team.getJSON());
        }
        return teamsArray;
    }

    protected void loadPlayers(JSONArray playersArray) throws IOException {
        try {
            players.clear();
            playerPositions.clear();

            for (JSONObject player : (Iterable<JSONObject>) playersArray) {
                int id = ((Long) player.get("id")).intValue();
                String name = (String) player.get("name");
                String team = (String) player.get("team");
                int energy = ((Long) player.get("currentEnergy")).intValue();
                int experience = ((Long) player.get("experiencePoints")).intValue();
                addPlayer(id, name, team);
                Player playerObject = players.get(id);
                playerObject.setCurrentEnergy(energy);
                playerObject.addExperiencePoints(experience);
            }
        } catch (Exception exception) {
            throw new IOException(exception.getMessage());
        }
    }

    protected JSONArray savePlayers() {
        JSONArray playersArray = new JSONArray();
        for (Player player : players.getValues()) {
            playersArray.add(player.getJSON());
        }
        return playersArray;
    }

    protected void loadLocals(JSONArray localsArray) throws IOException {
        try {
            locals.clear();

            for (JSONObject local : (Iterable<JSONObject>) localsArray) {
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
        } catch (Exception exception) {
            throw new IOException(exception.getMessage());
        }
    }

    protected JSONArray saveLocals() {
        JSONArray localsArray = new JSONArray();
        Iterator<Local> iterator = network.iteratorVertexes();
        while (iterator.hasNext()) {
            Local place = iterator.next();
            localsArray.add(place.getJSON());
        }
        return localsArray;
    }

    protected void loadRoutes(JSONArray routesArray) throws IOException {
        try {
            network.clearRoutes();

            for (JSONObject route : (Iterable<JSONObject>) routesArray) {
                int from = ((Long) route.get("from")).intValue();
                int to = ((Long) route.get("to")).intValue();
                addRoute(from, to);
            }
        } catch (Exception exception) {
            throw new IOException(exception.getMessage());
        }
    }

    protected JSONArray saveRoutes() {
        JSONArray routesArray = new JSONArray();
        Iterator<Local> iterator = network.iteratorRoutes();
        while (iterator.hasNext()) {
            JSONObject route = new JSONObject();
            route.put("from", iterator.next().getID());
            route.put("to", iterator.next().getID());
            routesArray.add(route);
        }
        return routesArray;
    }
}
