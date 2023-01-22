package GameAPI.Implementations;

import java.time.Instant;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.IllegalArgumentException;
import Collections.Exceptions.NoSuchElementException;
import Collections.Implementations.HashTables.HashMap;
import Collections.Implementations.HashTables.MapADT;
import GameAPI.Exceptions.InvalidArgumentException;
import GameAPI.Interfaces.Connector;
import GameAPI.Interfaces.Player;

public class ConnectorImpl extends PlaceImpl implements Connector {

    int coolDownTime;
    MapADT<Player, Instant> playerInstantMap;
    
    public ConnectorImpl(int id, String name, double latitude, double longitude, int energy, int coolDownTime) {
        super(id, name, latitude, longitude, energy);
        this.coolDownTime = coolDownTime;
        this.playerInstantMap = new HashMap<>();
    }

    @Override
    public int getCoolDownTime() {
        return coolDownTime;
    }

    @Override
    public void setCoolDownTime(int coolDownTime) throws InvalidArgumentException{
        if (coolDownTime < 0) {
            throw new InvalidArgumentException("Cool down time cannot be negative");
        }
        this.coolDownTime = coolDownTime;
    }

    @Override
    public void addLastInteraction(Player player) throws InvalidArgumentException {
        try {
            playerInstantMap.put(player, Instant.now());
        } catch (IllegalArgumentException ignored){
            throw new InvalidArgumentException("Player is not valid");
        }
    }

    @Override
    public boolean isCoolDownOver(Player player) throws InvalidArgumentException {
        try {
            return Instant.now().compareTo(playerInstantMap.get(player).plusSeconds(coolDownTime * 60L)) > 0;
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            return true;
        } catch (IllegalArgumentException ignored) {
            throw new InvalidArgumentException("Player is not valid");
        }
    }
}
