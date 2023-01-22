package Game.Entities;

import Exceptions.IllegalArgumentException;
import Game.Exceptions.NoAssociationException;
import Game.Exceptions.NoSuchPlayerException;

public class PortalImpl extends PlaceImpl implements Portal {

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
}
