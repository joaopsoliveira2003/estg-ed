package GameAPI.Implementations;

import GameAPI.Interfaces.Connector;

public class ConnectorImpl extends PlaceImpl implements Connector {
    
    public ConnectorImpl(int id, String name, double latitude, double longitude, int energy) {
        super(id, name, latitude, longitude, energy);
    }
    
    
}
