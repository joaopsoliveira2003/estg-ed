package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;
import Collections.Lists.UnorderedListADT;
import Game.API.GameImpl;
import Game.Enumerations.SortPlayers;
import Game.Exceptions.NoAssociationException;
import org.json.simple.JSONObject;

import static Game.Utilities.Validations.validateInteger;

/**
 * PlayerImpl defines the implementation of a player in the game.
 */
public class PlayerImpl implements Player {

    PortalPlayerAssociation ppa = PortalPlayerAssociation.getInstance();
    TeamPlayerAssociation tpa = TeamPlayerAssociation.getInstance();

    private int id;
    private String name;
    private int currentEnergy;
    private int maxEnergy;
    private int experiencePoints;

    public PlayerImpl(int id, String name) {
        setID(id);
        setName(name);
        setCurrentEnergy(150);
        setMaxEnergy(150);
        setExperiencePoints(0);
    }

    public PlayerImpl(int id, String name, Team team) {
        setID(id);
        setName(name);
        setTeam(team);
        setCurrentEnergy(150);
        setMaxEnergy(150);
        setExperiencePoints(0);
    }

    public PlayerImpl(int id, String name, Team team, int currentEnergy, int experiencePoints) {
        setID(id);
        setName(name);
        setTeam(team);
        setCurrentEnergy(currentEnergy);
        setMaxEnergy(150);
        setExperiencePoints(experiencePoints);

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
    public Team getTeam() throws NoAssociationException {
        return tpa.getTeam(this);
    }

    @Override
    public void setTeam(Team team) throws IllegalArgumentException {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        try {
            tpa.removeAssociation(getTeam(), this);
        } catch (NoAssociationException ignored) {} finally {
            tpa.addAssociation(team, this);
        }
    }

    @Override
    public void removeTeam() throws NoAssociationException {
        tpa.removeAssociation(getTeam(), this);
    }

    @Override
    public int getCurrentEnergy() {
        return currentEnergy;
    }

    @Override
    public void setCurrentEnergy(int energy) throws IllegalArgumentException {
        if (energy < 0) {
            throw new IllegalArgumentException("Energy cannot be negative");
        }
        this.currentEnergy = energy;
    }

    @Override
    public void addEnergy(int energy) throws IllegalArgumentException {
        validateInteger(energy, "Energy");
        if (energy + currentEnergy > getMaxEnergy()) {
            throw new IllegalArgumentException("Energy cannot be greater than max energy");
        }
        this.currentEnergy += energy;
    }

    @Override
    public void removeEnergy(int energy) throws IllegalArgumentException {
        if (energy < 0) {
            throw new IllegalArgumentException("Energy cannot be negative");
        }
        this.currentEnergy -= energy;
    }

    @Override
    public int getMaxEnergy() {
        return (int) (maxEnergy * Math.pow(1.1, getLevel()));
    }

    @Override
    public void setMaxEnergy(int maxEnergy) throws IllegalArgumentException {
        if (maxEnergy < 0) {
            throw new IllegalArgumentException("Max Energy cannot be negative");
        }
        this.maxEnergy = maxEnergy;
    }

    @Override
    public int getLevel() {
        return (int) (0.07 * Math.sqrt(experiencePoints));
    }


    @Override
    public int getExperiencePoints() {
        return experiencePoints;
    }

    @Override
    public void setExperiencePoints(int experiencePoints) throws IllegalArgumentException {
        if (experiencePoints < 0) {
            throw new IllegalArgumentException("Experience Points cannot be negative");
        }
        this.experiencePoints = experiencePoints;
    }

    @Override
    public void addExperiencePoints(int experiencePoints) throws IllegalArgumentException {
        if (experiencePoints < 0) {
            throw new IllegalArgumentException("Experience Points cannot be negative");
        }
        this.experiencePoints += experiencePoints;
    }

    @Override
    public UnorderedListADT<Portal> getPortals() throws NoAssociationException {
        return ppa.getPortals(this);
    }

    @Override
    public JSONObject getJSON() {
        JSONObject json = new JSONObject();

        json.put("id", id);
        json.put("name",name);
        try {
            json.put("team", getTeam().getName());
        } catch (NoAssociationException e) {
            json.put("team", "None");
        }
        json.put("level", getLevel());
        json.put("experiencePoints", experiencePoints);
        json.put("currentEnergy", currentEnergy);

        return json;
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
        final PlayerImpl other = (PlayerImpl) obj;
        return this.id == other.id;
    }

    @Override
    public int compareTo(Player o) {
        switch (GameImpl.sortPlayers) {
            case ID:
                return this.id - o.getID();
            case TEAM:
                try {
                    String name = this.getTeam().getName();
                    try {
                        return name.compareTo(o.getTeam().getName());
                    } catch (NoAssociationException e) {
                        return 1;
                    }
                } catch (NoAssociationException e) {
                    return -1;
                }
            case LEVEL:
                return this.getLevel() - o.getLevel();
            case PORTALS:
                try {
                    int portals = this.getPortals().size();
                    try {
                        return portals - o.getPortals().size();
                    } catch (NoAssociationException e) {
                        return 1;
                    }
                } catch (NoAssociationException e) {
                    return -1;
                }
            default:
                return 0;
        }
    }

    @Override
        public String toString() {
            String team = "None";
            try {
                team = getTeam().getName();
            } catch (NoAssociationException ignored) {}
            int portals = 0;
            try {
                portals = getPortals().size();
            } catch (NoAssociationException ignored) {}
            return "\nPlayer {"
            + "id=" + id
            + ", name=" + name
            + ", team=" + team
            + ", currentEnergy=" + currentEnergy
            + ", level=" + getLevel()
            + ", portals=" + portals
            + ", experiencePoints=" + experiencePoints + '}';
        }
}


