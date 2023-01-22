package Game.Entities;

import Collections.Lists.UnorderedListADT;
import Exceptions.IllegalArgumentException;
import Game.Exceptions.NoAssociationException;

public class PlayerImpl implements Player {

    PortalPlayerAssociation ppa = PortalPlayerAssociation.getInstance();
    TeamPlayerAssociation tpa = TeamPlayerAssociation.getInstance();

    private int id;
    private String name;
    private int currentEnergy;
    private int level;
    private int experiencePoints;

    public PlayerImpl(int id, String name) {
        setID(id);
        setName(name);
        setCurrentEnergy(100);
        setLevel(0);
        setExperiencePoints(0);
    }

    public PlayerImpl(int id, String name, Team team, int currentEnergy, int level, int experiencePoints) {
        setID(id);
        setName(name);
        setTeam(team);
        setCurrentEnergy(currentEnergy);
        setLevel(level);
        setExperiencePoints(experiencePoints);
        setTeam(team);
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
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) throws IllegalArgumentException {
        if (level < 0) {
            throw new IllegalArgumentException("Level cannot be negative");
        }
        this.level = level;
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
        public String toString() {
            return "Player {"
            + "id=" + id
            + ", name=" + name
            + ", team=" + getTeam().getName()
            + ", currentEnergy=" + currentEnergy
            + ", level=" + level
            + ", experiencePoints=" + experiencePoints + '}';
        }
}


