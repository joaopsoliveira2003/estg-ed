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
import GameAPI.Interfaces.Player;
import GameAPI.Interfaces.Team;

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

    public UnorderedListADT<Player> getPlayers(Team team) throws NoAssociationException, InvalidArgumentException {

        try {
            return teamPlayerMap.get(team);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new NoAssociationException("Team is not associated with any player");
        } catch (IllegalArgumentException ignored) {
            throw new InvalidArgumentException("Team is null");
        }
    }

    public Team getTeam(Player player) throws NoAssociationException, InvalidArgumentException  {
        try {
            return playerTeamMap.get(player);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new NoAssociationException("Player is not associated with any team");
        } catch (IllegalArgumentException ignored) {
            throw new InvalidArgumentException("Team is null");
        }
    }

    public void addAssociation(Team team, Player player)throws InvalidArgumentException {
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
            throw new InvalidArgumentException("Team or Player is null");
        }
    }

    public void removeAssociation(Team team, Player player) throws NoAssociationException, InvalidArgumentException {
        try {
            UnorderedListADT<Player> players = teamPlayerMap.get(team);
            players.remove(player);
            if (players.isEmpty()) {
                teamPlayerMap.remove(team);
            }
            playerTeamMap.remove(player);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new NoAssociationException("Player is not associated with any team");
        } catch (IllegalArgumentException ignored) {
            throw new InvalidArgumentException("Team or Player is null");
        }
    }
}
