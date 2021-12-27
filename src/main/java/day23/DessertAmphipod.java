package day23;

import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode(callSuper = true)
public class DessertAmphipod extends Amphipod {

    public DessertAmphipod(int row, int column) {
        this(row, column, 0);
    }

    public DessertAmphipod(int row, int column, int moves) {
        super(row, column, moves);
    }

    @Override
    protected String getType(){
        return "dessert";
    }


    @Override
    protected int getEnergyPerStep() {
        return 1000;
    }

    @Override
    protected int getBaseColumn() {
        return 8;
    }

    public Amphipod cloneAmphipod() {
        return new DessertAmphipod(row, column, moves);
    }

    @Override
    public String toString(){
        return "C" + row + column;
    }

}
