package EncounterSimulator;

import java.util.ArrayList;

public class Team extends ArrayList<Creature>{
    private ArrayList<Creature> team = new ArrayList<>();


    /**
     * Builds a list of everyone in the party.
     * @return A potentially multi-line string of all party members
     */
    public String toString(){
        StringBuilder output = new StringBuilder();
        for (Creature creature : team){
            output.append(creature.toString());
            output.append("\n");
        }
        return output.toString();
    }

    void fullHeal(){
        for (Creature creature : team){
            creature.fullHeal();
        }
    }

}
