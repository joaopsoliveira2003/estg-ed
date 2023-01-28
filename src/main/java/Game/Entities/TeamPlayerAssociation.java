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
 * TeamPlayerAssociation is a singleton class that manages the association between teams and players.
 */
public class TeamPlayerAssociation {

    private static TeamPlayerAssociation instance;
    private final MapADT<Player, Team> playerTeamMap;
    private final MapADT<Team, UnorderedListADT<Player>> teamPlayerMap;

    private TeamPlayerAssociation() {
        playerTeamMap = new HashMap<>();
        teamPlayerMap = new HashMap<>();
    }

    public static TeamPlayerAssociation getInstance() {
        if (instance == null) {
            instance = new TeamPlayerAssociation();
        }
        return instance;
    }

    public UnorderedListADT<Player> getPlayers(Team team) throws NoAssociationException, IllegalArgumentException {

        try {
            return teamPlayerMap.get(team);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new NoAssociationException("Team is not associated with any player");
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Team is null");
        }
    }

    public Team getTeam(Player player) throws NoAssociationException, IllegalArgumentException  {
        try {
            return playerTeamMap.get(player);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new NoAssociationException("Player is not associated with any team");
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Team is null");
        }
    }

    public void addAssociation(Team team, Player player)throws IllegalArgumentException {
        try {
            if (teamPlayerMap.containsKey(team)) {
                teamPlayerMap.get(team).addToRear(player);
            } else {
                UnorderedListADT<Player> players = new LinkedUnorderedList<>();
                players.addToRear(player);
                teamPlayerMap.put(team, players);
            }
            playerTeamMap.put(player, team);
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Team or Player is null");
        }
    }

    public void removeAssociation(Team team, Player player) throws NoAssociationException, IllegalArgumentException {
        try {
            UnorderedListADT<Player> players = teamPlayerMap.get(team);
            players.remove(player);
            if (players.isEmpty()) {
                teamPlayerMap.remove(team);
            }
            playerTeamMap.remove(player);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new NoAssociationException("Player " + player.getName() + " is not associated with any team");
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Team or Player is null");
        }
    }
}
