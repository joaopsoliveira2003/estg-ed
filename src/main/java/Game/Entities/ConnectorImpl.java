package Game.Entities;

import java.time.Instant;

import Exceptions.EmptyCollectionException;
import Exceptions.IllegalArgumentException;
import Exceptions.NoSuchElementException;
import Game.CustomCollections.HashMap;
import Game.CustomCollections.MapADT;

public class ConnectorImpl extends PlaceImpl implements Connector {

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
        if (coolDownTime < 0) {
            throw new IllegalArgumentException("Cool down time cannot be negative");
        }
        this.coolDownTime = coolDownTime;
    }

    @Override
    public void addLastInteraction(Player player) throws IllegalArgumentException {
        try {
            playerInstantMap.put(player, Instant.now());
        } catch (IllegalArgumentException ignored){
            throw new IllegalArgumentException("Player is not valid");
        }
    }

    @Override
    public boolean isCoolDownOver(Player player) throws IllegalArgumentException {
        try {
            return Instant.now().compareTo(playerInstantMap.get(player).plusSeconds(coolDownTime * 60L)) > 0;
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            return true;
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Player is not valid");
        }
    }
}
