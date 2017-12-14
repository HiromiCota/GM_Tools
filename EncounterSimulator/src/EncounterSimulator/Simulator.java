package EncounterSimulator;

import java.util.Random;

public class Simulator {
    static Entity player = new Entity(20, 10, "Dudeface McGee");
    static Entity monster = new Entity( 10, 10, "Grrface the Meanie");
    static Random D20 = new Random();
    static Random D8 = new Random();
    static Random D6 = new Random();


    private Simulator(){
    }



    private static void announceAttack(Entity attacker){
        System.out.print(attacker.getName() + " is attacking: ");
    }
    private static Boolean attack(Entity attacker, Entity defender) {

        announceAttack(attacker);
        int roll = D20.nextInt(20) + 1;
        System.out.print("They roll a " + roll + " ");
        if (roll >= defender.getAc()) {
            System.out.print("HIT!");
            int damage = D8.nextInt(8) + 1;
            System.out.println("They do " + damage+ " damage!");
            defender.setCurrHp(defender.getCurrHp() - damage);
        }
        else
            System.out.println("MISS!");
        return (defender.getCurrHp() <= 0);
    }
    public static void main(String[] args){
        Simulator simulator = new Simulator();

        do{
            if (attack(simulator.player, simulator.monster))
                break;
            attack(simulator.monster, simulator.player);
            System.out.print("\n");
        } while (simulator.monster.getCurrHp() > 0 && simulator.player.getCurrHp() > 0);
        System.out.print("\n");
        if (simulator.monster.getCurrHp() <= 0)
            System.out.println(simulator.monster.getName() + " has been defeated!");
        else
            System.out.println(simulator.player.getName() + " has been slain!");
    }
}


