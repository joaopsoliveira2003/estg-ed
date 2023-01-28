package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;
import org.json.simple.JSONObject;

import static Game.Utilities.Validations.validateString;

/**
 * TeamImpl defines the implementation of a team in the game.
 */
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
        validateString(name, "Name");
        this.name = name;
    }

    @Override
    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
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
