package day4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BingoBoard {

    private List<List<BingoNumber>> board;

    public BingoBoard(List<String> input){
        List<List<String>> boardInString =  input.stream().map(s -> Arrays.asList(s.split(" "))).collect(Collectors.toList());
        board = boardInString.stream().map(row -> row.stream().filter(s-> !s.isEmpty()).map(s -> new BingoNumber(Integer.valueOf(s))).collect(Collectors.toList())).collect(Collectors.toList());
    }

    public Optional<Integer> hit(int number){
        board.stream().forEach(bingoNumbers -> bingoNumbers.stream().forEach(b -> b.hit(number)));
        if(hasBingo()){
            return Optional.of(calculateScore(number));
        }
        return Optional.empty();
    }

    public boolean hasBingo(){
        boolean hasBingoInRow = board.stream().anyMatch( row -> row.stream().allMatch(BingoNumber::isDrawn));
        if(hasBingoInRow){
            return true;
        }
        for(int columnIndex= 0; columnIndex<=4; columnIndex++){
            int finalColumnIndex = columnIndex;
            boolean hasBingoInColumn = board.stream().map(s -> s.get(finalColumnIndex)).allMatch(BingoNumber::isDrawn);
            if (hasBingoInColumn) {
                return true;
            }
        }
            return  false;
    }

    private int calculateScore(int number){
        return number * board.stream().flatMap(bingoNumbers -> bingoNumbers.stream().filter(b -> !b.isDrawn()).map(b-> b.getNumber())).reduce(0, Integer::sum);
    }


}
