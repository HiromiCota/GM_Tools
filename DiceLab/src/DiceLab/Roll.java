package DiceLab;

public class Roll {
    int dice, faces, modifier, dropHigh, dropLow;

    public Roll(int dice, int faces, int modifier, int dropHigh, int dropLow) {
        this.dice = dice;
        this.faces = faces;
        this.modifier = modifier;
        this.dropHigh = dropHigh;
        this.dropLow = dropLow;
    }

    public int getDice() { return dice; }

    public int getFaces() { return faces; }

    public int getModifier() {return modifier; }

    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append(dice);
        output.append('d');
        output.append(faces);
        if (dropHigh >0) {
            output.append(" Drop highest ");
            output.append(dropHigh);
        }
        if (dropLow > 0){
            output.append(" Drop lowest ");
            output.append(dropLow);
        }
        return output.toString();
    }
}
