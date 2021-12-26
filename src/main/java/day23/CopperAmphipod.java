package day23;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class CopperAmphipod extends Amphipod {



    public CopperAmphipod(int row, int column) {
        this(row, column, 0);
    }

    public CopperAmphipod(int row, int column, int moves) {
        super(row, column, moves);
    }

    @Override
    protected String getType(){
        return "copper";
    }

    @Override
    protected int getEnergyPerStep() {
        return 100;
    }

    @Override
    protected int getBaseColumn() {
        return 6;
    }

    public Amphipod cloneAmphipod() {
        return new CopperAmphipod(row, column, moves);
    }
}
