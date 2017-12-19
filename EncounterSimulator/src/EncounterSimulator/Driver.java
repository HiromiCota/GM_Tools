package EncounterSimulator;

import static EncounterSimulator.Simulator.*;

class Driver {

    /**
     * Driver for the simulator
     * @param args Whatever command line arguments get passed in. Currently, not supported
     */
    public static void main(String[] args){
        Simulator simulator = new Simulator();
        int playerWins = 0;
        int monsterWins = 0;
        final int FIGHTS = 10000;

        for (int i = 0; i < FIGHTS; i++)
        {
            if (battle(simulator.TeamOne.get(0),simulator.TeamTwo.get(0),false) == 0)
                monsterWins++;
            else
                playerWins++;
        }
        System.out.println("\nCombatants: " + simulator.TeamOne.get(0).toString() + " vs " + simulator.TeamTwo.get(0).toString() );
        announceWins(simulator.TeamOne.get(0),playerWins,FIGHTS);
        announceWins(simulator.TeamTwo.get(0),monsterWins,FIGHTS);
        playerWins =0;
        monsterWins = 0;
        System.out.println("\nCombatants: " + simulator.TeamOne.get(0).toString() + " vs " + simulator.TeamTwo.get(0).toString() );
        for (int i = 0; i < FIGHTS; i++)
        {
            if (battle(simulator.TeamTwo.get(0), simulator.TeamOne.get(0),false) == 1)
                monsterWins++;
            else
                playerWins++;
        }
        announceWins(simulator.TeamOne.get(0),playerWins,FIGHTS);
        announceWins(simulator.TeamTwo.get(0),monsterWins,FIGHTS);

    }
}
