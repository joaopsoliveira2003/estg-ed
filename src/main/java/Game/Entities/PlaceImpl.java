package Game.Entities;

import Exceptions.IllegalArgumentException;

public class PlaceImpl implements Place {

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private int energy;

    public PlaceImpl(int id, String name, double latitude, double longitude, int energy) {
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
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        this.id = id;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null, empty or blank");
        }
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
    public double getDistanceTo(Place place) throws IllegalArgumentException {
        if (place == null) {
            throw new IllegalArgumentException("Place cannot be null");
        }
        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(place.getLatitude());
        double lon1 = Math.toRadians(longitude);
        double lon2 = Math.toRadians(place.getLongitude());
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
        if (energy < 0) {
            throw new IllegalArgumentException("Energy cannot be negative");
        }
        this.energy = energy;
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
        final PlaceImpl other = (PlaceImpl) obj;
        return this.id == other.id;
    }

    @Override
    public int compareTo(Place other) {
        return this.id - other.getID();
    }

    @Override
    public String toString() {
        return "\nPlace {" + "id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", energy=" + energy + '}';
    }
}
