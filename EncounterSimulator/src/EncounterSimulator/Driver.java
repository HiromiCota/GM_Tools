package EncounterSimulator;

import javax.swing.*;

import static EncounterSimulator.Simulator.*;

class Driver {

    /**
     * Driver for the simulator
     * @param args Whatever command line arguments get passed in. Currently, not supported
     */
    public static void main(String[] args){
        JFrame frame = new JFrame("ELF");
        SimulatorGUI GUI = new SimulatorGUI();
        frame.setContentPane(GUI);

        /*
        Simulator simulator = new Simulator();
        int playerWins = 0;
        int monsterWins = 0;

        final int FIGHTS = 10000;


        for (int i = 0; i < FIGHTS; i++)
        {
            if (battle(simulator.Players.get(0),simulator.Monsters.get(0),false) == 0)
                monsterWins++;
            else
                playerWins++;
        }
        System.out.println("\nCombatants: " + simulator.Players.get(0).toString() + " vs " + simulator.Monsters.get(0).toString() );
        announceWins(simulator.Players.get(0),playerWins,FIGHTS);
        announceWins(simulator.Monsters.get(0),monsterWins,FIGHTS);
        playerWins =0;
        monsterWins = 0;
        System.out.println("\nCombatants: " + simulator.Players.get(0).toString() + " vs " + simulator.Monsters.get(0).toString() );
        for (int i = 0; i < FIGHTS; i++)
        {
            if (battle(simulator.Monsters.get(0), simulator.Players.get(0),false) == 1)
                monsterWins++;
            else
                playerWins++;
        }
        announceWins(simulator.Players.get(0),playerWins,FIGHTS);
        announceWins(simulator.Monsters.get(0),monsterWins,FIGHTS);
        */
    }
}
