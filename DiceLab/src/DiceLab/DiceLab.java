package DiceLab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DiceLab {
    //Consts
    private static final int NUMBER_OF_SIMULATIONS = 10000;
    private static final int MAX_VALUE = 300; //If the maximum possible value of a roll exceeds this, runtime errors are possible.
    private static final int ARRAY_SIZE = MAX_VALUE +1;

    //Structural Variables
    private static Random diceBag = new Random();
    private static int median, mode;
    private static double mean;
    private static int[] outcomes = new int[ARRAY_SIZE];
    private static int highestValue = 0;
    private static int lowestValue = Integer.MAX_VALUE;

    //Fun variables
    static ArrayList<Roll> rolls = new ArrayList<>();

    public DiceLab() {
    }

    void addRoll(Roll roll){
        rolls.add(roll);
    }

    void addRoll(int dice, int faces, int modifier, int high, int low){
        addRoll(new Roll(dice,faces,modifier, high, low));
    }
    void addRoll(int dice, int faces){
        addRoll(new Roll(dice,faces,0,0,0));
    }

    int getRoll(Roll roll){
        int output = 0;
        int[] rollArray = new int[roll.dice];
        for (int i = 0; i < roll.dice; i++) { //This costs n
            rollArray[i] = diceBag.nextInt(roll.faces) +1;
        }
        Arrays.sort(rollArray); //This costs n log n
        for (int i = roll.dropLow; i < roll.dice - roll.dropHigh ; i++){ //This costs another n
            output += rollArray[i];
        }
        output += roll.modifier;
        return output;
    }
    void reset(){
        for (int i = lowestValue; i <= highestValue; i++){
            outcomes[i]=0;
        }
        highestValue =0;
        lowestValue =0;
        mean = 0;
        for (int i = 0; i < rolls.size(); i++)
            rolls.remove(i);
    }
    void rollDice(){
        highestValue =0;
        lowestValue = Integer.MAX_VALUE;
        int tempRoll;
        for (int i = 0; i < NUMBER_OF_SIMULATIONS; i++){ // This costs NUMBER_OF_SIMULATIONS LINEAR
            for (Roll roll : rolls){ //This costs rolls.size() LINEAR
                tempRoll = getRoll(roll); //This costs 2(roll.dice) + (roll.dice)log(roll.dice) N LOG N
                outcomes[tempRoll]++;
                if (tempRoll > highestValue)
                    highestValue = tempRoll;
                if (tempRoll < lowestValue)
                    lowestValue = tempRoll;
            }
        }
    }
    void findMean(){
        int total = 0;
        for (int i = lowestValue; i <= highestValue; i++){
            total += outcomes[i] * i;
        }
        mean = (double) total / (double) NUMBER_OF_SIMULATIONS;
    }
    void printArray(){
        final int NUMBERS_PER_LINE = 20;
        int numberThisLine = 0;
        for (Integer result : outcomes){
            numberThisLine++;
            if (numberThisLine % NUMBERS_PER_LINE == 0)
                System.out.print('\n');
            System.out.print(result + " ");
        }
    }
    static String announceRolls(DiceLab roller){
        StringBuilder output = new StringBuilder();
        for (Roll roll : roller.rolls){
            output.append(roll.toString());
            output.append('\n');
        }
        return output.toString();
    }
    static void announceWrap(DiceLab roller){
        System.out.print("I rolled: ");
        System.out.print(announceRolls(roller));
        System.out.println("The mean is: " + mean + '\n');
    }
    public static void main(String[] args){
        DiceLab roller = new DiceLab();
        roller.addRoll(2,6);
        roller.rollDice();
        roller.findMean();
        DiceLab.announceWrap(roller);

        roller.reset();
        roller.addRoll(3,6);
        roller.rollDice();
        roller.findMean();
        DiceLab.announceWrap(roller);

        roller.reset();
        roller.addRoll(4,6);
        roller.rollDice();
        roller.findMean();
        DiceLab.announceWrap(roller);

        roller.reset();
        roller.addRoll(4,6,0,0,1);
        roller.rollDice();
        roller.findMean();
        DiceLab.announceWrap(roller);

        roller.reset();
        roller.addRoll(1,20);
        roller.rollDice();
        roller.findMean();
        DiceLab.announceWrap(roller);

        roller.reset();
        roller.addRoll(2,20,0,0,1);
        roller.rollDice();
        roller.findMean();
        DiceLab.announceWrap(roller);

        roller.reset();
        roller.addRoll(3,20,0,0,2);
        roller.rollDice();
        roller.findMean();
        DiceLab.announceWrap(roller);
    }
}
