package GameAPI.Implementations;

import Collections.Implementations.HashTables.HashMap;
import Collections.Implementations.HashTables.MapADT;
import Collections.Implementations.Lists.ListADT;
import GameAPI.Interfaces.Player;
import GameAPI.Interfaces.Portal;
import GameAPI.Interfaces.Team;

import java.util.Iterator;

public class PortalPlayerAssociation {

    private static PortalPlayerAssociation instance;
    private final MapADT<Portal, Player> portalPlayerMap;

    private PortalPlayerAssociation() {
        portalPlayerMap = new HashMap<>();
    }

    public static PortalPlayerAssociation getInstance() {
        if (instance == null) {
            instance = new PortalPlayerAssociation();
        }
        return instance;
    }

    public void addAssociation(Portal portal, Player player) {
        portalPlayerMap.put(portal, player);
    }

    public Player getPlayer(Portal portal) {
        return portalPlayerMap.get(portal);
    }

    public void removeAssociation(Portal portal) {
        portalPlayerMap.remove(portal);
    }

    //get all portals owned by a player
    public Iterator<Portal> getPortals(Player player) {
        return portalPlayerMap.getKeys(player).iterator();
    }

    //get all portals owned by a team
    public Iterator<Portal> getPortals(Team team) {
        TeamPlayerAssociation teamPlayerAssociation = TeamPlayerAssociation.getInstance();
        ListADT<Player> players = teamPlayerAssociation.getPlayers(team);
    }


}
