package EncounterSimulator;

public class Attack {
    enum AttackType { slashing, piercing, bashing, fire, ice, magic}
    enum AttackAttribute { strength, dexterity, constitution, wisdom, intelligence, charisma}
    enum DamageType { hitPoints, strength, dexterity, constitution, wisdom, intelligence, charisma}

    private String attackName;
    private AttackType attackType;
    private int numberOfDice;
    private int dieSize;
    private int damageModifier;
    private int toHitModifier;
    private AttackAttribute attackAttribute;
    private DamageType damageType;

    public Attack(String attackName, AttackType attackType, int numberOfDice, int dieSize, int damageModifier, int toHitModifier, AttackAttribute attackAttribute, DamageType damageType) {
        this.attackName = attackName;
        this.attackType = attackType;
        this.numberOfDice = numberOfDice;
        this.dieSize = dieSize;
        this.damageModifier = damageModifier;
        this.toHitModifier = toHitModifier;
        this.attackAttribute = attackAttribute;
        this.damageType = damageType;
    }

    public Attack(String attackName, AttackType attackType, int numberOfDice, int dieSize) {
        this.attackName = attackName;
        this.attackType = attackType;
        this.numberOfDice = numberOfDice;
        this.dieSize = dieSize;
        damageModifier = 0;
        toHitModifier = 0;
        attackAttribute = AttackAttribute.strength;
        damageType = DamageType.hitPoints;
    }

    // Default constructor yields a 1d6 slashing attack
    public Attack() {
        attackName = "Default attack";
        attackType = AttackType.slashing;
        numberOfDice = 1;
        dieSize = 6;
        damageModifier = 0;
        toHitModifier = 0;
        attackAttribute = AttackAttribute.strength;
        damageType = DamageType.hitPoints;
    }

    String getAttackName() {
        return attackName;
    }

    void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    int getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    int getDieSize() {
        return dieSize;
    }

    public void setDieSize(int dieSize) {
        this.dieSize = dieSize;
    }

    int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    int getToHitModifier() {
        return toHitModifier;
    }

    public void setToHitModifier(int toHitModifier) {
        this.toHitModifier = toHitModifier;
    }

    AttackAttribute getAttackAttribute() {
        return attackAttribute;
    }

    public void setAttackAttribute(AttackAttribute attackAttribute) {
        this.attackAttribute = attackAttribute;
    }
}
