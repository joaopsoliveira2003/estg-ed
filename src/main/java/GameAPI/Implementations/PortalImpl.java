package GameAPI.Implementations;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;
import GameAPI.Exceptions.InvalidArgumentException;
import GameAPI.Exceptions.NoAssociationException;
import GameAPI.Exceptions.NoSuchPlayerException;
import GameAPI.Interfaces.Player;
import GameAPI.Interfaces.Portal;

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
    public void setMaxEnergy(int maxEnergy) throws InvalidArgumentException {
        if (maxEnergy < 0) {
            throw new InvalidArgumentException("Max energy cannot be negative");
        }
        this.maxEnergy = maxEnergy;
        
    }

    @Override
    public Player getOwner() throws NoSuchPlayerException {
        return ppa.getPlayer(this);
    }

    @Override
    public void setOwner(Player owner) throws InvalidArgumentException {
        if (owner == null) {
            throw new InvalidArgumentException("Owner cannot be null");
        }
        try {
            ppa.removeAssociation(this, getOwner());
        } catch (NoAssociationException ignored) {
            ppa.addAssociation(this, owner);
        }
    }
}
