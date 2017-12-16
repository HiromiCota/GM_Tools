package EncounterSimulator;

/**
 * This class is the building block for all combatants.
 * maxHp is the highest their hit points can go
 * currHp is their current hit point total.
 * ac is their armor class
 * name is their name
 */
class Entity {
    int maxHp;
    int currHp;
    int ac;
    String name;

    // Main constructor defaults to fully healed
    Entity(int maxHp, int ac, String name) {
        this.maxHp = maxHp;
        this.currHp = maxHp;
        this.ac = ac;
        this.name = name;
    }

    /**
     * Heals up to their maximum hit points, without concern for overheal.
     */
    void fullHeal(){
        heal(maxHp);
    }

    /**
     * Heals up to maximum hit point or healAmount, whichever is lesser.
     *
     * @param healAmount
     * @return The amount of healing wasted by hitting the max HP ceiling.
     */
    int heal(int healAmount){
        currHp += healAmount;
        if (currHp > maxHp){
            int overHeal = currHp - maxHp;
            currHp = maxHp;
            return overHeal;
        }
        return 0;
    }

    int getCurrHp() {
        return currHp;
    }

    void setCurrHp(int currHp) {
        this.currHp = currHp;
    }

    int getAc() {
        return ac;
    }

    String getName() {
        return name;
    }
}
