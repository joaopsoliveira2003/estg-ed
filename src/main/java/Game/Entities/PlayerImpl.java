package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;
import Collections.Lists.UnorderedListADT;
import Game.Exceptions.NoAssociationException;
import org.json.simple.JSONObject;

public class PlayerImpl implements Player {

    PortalPlayerAssociation ppa = PortalPlayerAssociation.getInstance();
    TeamPlayerAssociation tpa = TeamPlayerAssociation.getInstance();

    private int id;
    private String name;
    private int currentEnergy;
    private int experiencePoints;

    public PlayerImpl(int id, String name) {
        setID(id);
        setName(name);
        setCurrentEnergy(150);
        setExperiencePoints(0);
    }

    public PlayerImpl(int id, String name, Team team) {
        setID(id);
        setName(name);
        setTeam(team);
        setCurrentEnergy(150);
        setExperiencePoints(0);
    }

    public PlayerImpl(int id, String name, Team team, int currentEnergy, int experiencePoints) {
        setID(id);
        setName(name);
        setTeam(team);
        setCurrentEnergy(currentEnergy);
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
        } catch (NoAssociationException e) {
            tpa.addAssociation(team, this);
        }

    }

    @Override
    public void removeTeam() throws IllegalArgumentException, NoAssociationException {
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
    public JSONObject toJSON() {
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
    public void fromJSON(JSONObject json) {
        setID((int) json.get(id));
        setName((String) json.get(name));
        setCurrentEnergy((int) json.get(currentEnergy));
        setExperiencePoints((int) json.get(experiencePoints));
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
        return this.id - o.getID();
    }

    /*public int compareTo(Player o, PlayerFilter filter) {
        switch (filter) {
            case ID:
                return this.id - o.getID();
            case NAME:
                return this.name.compareTo(o.getName());
            case TEAM:
                try {
                    return this.getTeam().getName().compareTo(o.getTeam().getName());
                } catch (NoAssociationException e) {
                    return -1;
                }
            case CURRENT_ENERGY:
                return this.currentEnergy - o.getCurrentEnergy();
            case LEVEL:
                return this.level - o.getLevel();
            case EXPERIENCE_POINTS:
                return this.experiencePoints - o.getExperiencePoints();
            default:
                return 0;
        }
    }*/

    @Override
        public String toString() {
            String team = "None";
            try {
                team = getTeam().getName();
            } catch (NoAssociationException ignored) {}
            return "Player {"
            + "id=" + id
            + ", name=" + name
            + ", team=" + team
            + ", currentEnergy=" + currentEnergy
            + ", level=" + getLevel()
            + ", experiencePoints=" + experiencePoints + '}';
        }
}


