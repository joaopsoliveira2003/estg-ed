package GameAPI.Implementations;

import GameAPI.Interfaces.Place;

public class PlaceImpl implements Place {

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private int energy;

    public PlaceImpl(int id, String name, double latitude, double longitude, int energy) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.energy = energy;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int getID() {
        return id;
    }
    
    @Override
    public void setID(int id) {
        this.id = id;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public double getLatitude() {
        return latitude;
    }
    
    @Override
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    @Override
    public double getLongitude() {
        return longitude;
    }
    
    @Override
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    @Override
    public int getEnergy() {
        return energy;
    }
    
    @Override
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    
    @Override
    public String toString() {
        return "Place {" + "id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", energy=" + energy + '}';
    }
}
