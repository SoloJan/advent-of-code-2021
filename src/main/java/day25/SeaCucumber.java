package day25;

import lombok.Getter;

import java.util.Optional;

@Getter
public class SeaCucumber {

    public int row;
    public int column;
    boolean movesEast = false;

    public SeaCucumber(int row, int column,  Character direction){
        this.row = row;
        this.column = column;
        if(direction == '>'){
            movesEast = true;
        }
    }

    boolean canMove(SeaCucumber [][] grid){
        if(movesEast){
            if(column == grid[row].length-1){
                return grid[row][0] == null;
            }
            return grid[row][column+1] == null;
        }
        if(row == grid.length-1){
            return grid[0][column] == null;
        }
        return grid[row+1][column] == null;
    }

    void move(SeaCucumber[][] grid){
        if(movesEast){
            if(column == grid[row].length-1){
                column =0;
            }
            else{
                column++;
            }
        }
        else{
            if(row == grid.length-1){
                 row = 0;
            }
            else{
                row++;
            }
        }
    }


}
