package EncounterSimulator;

import java.util.ArrayList;

public class Team {
    ArrayList<Entity> team = new ArrayList<>();


    /**
     * Builds a list of everyone in the party.
     * @return A potentially multi-line string of all party members
     */
    public String toString(){
        StringBuilder output = new StringBuilder();
        for (Entity entity: team){
            output.append(entity.toString());
            output.append("\n");
        }
        return output.toString();
    }

}
