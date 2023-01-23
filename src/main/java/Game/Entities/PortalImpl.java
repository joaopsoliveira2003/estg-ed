package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;
import Game.Exceptions.NoAssociationException;
import org.json.simple.JSONObject;

import java.io.IOException;

public class PortalImpl extends LocalImpl implements Portal {

    PortalPlayerAssociation ppa = PortalPlayerAssociation.getInstance();
    int maxEnergy;

    public PortalImpl(int id, String name, double latitude, double longitude, int energy, int maxEnergy) {
        super(id, name, latitude, longitude, energy);
        setEnergy(energy);
        setMaxEnergy(maxEnergy);
    }
    
    public PortalImpl(int id, String name, double latitude, double longitude, int energy, int maxEnergy, Player owner) {
        super(id, name, latitude, longitude, energy);
        setEnergy(energy);
        setMaxEnergy(maxEnergy);
        setOwner(owner);
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
            ownership.put("player", getOwner().getName());
        } catch (NoAssociationException ignored) {
            ownership.put("player", "None");
        }
        gameSettings.put("ownership", ownership);
        portal.put("gameSettings", gameSettings);
        return portal;
    }

    @Override
    public void setJSON(JSONObject json) throws IOException, IllegalArgumentException {

    }
}
