package GameAPI.Implementations;

import GameAPI.Interfaces.Player;
import GameAPI.Interfaces.Team;

public class PlayerImpl implements Player {

    private int id;
    private String name;
    private Team team;
    private int currentEnergy;
    private int level;
    private int experiencePoints;

    public PlayerImpl(int id, String name, Team team, int currentEnergy, int level, int experiencePoints) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.currentEnergy = currentEnergy;
        this.level = level;
        this.experiencePoints = experiencePoints;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
          return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int getCurrentEnergy() {
        return currentEnergy;
    }

    @Override
    public void setCurrentEnergy(int energy) {
        this.currentEnergy = energy;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getExperiencePoints() {
        return experiencePoints;
    }

    @Override
    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    @Override
        public String toString() {
            return "PlayerImpl{"
            + "name=" + name
            + ", team=" + team
            + ", currentEnergy=" + currentEnergy
            + ", level=" + level
            + ", experiencePoints=" + experiencePoints + '}';
        }
}


