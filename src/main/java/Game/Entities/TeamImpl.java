package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;

public class TeamImpl implements Team {

    private String name;

    public TeamImpl(String name) {
        setName(name);
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TeamImpl team = (TeamImpl) obj;
        return name.equals(team.name);
    }

    @Override
    public String toString() {
        return "Team {" + "name=" + name + '}';
    }
}
