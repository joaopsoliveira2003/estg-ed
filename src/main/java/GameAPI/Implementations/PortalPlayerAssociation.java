package GameAPI.Implementations;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.IllegalArgumentException;
import Collections.Exceptions.NoSuchElementException;
import Collections.Implementations.HashTables.HashMap;
import Collections.Implementations.HashTables.MapADT;
import Collections.Implementations.Lists.LinkedUnorderedList;
import Collections.Implementations.Lists.UnorderedListADT;
import GameAPI.Exceptions.InvalidArgumentException;
import GameAPI.Exceptions.NoAssociationException;
import GameAPI.Exceptions.NoSuchPlayerException;
import GameAPI.Exceptions.NoSuchPortalException;
import GameAPI.Interfaces.Player;
import GameAPI.Interfaces.Portal;

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

    public Player getPlayer(Portal portal) throws NoAssociationException, InvalidArgumentException {
        try {
            return portalPlayerMap.get(portal);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new NoAssociationException("Portal is not associated with any player");
        } catch (IllegalArgumentException ignored) {
            throw new InvalidArgumentException("Portal is null");
        }
    }

    public UnorderedListADT<Portal> getPortals(Player player) throws NoAssociationException, InvalidArgumentException {
        try {
            return playerPortalMap.get(player);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new NoAssociationException("Player is not associated with any portal");
        } catch (IllegalArgumentException ignored) {
            throw new InvalidArgumentException("Player is null");
        }
    }

    public void addAssociation(Portal portal, Player player) throws InvalidArgumentException {
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
            throw new InvalidArgumentException("Portal or Player is null");
        }
    }

    public void removeAssociation(Portal portal, Player player) throws NoAssociationException, InvalidArgumentException {
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
            throw new InvalidArgumentException("Portal or Player is null");
        }
    }
}
