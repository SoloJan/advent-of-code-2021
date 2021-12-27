package day23;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class BronzeAmphipod extends Amphipod {



    public BronzeAmphipod(int row, int column) {
        this(row, column, 0);

    }

    public BronzeAmphipod(int row, int column, int moves) {
        super(row, column, moves);
    }

    @Override
    protected String getType(){
        return "bronze";
    }

    @Override
    protected int getEnergyPerStep() {
        return 10;
    }

    @Override
    protected int getBaseColumn() {
        return 4;
    }

    public Amphipod cloneAmphipod() {
        return new BronzeAmphipod(row, column, moves);
    }

    @Override
    public String toString(){
        return "B" + row + column;
    }

}
