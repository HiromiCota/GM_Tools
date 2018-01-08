package EncounterSimulator;

import java.util.ArrayList;

public class Armory {
    final Attack LONG_SWORD = new Attack("Long sword", Attack.AttackType.slashing,1,8);

    private ArrayList<Attack> weapons = new ArrayList();

    Armory(){
        weapons.add(LONG_SWORD);
        weapons.add(new Attack("Short sword", Attack.AttackType.slashing, 1,6));
        weapons.add(new Attack("Heavy mace", Attack.AttackType.bashing, 1, 6));
        weapons.add(new Attack("Dagger", Attack.AttackType.piercing, 1, 4));
        weapons.add(new Attack("Great axe", Attack.AttackType.slashing, 1,12));
    }

    Attack get(int index){ return weapons.get(index);}
    int size() { return weapons.size();}
}
