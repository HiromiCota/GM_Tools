package EncounterSimulator;

import java.util.Random;

public class Simulator {
    static Entity player = new Entity(20, 10, "Dudeface McGee");
    static Entity monster = new Entity( 10, 10, "Grrface the Meanie");
    static Random dice = new Random();

    private Simulator(){    }

    private static void announceAttack(Entity attacker){
        System.out.print(attacker.getName() + " is attacking: ");
    }
    private static Boolean attack(Entity attacker, Entity defender, boolean battleLog) {
        if (battleLog)
            announceAttack(attacker);
        int roll = roll(1,20);
        if (battleLog)
            System.out.print("They roll a " + roll + " ");
        if (roll >= defender.getAc()) {
            if (battleLog)
                System.out.print("HIT!");
            int damage = roll(1,8);
            if (battleLog)
                System.out.println("They do " + damage+ " damage!");
            defender.setCurrHp(defender.getCurrHp() - damage);
        }else{
            if (battleLog)
                System.out.println("MISS!");
        }
        return (defender.getCurrHp() <= 0);
    }
    private static int roll(int numberOfDice, int sidesPerDie){
        if (numberOfDice <= 0)
            return 0;
        return 1 + dice.nextInt(sidesPerDie) + roll(numberOfDice -1, sidesPerDie);
    }
    private static int battle(Entity teamOne, Entity teamTwo, boolean battleLog){
        teamOne.fullHeal();
        teamTwo.fullHeal();
        do{
            if (attack(teamOne, teamTwo, battleLog))
                break;
            attack(teamTwo, teamOne, battleLog);
            System.out.print("\n");
        } while (teamTwo.getCurrHp() > 0 && teamOne.getCurrHp() > 0);
        System.out.print("\n");
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

    private static void announceWins(Entity entityName, int wins, int fightsTotal){
        System.out.println("The " + entityName.getName() + " won " + wins +" times out of " + fightsTotal);
    }
    public static void main(String[] args){
        Simulator simulator = new Simulator();
        int playerWins = 0;
        int monsterWins = 0;
        final int FIGHTS = 1000;

        for (int i = 0; i < FIGHTS; i++)
        {
            if (simulator.battle(player,monster,false) == 0)
                monsterWins++;
            else
                playerWins++;
        }
        announceWins(player,playerWins,FIGHTS);
        announceWins(monster,monsterWins,FIGHTS);

    }
}


