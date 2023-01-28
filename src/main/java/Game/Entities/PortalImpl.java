package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;
import Game.Exceptions.NoAssociationException;
import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * PortalImpl defines the implementation of a portal in the game.
 */
public class PortalImpl extends LocalImpl implements Portal {

    PortalPlayerAssociation ppa = PortalPlayerAssociation.getInstance();
    int maxEnergy;

    public PortalImpl(int id, String name, double latitude, double longitude, int energy, int maxEnergy) {
        super(id, name, latitude, longitude, energy);
        setMaxEnergy(maxEnergy);
        setEnergy(energy);
    }
    
    public PortalImpl(int id, String name, double latitude, double longitude, int energy, int maxEnergy, Player owner) {
        super(id, name, latitude, longitude, energy);
        setMaxEnergy(maxEnergy);
        setEnergy(energy);
        setOwner(owner);
    }

    @Override
    public void setEnergy(int energy) throws IllegalArgumentException {
        if (energy < 0) {
            throw new IllegalArgumentException("Energy cannot be negative");
        }
        if (maxEnergy != 0 && energy > maxEnergy) {
            throw new IllegalArgumentException("Energy cannot be greater than max energy");
        }
        super.energy = energy;
    }

    @Override
    public void addEnergy(int energy) throws IllegalArgumentException {
        if (energy < 0) {
            throw new IllegalArgumentException("Energy cannot be negative");
        }
        if (energy + super.energy > (maxEnergy == 0 ? Integer.MAX_VALUE : maxEnergy)) {
            throw new IllegalArgumentException("Energy cannot be greater than max energy");
        }
        super.energy += energy;
    }

    @Override
    public int getMaxEnergy() {
        return maxEnergy;
    }

    @Override
    public void setMaxEnergy(int maxEnergy) throws IllegalArgumentException {
        if (maxEnergy < 0) {
            throw new IllegalArgumentException("Max energy cannot be negative");
        }
        this.maxEnergy = maxEnergy;
        
    }

    @Override
    public Player getOwner() throws NoAssociationException {
        return ppa.getPlayer(this);
    }

    @Override
    public void setOwner(Player owner) throws IllegalArgumentException {
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null");
        }
        try {
            ppa.removeAssociation(this, getOwner());
        } catch (NoAssociationException ignored) {
            ppa.addAssociation(this, owner);
        }
    }

    @Override
    public void removeOwner() throws NoAssociationException {
        ppa.removeAssociation(this, getOwner());
    }

    @Override
    public String toString() {
        String owner;
        try {
            owner = getOwner().getName();
        } catch (NoAssociationException ignored) {
            owner = "None";
        }
        return "Portal {" +
                "id=" + getID() +
                ", name='" + getName() + '\'' +
                ", latitude=" + getLatitude() +
                ", longitude=" + getLongitude() +
                ", energy=" + getEnergy() +
                ", maxEnergy=" + maxEnergy +
                ", owner=" + owner +
                '}';
    }

    @Override
    public JSONObject getJSON() {
        JSONObject portal = new JSONObject();
        portal.put("id", getID());
        portal.put("type", "Portal");
        portal.put("name", getName());
        JSONObject coordinates = new JSONObject();
        coordinates.put("latitude", getLatitude());
        coordinates.put("longitude", getLongitude());
        portal.put("coordinates", coordinates);
        JSONObject gameSettings = new JSONObject();
        gameSettings.put("energy", getEnergy());
        gameSettings.put("maxEnergy", getMaxEnergy());
        JSONObject ownership = new JSONObject();
        try {
            ownership.put("player", getOwner().getID());
        } catch (NoAssociationException ignored) {
            ownership.put("player", -1);
        }
        gameSettings.put("ownership", ownership);
        portal.put("gameSettings", gameSettings);
        return portal;
    }
}
