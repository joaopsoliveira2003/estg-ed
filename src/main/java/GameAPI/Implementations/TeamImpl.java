package GameAPI.Implementations;

import GameAPI.Interfaces.Team;

public class TeamImpl implements Team {

    private String name;

    public TeamImpl(String name) {
        this.name = name;
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
    public String toString() {
        return "TeamImpl{" + "name='" + name + '\'' + '}';
    }
}
