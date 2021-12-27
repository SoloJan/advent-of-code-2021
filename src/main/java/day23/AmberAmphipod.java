package day23;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
public class AmberAmphipod extends Amphipod {


    public AmberAmphipod(int row, int column) {
        this(row, column, 0);

    }

    @Override
    protected String getType() {
        return  "amber";
    }

    public AmberAmphipod(int row, int column, int moves) {
        super(row, column, moves);
    }

    @Override
    public String toString(){
        return "A" + row + column;
    }

    @Override
    protected int getEnergyPerStep() {
        return 1;
    }

    @Override
    protected int getBaseColumn() {
        return 2;
    }


    public Amphipod cloneAmphipod() {
        return new AmberAmphipod(row, column, moves);
    }


}
