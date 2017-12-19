package EncounterSimulator;

import java.util.Random;
import java.util.ArrayList;

/**
 * This is the core class for the combat simulator. It currently contains:
 * 1 player character
 * 1 monster
 * They are very generic, only having hit points and AC, with no modifiers.
 *
 * The purpose of this class is to run simulations of fights with varying conditions in order to get an estimate
 * as to what the likely outcome will be.
 */
public class Simulator {
    final Attack LONG_SWORD = new Attack("Long sword", Attack.AttackType.slashing,1,8);


    //Class level variables
    ArrayList<Entity> TeamOne = new ArrayList<>();
    ArrayList<Entity> TeamTwo = new ArrayList<>();
    static Random dice = new Random();

    //Default constructor gives default monsters.
    public Simulator(){
        TeamOne.add( new Entity(10, 10, "Dudeface McGee",LONG_SWORD));
        TeamTwo.add( new Entity(10, 10, "Grrface the Meanie",LONG_SWORD));
    }

    public Simulator(ArrayList<Entity> TeamOne, ArrayList<Entity> TeamTwo){
        this.TeamOne.addAll(TeamOne);
        this.TeamTwo.addAll(TeamTwo);
    }

    static void announceAttack(Entity attacker){ System.out.print(attacker.getName() + " is attacking: "); }

    /**
     * Runs a single attack exchange between the two combatant Entities.
     * @param attacker The Entity with the higher initiative
     * @param defender The Entity with the lower initiative
     * @param battleLog Whether or not the blow-by-blow battle log should go to sout
     * @return = true if the attacker won. False otherwise.
     */
    static Boolean attack(Entity attacker, Entity defender, boolean battleLog) {
        if (battleLog)
            announceAttack(attacker);
        int roll = roll() + attacker.getToHit();
        if (battleLog)
            System.out.print("They roll a " + roll + " ");
        if (roll >= defender.getAc()) {
            if (battleLog)
                System.out.print("HIT!");
            int damage = roll(attacker.getAttack().getNumberOfDice(),attacker.getAttack().getDieSize());
            if (battleLog)
                System.out.println("They do " + damage+ " damage!");
            defender.setCurrHp(defender.getCurrHp() - damage);
        }else{
            if (battleLog)
                System.out.println("MISS!");
        }
        if (battleLog)
            System.out.print("\n");
        return (defender.getCurrHp() <= 0);
    }

    /**
     * Gives a random number from 1 to sidesPerDie, up to numberOfDice.
     * @param numberOfDice Please be a positive number
     * @param sidesPerDie Please be a positive number
     * @return dice total
     */
    static int roll(int numberOfDice, int sidesPerDie){
        if (numberOfDice <= 0)
            return 0;
        return 1 + dice.nextInt(sidesPerDie) + roll(numberOfDice -1, sidesPerDie);
    }

    /**
     * Rolls 1d20. Depends on roll(int,int) method
     * @return A random number between 1 and 20, inclusive
     */
    static int roll(){
        return roll(1,20);
    }

    /**
     * Runs a full simulation until one of the Entities dies.
     * @param teamOne Entity that won initiative
     * @param teamTwo Entity that lost initiative
     * @param battleLog If the blow-by-blow battle log should go to sout
     * @return Which entity won. 0 = teamOne, 1 = teamTwo
     */
    static int battle(Entity teamOne, Entity teamTwo, boolean battleLog){
        teamOne.fullHeal();
        teamTwo.fullHeal();
        do{
            if (attack(teamOne, teamTwo, battleLog))
                break;
            attack(teamTwo, teamOne, battleLog);
        } while (teamTwo.getCurrHp() > 0 && teamOne.getCurrHp() > 0);

        if (teamTwo.getCurrHp() <= 0) {
            if (battleLog)
                System.out.println(teamTwo.getName() + " has been defeated!");
            return 1;
        }else{
            if (battleLog)
                System.out.println(teamOne.getName() + " has been slain!");
            return 0;
        }
    }

    static void announceWins(Entity entityName, int wins, int fightsTotal){
        System.out.println("The " + entityName.getName() + " won " + wins +" times out of " + fightsTotal);
        System.out.println(entityName.getName() + "'s win percentage is: " + String.format("%,.2f%%", 100.0* (float)wins/fightsTotal));
    }


}


