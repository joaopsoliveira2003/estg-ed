package GameAPI.Implementations;

import Collections.Implementations.HashTables.HashMap;
import Collections.Implementations.HashTables.MapADT;
import Collections.Implementations.Lists.ListADT;
import GameAPI.Interfaces.Player;
import GameAPI.Interfaces.Team;

public class TeamPlayerAssociation {

    private static TeamPlayerAssociation instance;
    private final MapADT<Team, Player> teamPlayerMap;

    private TeamPlayerAssociation() {
        teamPlayerMap = new HashMap<>();
    }

    public static TeamPlayerAssociation getInstance() {
        if (instance == null) {
            instance = new TeamPlayerAssociation();
        }
        return instance;
    }

    public void addAssociation(Team team, Player player) {
        teamPlayerMap.put(team, player);
    }

    public Player getPlayer(Team team) {
        return teamPlayerMap.get(team);
    }

    public void removeAssociation(Team team) {
        teamPlayerMap.remove(team);
    }

    //get players of a team
    public ListADT<Player> getPlayers(Team team) {
        return teamPlayerMap.getValues(team);
    }
}
