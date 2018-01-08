package EncounterSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulatorGUI extends javax.swing.JFrame{
    final int DEFAULT_AC_AND_HP = 10;
    enum CREATURES {PLAYER,MONSTER}
    Simulator simulator = new Simulator();
    int playerWins = 0;
    int monsterWins = 0;
    int fights = 10000;

    public static void main(String[] args){
        JFrame frame = new SimulatorGUI();
        //frame.BasePanel.setVisible(true);
        frame.getContentPane().setVisible(true);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(674,379));

    }

    void initComponents(){
        playerACSpinner.setValue(DEFAULT_AC_AND_HP);
        monsterACSpinner.setValue(DEFAULT_AC_AND_HP);
        playerHPSpinner.setValue(DEFAULT_AC_AND_HP);
        monsterHPSpinner.setValue(DEFAULT_AC_AND_HP);
        fillWeaponBox(playerWeaponCombo);
        fillWeaponBox(monsterWeaponCombo);

        this.add(BasePanel);


        fightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buildCreatures()){
                    //FIGHT!
                    if (PlayerInitRadio.isSelected())
                        simulator.Players.get(0).setInitiative(20);
                    else if (MonsterInitButton.isSelected())
                        simulator.Monsters.get(0).setInitiative(20);
                    else{
                        Simulator.rollInitiative(simulator.Players);
                        Simulator.rollInitiative(simulator.Monsters);
                    }
                    fight(fights);

                    playerOddsLabel.setText(buildOdds(CREATURES.PLAYER));
                    monsterOddsLabel.setText(buildOdds(CREATURES.MONSTER));
                    lethalityBar.setValue(playerWins/fights);
                }

            }
        });
    }

    Boolean buildCreatures(){
        Creature tempPlayer = validateCreature(CREATURES.PLAYER);
        Creature tempMonster = validateCreature(CREATURES.MONSTER);
        if (tempPlayer != null && tempMonster != null){
            simulator.Players.remove(0);
            simulator.Players.add(tempPlayer);
            simulator.Monsters.remove(0);
            simulator.Monsters.add(tempMonster);
        } else{
            return false;
        }
        return true;
    }
    Creature validateCreature(CREATURES creature){
        if (creature == CREATURES.PLAYER){
            return (validateValues(playerNameText,playerACSpinner,playerHPSpinner,playerToHitSpinner,playerDamageSpinner,playerWeaponCombo));
        } else {
            return (validateValues(monsterNameText,monsterACSpinner,monsterHPSpinner,monsterToHitSpinner,monsterDamageSpinner,monsterWeaponCombo));
            }
    }

    Creature validateValues(JTextField textField, JSpinner AC, JSpinner HP, JSpinner toHit, JSpinner damage, JComboBox attack){
        int tempAC, tempHP, tempToHit, tempDamage;
        String tempName;
        Attack tempAttack;
        try{
            tempName = textField.toString();
            tempAC = (Integer) AC.getValue();
            tempHP= (Integer) HP.getValue();
            tempToHit = (Integer) toHit.getValue();
            tempDamage = (Integer) damage.getValue();
            tempAttack = getAttack(attack);
        }
        catch(Exception ex){
            //If any cast or parse fails, we know something's wrong.
            return null;
        }
        return new Creature(tempHP,tempAC,tempName,tempAttack,tempToHit,tempDamage);
    }

    Attack getAttack(JComboBox attack){
        return simulator.armory.get(attack.getSelectedIndex());
    }

    void fillWeaponBox(JComboBox attack){
        for (int i = 0; i < simulator.armory.size();i++){
            attack.addItem(simulator.armory.get(i));
        }
    }

    void fight(int fights){
        monsterWins = 0;
        playerWins = 0;

        for (int i = 0; i < fights; i++)
        {
            if (Simulator.battle(simulator.Players.get(0),simulator.Monsters.get(0),false) == 0)
                monsterWins++;
            else
                playerWins++;
        }
    }
    String buildOdds(CREATURES creature){
        StringBuilder odds = new StringBuilder();
        if (creature == CREATURES.PLAYER){
            odds.append("Player Survival Odds:");
            odds.append(String.format("%4.2f",(double)(playerWins/(double)fights)));

        } else{
            odds.append("Monster Survival Odds:");
            odds.append(String.format("%4.2f",(double)(monsterWins/(double)fights)));
        }
        odds.append("%");
        return odds.toString();
    }

    public SimulatorGUI() {
        initComponents();
    }

    private JPanel BasePanel;
    private JTextField playerNameText;
    private JSpinner playerACSpinner;
    private JComboBox playerWeaponCombo;
    private JLabel playerToHitLabel;
    private JPanel PlayerPanel;
    private JLabel playerNameLabel;
    private JLabel playerACLabel;
    private JLabel playerHPLabel;
    private JLabel playerDamageLabel;
    private JLabel playerWeaponLabel;
    private JSpinner playerHPSpinner;
    private JSpinner playerToHitSpinner;
    private JSpinner playerDamageSpinner;
    private JPanel FooterPanel;
    private JPanel HeaderPanel;
    private JPanel MonsterPanel;
    private JPanel FightPanel;
    private JLabel monsterNameLabel;
    private JTextField monsterNameText;
    private JSpinner monsterACSpinner;
    private JLabel monsterACLabel;
    private JLabel monsterHPLabel;
    private JLabel monsterToHitLabel;
    private JLabel monsterDamageLabel;
    private JLabel monsterWeaponLabel;
    private JSpinner monsterHPSpinner;
    private JSpinner monsterToHitSpinner;
    private JSpinner monsterDamageSpinner;
    private JComboBox monsterWeaponCombo;
    private JButton fightButton;
    private JRadioButton PlayerInitRadio;
    private JPanel InitiativePanel;
    private JRadioButton MonsterInitButton;
    private JRadioButton RandomInitButton;
    private JProgressBar lethalityBar;
    private JLabel lethalityLevelLabel;
    private JLabel initiativeLabel;
    private JPanel LethalityPanel;
    private JLabel playerOddsLabel;
    private JLabel monsterOddsLabel;
    private JLabel titleLabel;


}
