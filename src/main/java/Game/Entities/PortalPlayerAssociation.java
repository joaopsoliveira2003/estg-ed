package Game.Entities;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.IllegalArgumentException;
import Collections.Exceptions.NoSuchElementException;
import Collections.HashTables.HashMap;
import Collections.HashTables.MapADT;
import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;
import Game.Exceptions.NoAssociationException;

/**
 * PortalPlayerAssociation is a singleton class that manages the association between portals and players.
 */
public class PortalPlayerAssociation {

    private static PortalPlayerAssociation instance;
    private final MapADT<Portal, Player> portalPlayerMap;
    private final MapADT<Player, UnorderedListADT<Portal>> playerPortalMap;

    private PortalPlayerAssociation() {
        portalPlayerMap = new HashMap<>();
        playerPortalMap = new HashMap<>();
    }

    public static PortalPlayerAssociation getInstance() {
        if (instance == null) {
            instance = new PortalPlayerAssociation();
        }
        return instance;
    }

    public Player getPlayer(Portal portal) throws NoAssociationException, IllegalArgumentException {
        try {
            return portalPlayerMap.get(portal);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new NoAssociationException("Portal is not associated with any player");
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Portal is null");
        }
    }

    public UnorderedListADT<Portal> getPortals(Player player) throws NoAssociationException, IllegalArgumentException {
        try {
            return playerPortalMap.get(player);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new NoAssociationException("Player is not associated with any portal");
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Player is null");
        }
    }

    public void addAssociation(Portal portal, Player player) throws IllegalArgumentException {
        try {
            if (playerPortalMap.containsKey(player)) {
                playerPortalMap.get(player).addToRear(portal);
            } else {
                UnorderedListADT<Portal> portals = new LinkedUnorderedList<>();
                portals.addToRear(portal);
                playerPortalMap.put(player, portals);
            }
            portalPlayerMap.put(portal, player);
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Portal or Player is null");
        }
    }

    public void removeAssociation(Portal portal, Player player) throws NoAssociationException, IllegalArgumentException {
        try {
            UnorderedListADT<Portal> portals = playerPortalMap.get(player);
            portals.remove(portal);
            if (portals.isEmpty()) {
                playerPortalMap.remove(player);
            }
            portalPlayerMap.remove(portal);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new NoAssociationException("Player is not associated with any portal");
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Portal or Player is null");
        }
    }
}
