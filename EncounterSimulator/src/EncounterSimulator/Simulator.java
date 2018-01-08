package EncounterSimulator;

import java.util.Random;
import java.util.ArrayList;
import EncounterSimulator.Armory.*;

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
    Armory armory = new Armory();

    //Class level variables
    Team Players = new Team();
    Team Monsters = new Team();

    private static Random dice = new Random();
    private static int PlayerWins = 0;
    private static int Battles = 0;

    //Default constructor gives default monsters.
    public Simulator(){
        Players.add( new Creature(10, 10, "Dudeface McGee",armory.LONG_SWORD));
        Monsters.add( new Creature(10, 10, "Grrface the Meanie",armory.LONG_SWORD));
        initSimulator();
    }

    public Simulator(ArrayList<Creature> Players, ArrayList<Creature> Monsters){
        this.Players.addAll(Players);
        this.Monsters.addAll(Monsters);
        initSimulator();
    }

    private void initSimulator(){
        rollInitiative(Players);
        rollInitiative(Monsters);
        Players.fullHeal();
        Monsters.fullHeal();
    }

    private static void announceAttack(Creature attacker){ System.out.print(attacker.getName() + " is attacking: "); }

    static void rollInitiative(Team team){
        for (Creature creature : team){
            creature.setInitiative(roll());
        }
    }

    /**
     * Runs a single attack exchange between the two combatant Entities.
     * @param attacker The Creature with the higher initiative
     * @param defender The Creature with the lower initiative
     * @param battleLog Whether or not the blow-by-blow battle log should go to sout
     * @return = true if the attacker won. False otherwise.
     */
    private static Boolean attack(Creature attacker, Creature defender, boolean battleLog) {
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
    private static int roll(int numberOfDice, int sidesPerDie){
        if (numberOfDice <= 0)
            return 0;
        return 1 + dice.nextInt(sidesPerDie) + roll(numberOfDice -1, sidesPerDie);
    }

    /**
     * Rolls 1d20. Depends on roll(int,int) method
     * @return A random number between 1 and 20, inclusive
     */
    private static int roll(){
        return roll(1,20);
    }

    /**
     * Runs a full simulation until one of the Entities dies.
     * @param Players Creature that won initiative
     * @param Monsters Creature that lost initiative
     * @param battleLog If the blow-by-blow battle log should go to sout
     * @return Which entity won. 0 = Players, 1 = Monsters
     */
    static int battle(Creature Players, Creature Monsters, boolean battleLog){
        Players.fullHeal();
        Monsters.fullHeal();
        do{
            if (attack(Players, Monsters, battleLog))
                break;
            attack(Monsters, Players, battleLog);
        } while (Monsters.getCurrHp() > 0 && Players.getCurrHp() > 0);

        if (Monsters.getCurrHp() <= 0) {
            if (battleLog)
                System.out.println(Monsters.getName() + " has been defeated!");
            return 1;
        }else{
            if (battleLog)
                System.out.println(Players.getName() + " has been slain!");
            return 0;
        }
    }

    static void announceWins(Creature creatureName, int wins, int fightsTotal){
        System.out.println("The " + creatureName.getName() + " won " + wins +" times out of " + fightsTotal);
        System.out.println(creatureName.getName() + "'s win percentage is: " + String.format("%,.2f%%", 100.0* (float)wins/fightsTotal));
    }


}


