package EncounterSimulator;

/**
 * This class is the building block for all combatants.
 * maxHp is the highest their hit points can go
 * currHp is their current hit point total.
 * ac is their armor class
 * name is their name
 */
class Entity {
    private int maxHp;
    private int currHp;
    private int ac;
    private String name;
    private Attack attack;
    private int toHitMod;
    private int damageMod;

    // Main constructor defaults to fully healed
    Entity(int maxHp, int ac, String name, Attack attack, int toHitMod, int damageMod) {
        this.maxHp = maxHp;
        this.currHp = maxHp;
        this.ac = ac;
        this.name = name;
        this.attack = attack;
        this.toHitMod = toHitMod;
        this.damageMod = damageMod;
    }

    // Abbreviated constructor has 0 for to hit and damage modifiers
    Entity(int maxHp, int ac, String name, Attack attack) {
        this.maxHp = maxHp;
        this.currHp = maxHp;
        this.ac = ac;
        this.name = name;
        this.attack = attack;
        this.toHitMod = 0;
        this.damageMod = 0;
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
     * @param healAmount The amount of incoming healing
     * @return The amount of healing wasted by hitting the max HP ceiling.
     */
    private int heal(int healAmount){
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

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    private int getMaxHp() {
        return maxHp;
    }
    int getToHit(){
        return toHitMod + getAttack().getToHitModifier();
    }
    public int getDamage(){
        return damageMod + getAttack().getDamageModifier();
    }

    /**
     * Builds a string with the most pertinent stats for the entity
     *
     * Example string: &gt;DudeFace McGee&lt; strength + 3 1d8+2 (Long sword) AC: 10 HP: 10
     * @return A string with a fair amount of the entity's information
     */
    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append("<" + getName() + "> ");
        output.append(getAttack().getAttackAttribute().toString() + "+" + getAttack().getToHitModifier() + "  ");
        output.append(getAttack().getNumberOfDice() + "d" + getAttack().getDieSize() + "+" + getAttack().getDamageModifier());
        output.append("(" + getAttack().getAttackName() + ") ");
        output.append("AC:" + getAc() + " HP:" + getMaxHp());
        return output.toString();
    }
}
