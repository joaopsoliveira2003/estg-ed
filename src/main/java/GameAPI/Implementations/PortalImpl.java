package GameAPI.Implementations;

import GameAPI.Interfaces.Portal;

public class PortalImpl extends PlaceImpl implements Portal {
    
    public PortalImpl(int id, String name, double latitude, double longitude, int energy) {
        super(id, name, latitude, longitude, energy);
    }
   
    
}
