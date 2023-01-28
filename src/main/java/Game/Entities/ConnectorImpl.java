package Game.Entities;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.IllegalArgumentException;
import Collections.Exceptions.NoSuchElementException;
import Collections.HashTables.HashMap;
import Collections.HashTables.MapADT;
import org.json.simple.JSONObject;

import java.time.Instant;

import static Game.Utilities.Validations.validateInteger;

/**
 * ConnectorImpl defines the implementation of a connector in the game.
 */
public class ConnectorImpl extends LocalImpl implements Connector {

    int coolDownTime;
    MapADT<Player, Instant> playerInstantMap;
    
    public ConnectorImpl(int id, String name, double latitude, double longitude, int energy, int coolDownTime) {
        super(id, name, latitude, longitude, energy);
        setCoolDownTime(coolDownTime);
        this.playerInstantMap = new HashMap<>();
    }

    @Override
    public int getCoolDownTime() {
        return coolDownTime;
    }

    @Override
    public void setCoolDownTime(int coolDownTime) throws IllegalArgumentException{
        validateInteger(coolDownTime, "Cool down time");
        this.coolDownTime = coolDownTime;
    }

    @Override
    public void addLastInteraction(Player player) throws IllegalArgumentException {
        if (player == null) throw new IllegalArgumentException("Player is not valid");
        try {
            playerInstantMap.put(player, Instant.now());
        } catch (IllegalArgumentException ignored){
            throw new IllegalArgumentException("Player is not valid");
        }
    }

    @Override
    public boolean isCoolDownOver(Player player) throws IllegalArgumentException {
        if (player == null) throw new IllegalArgumentException("Player is not valid");
        try {
            return Instant.now().compareTo(playerInstantMap.get(player).plusSeconds(coolDownTime * 60L)) > 0;
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            return true;
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Player is not valid");
        }
    }

    @Override
    public JSONObject getJSON() {
        JSONObject connector = new JSONObject();
        connector.put("id", getID());
        connector.put("type", "Connector");
        connector.put("name", getName());
        JSONObject coordinates = new JSONObject();
        coordinates.put("latitude", getLatitude());
        coordinates.put("longitude", getLongitude());
        connector.put("coordinates", coordinates);
        JSONObject gameSettings = new JSONObject();
        gameSettings.put("energy", getEnergy());
        gameSettings.put("cooldown", getCoolDownTime());
        connector.put("gameSettings", gameSettings);
        return connector;
    }

    @Override
    public String toString() {
        return "Conector { " +
                "id = " + getID() +
                ", name = '" + getName() +
                ", latitude = " + getLatitude() +
                ", longitude = " + getLongitude() +
                ", energy = " + getEnergy() +
                ", coolDownTime = " + getCoolDownTime() +
                " }";
    }
}
