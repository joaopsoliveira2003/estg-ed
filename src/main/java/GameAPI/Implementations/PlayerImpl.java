package GameAPI.Implementations;

import Collections.Implementations.Lists.UnorderedListADT;
import GameAPI.Exceptions.InvalidArgumentException;
import GameAPI.Exceptions.NoAssociationException;
import GameAPI.Interfaces.Player;
import GameAPI.Interfaces.Portal;
import GameAPI.Interfaces.Team;

public class PlayerImpl implements Player {

    PortalPlayerAssociation ppa = PortalPlayerAssociation.getInstance();
    TeamPlayerAssociation tpa = TeamPlayerAssociation.getInstance();

    private int id;
    private String name;
    private int currentEnergy;
    private int level;
    private int experiencePoints;

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
    public void setID(int id) throws InvalidArgumentException {
        if (id < 0) {
            throw new InvalidArgumentException("ID cannot be negative");
        }
        this.id = id;
    }

    @Override
    public String getName() {
          return name;
    }

    @Override
    public void setName(String name) throws InvalidArgumentException {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new InvalidArgumentException("Name cannot be null, empty or blank");
        }
        this.name = name;
    }

    @Override
    public Team getTeam() throws NoAssociationException {
        return tpa.getTeam(this);
    }

    @Override
    public void setTeam(Team team) throws InvalidArgumentException {
        if (team == null) {
            throw new InvalidArgumentException("Team cannot be null");
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
    public void setCurrentEnergy(int energy) throws InvalidArgumentException {
        if (energy < 0) {
            throw new InvalidArgumentException("Energy cannot be negative");
        }
        this.currentEnergy = energy;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) throws InvalidArgumentException {
        if (level < 0) {
            throw new InvalidArgumentException("Level cannot be negative");
        }
        this.level = level;
    }

    @Override
    public int getExperiencePoints() {
        return experiencePoints;
    }

    @Override
    public void setExperiencePoints(int experiencePoints) throws InvalidArgumentException {
        if (experiencePoints < 0) {
            throw new InvalidArgumentException("Experience Points cannot be negative");
        }
        this.experiencePoints = experiencePoints;
    }

    @Override
    public void addExperiencePoints(int experiencePoints) throws InvalidArgumentException {
        if (experiencePoints < 0) {
            throw new InvalidArgumentException("Experience Points cannot be negative");
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
            + "name=" + name
            + ", team=" + getTeam().getName()
            + ", currentEnergy=" + currentEnergy
            + ", level=" + level
            + ", experiencePoints=" + experiencePoints + '}';
        }
}


