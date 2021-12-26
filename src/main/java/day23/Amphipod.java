package day23;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.*;

@Getter
@EqualsAndHashCode
public abstract class Amphipod {

    int row;
    int column;
    int moves;

    protected abstract String getType();

    public Amphipod( int row, int column, int moves) {
        this.row = row;
        this.column = column;
        this.moves = moves;
    }

    public Amphipod(Amphipod amphipod) {
        this.row = amphipod.getRow();
        this.column = getColumn();
    }

    protected boolean isInHallway(){
        return row == 0;
    }

    public int move(List<List<Optional<Amphipod>>>  grid, Coordinate coordinate){
        int stepsToSet = Math.abs(coordinate.getColumn() -column) + Math.abs(coordinate.getRow() - row);

        List<Optional<Amphipod>> gridRow =  grid.get(coordinate.getRow());
        gridRow.set(coordinate.getColumn(), Optional.of(this));
        grid.set(coordinate.getRow(), gridRow);

        List<Optional<Amphipod>> currentRow =  grid.get(row);
        currentRow.set(column, Optional.empty());
        grid.set(row, currentRow);

        this.row = coordinate.getRow();
        this.column = coordinate.getColumn();
        moves++;
        return stepsToSet * getEnergyPerStep();
    }



    protected abstract int getEnergyPerStep();

    public List<Coordinate> possibleMoves(List<List<Optional<Amphipod>>>  grid){
        if(moves == 2){
            return new ArrayList<>();
        }
        if(isInHallway()){
            if(canMoveToBase(grid)){
                return Arrays.asList(getFreeSpotInBaseColumn(grid));
            }
            return new ArrayList<>();
        }
        else{
            for(int i = row-1; i>=0; i--){
                if(grid.get(i).get(column).isPresent()){
                    return new ArrayList<>();
                }
            }
            return getSpacesToGoToInHallway(grid);
        }
    }

    public boolean canMove(List<List<Optional<Amphipod>>>  grid){
        return !possibleMoves(grid).isEmpty();
    }

    private List<Coordinate> getSpacesToGoToInHallway(List<List<Optional<Amphipod>>> grid) {
        ArrayList<Coordinate> possibleMoves = new ArrayList();
        for(int j = column+1; j< grid.get(0).size(); j++){
            if(j == 2 || j == 4 || j == 6 || j == 8){
                continue;
            }
            if(grid.get(0).get(j).isPresent()){
                break;
            }
            possibleMoves.add(new Coordinate(0, j));
        }
        for(int j =column-1; j>=0; j--){
            if(j == 2 || j == 4 || j == 6 || j == 8){
                continue;
            }
            if(grid.get(0).get(j).isPresent()){
                break;
            }
            possibleMoves.add(new Coordinate(0, j));
        }
        return possibleMoves;
    }

    private Coordinate getFreeSpotInBaseColumn(List<List<Optional<Amphipod>>>  grid){
        for(int i = grid.size()-1; i>0; i--){
            if(grid.get(i).get(getBaseColumn()).isEmpty()){
                return new Coordinate(i, getBaseColumn());
            }
        }
        throw new RuntimeException("No free spot found in base");
    }

    public boolean canMoveToBase(List<List<Optional<Amphipod>>>  grid){
        int baseColumn = getBaseColumn();
        int direction = 1;
        if(baseColumn<column){
            direction = -1;
        }
        int index = column;
        while(index!=baseColumn){
            index+=direction;
            if(grid.get(0).get(index).isPresent()){
                return false;
            }
        }
        for(int i = 1; i< grid.size(); i++){
            if(grid.get(i).get(baseColumn).isPresent() && grid.get(i).get(baseColumn).get().getType() != getType()){
                return false;
            }
        }
        return true;
    }

    protected abstract int getBaseColumn();
    public abstract Amphipod cloneAmphipod();

}
