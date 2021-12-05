package day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class Bingo {

    private List<Integer> numberDrawn;
    private List<BingoBoard> boards;

    public Bingo(String fileName){
        List<String> fileContent =  readFilePerLine(fileName);
        boards = new ArrayList<>();
        numberDrawn = Arrays.asList(fileContent.get(0).split(",")).stream().map(s-> Integer.valueOf(s)).collect(Collectors.toList());
        for(int i=1; i<=fileContent.size()-6; i=i+6){
            boards.add(new BingoBoard(fileContent.subList(i+1, i+6)));
        }
    }

    public int play(){
        for(Integer number: numberDrawn){
            for(BingoBoard board: boards){
                Optional<Integer> scoreOfWinner = board.hit(number);
                if(scoreOfWinner.isPresent()){
                    return scoreOfWinner.get();
                }
            }
        }
        return 0;
    }

    public int scoreOfLastBoardToWin(){
        BingoBoard lastBoard = null;
        for(Integer number: numberDrawn){
            for(BingoBoard board: boards){
                Optional<Integer> scoreOfWinner = board.hit(number);
                if(scoreOfWinner.isPresent() && board.equals(lastBoard)){
                    return scoreOfWinner.get();
                }
            }
            List<BingoBoard> nonWinningBoards =  boards.stream().filter(bingoBoard -> !bingoBoard.hasBingo()).collect(Collectors.toList());
            if(nonWinningBoards.size()==1){
                lastBoard = nonWinningBoards.get(0);
            }
        }
        return 0;
    }



}
