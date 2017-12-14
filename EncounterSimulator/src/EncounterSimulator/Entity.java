package EncounterSimulator;

class Entity {
    int maxHp;
    int currHp;
    int ac;
    String name;

    Entity(int maxHp, int ac, String name) {
        this.maxHp = maxHp;
        this.currHp = maxHp;
        this.ac = ac;
        this.name = name;
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
}
