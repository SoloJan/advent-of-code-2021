package day25;

import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;
import static util.StringUtil.toCharacterList;

@Getter
public class CucumberOrganizer {

    SeaCucumber [][] grid;
    int nrOfRows ;
    int nrOfColumns;

    public CucumberOrganizer(String fileName){
        List<List<Character>> fileContent = readFilePerLine(fileName).stream().map(s -> toCharacterList(s)).collect(Collectors.toList());
        nrOfRows = fileContent.size();
        nrOfColumns = fileContent.get(0).size();
        grid = new SeaCucumber[nrOfRows][nrOfColumns];
        for(int row = 0; row < nrOfRows; row++){
            for(int column = 0; column < nrOfColumns; column++){
                if(fileContent.get(row).get(column) != '.'){
                    grid[row][column] = new SeaCucumber(row, column, fileContent.get(row).get(column));
                }
            }
        }
    }

    public long getRoundsToStabalize(){
        long count  = 0L;
        while(true){
         //   printState(count);
            boolean move = move();
            if(move){
                count++;
            }
            else {
                return count+1;
            }
        }
    }

    public boolean move(){
        boolean movesPossible = false;
        SeaCucumber [][] moveEastGrid = new SeaCucumber[nrOfRows][nrOfColumns];
        for(int row =0; row<nrOfRows; row++){
            for(int column =0; column<nrOfColumns; column++){
                SeaCucumber seaCucumber = grid[row][column];
                if(seaCucumber != null){
                    if(seaCucumber.canMove(grid)  && seaCucumber.isMovesEast()){
                        movesPossible = true;
                        seaCucumber.move(grid);
                    }
                    moveEastGrid[seaCucumber.getRow()][seaCucumber.getColumn()] = seaCucumber;
                }
            }
        }

        SeaCucumber [][] moveSouthGrid = new SeaCucumber[nrOfRows][nrOfColumns];
        for(int row =0; row<nrOfRows; row++){
            for(int column =0; column<nrOfColumns; column++){
                SeaCucumber seaCucumber = moveEastGrid[row][column];
                if(seaCucumber != null){
                    if(seaCucumber.canMove(moveEastGrid) && !seaCucumber.isMovesEast()){
                        movesPossible = true;
                        seaCucumber.move(moveEastGrid);
                    }
                    moveSouthGrid[seaCucumber.getRow()][seaCucumber.getColumn()] = seaCucumber;
                }
            }
        }
        grid = moveSouthGrid;
        return movesPossible;
    }

    void printState(int i){
        System.out.println("State: " +i);
        for(int row =0; row<nrOfRows; row++){
            StringBuilder sb = new StringBuilder();
            for(int column =0; column<nrOfColumns; column++){
                SeaCucumber seaCucumber = grid[row][column];
                if(seaCucumber != null){
                    if(seaCucumber.isMovesEast()){
                        sb.append(">");
                    }
                    else{
                        sb.append("v");
                    }
                }
                else{
                    sb.append(".");
                }
            }
            System.out.println(sb.toString());
        }
        System.out.println("");
    }


}
