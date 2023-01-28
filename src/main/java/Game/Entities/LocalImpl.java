package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;
import Game.API.GameImpl;

import static Game.Utilities.Validations.validateInteger;
import static Game.Utilities.Validations.validateString;

/**
 * LocalImpl defines the implementation of a local in the game.
 */
public abstract class LocalImpl implements Local {

    protected int id;
    protected String name;
    protected double latitude;
    protected double longitude;
    protected int energy;

    public LocalImpl(int id, String name, double latitude, double longitude, int energy) {
        setID(id);
        setName(name);
        setLatitude(latitude);
        setLongitude(longitude);
        setEnergy(energy);
    }

    @Override
    public int getID() {
        return id;
    }
    
    @Override
    public void setID(int id) throws IllegalArgumentException {
        validateInteger(id, "ID");
        this.id = id;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setName(String name) throws IllegalArgumentException {
        validateString(name, "Name");
        this.name = name;
    }
    
    @Override
    public double getLatitude() {
        return latitude;
    }
    
    @Override
    public void setLatitude(double latitude) throws IllegalArgumentException {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Wrong latitude value");
        }
        this.latitude = latitude;
    }
    
    @Override
    public double getLongitude() {
        return longitude;
    }
    
    @Override
    public void setLongitude(double longitude) throws IllegalArgumentException {
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Wrong longitude value");
        }
        this.longitude = longitude;
    }

    @Override
    public double getDistanceTo(Local local) throws IllegalArgumentException {
        if (local == null) {
            throw new IllegalArgumentException("Local cannot be null");
        }
        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(local.getLatitude());
        double lon1 = Math.toRadians(longitude);
        double lon2 = Math.toRadians(local.getLongitude());
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }

    @Override
    public int getEnergy() {
        return energy;
    }
    
    @Override
    public void setEnergy(int energy) throws IllegalArgumentException {
        validateInteger(energy, "Energy");
        this.energy = energy;
    }

    @Override
    public void addEnergy(int energy) throws IllegalArgumentException {
        validateInteger(energy, "Energy");
        this.energy += energy;
    }

    public void removeEnergy(int energy) throws IllegalArgumentException {
        validateInteger(energy, "Energy");
        if (energy > this.energy) {
            throw new IllegalArgumentException("Not enough energy");
        }
        this.energy -= energy;
    }


    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LocalImpl other = (LocalImpl) obj;
        return this.id == other.id;
    }

    @Override
    public int compareTo(Local other) {
        switch (GameImpl.sortLocals) {
            case ID:
                return this.id - other.getID();
            case TYPE:
                return this.getClass().getSimpleName().compareTo(other.getClass().getSimpleName());
            case LATITUDE:
                return (int) (this.latitude - other.getLatitude());
            case LONGITUDE:
                return (int) (this.longitude - other.getLongitude());
            case ENERGY:
                return this.energy - other.getEnergy();
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return "\nLocal {" + "id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", energy=" + energy + '}';
    }
}
