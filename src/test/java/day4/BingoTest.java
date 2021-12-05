package day4;

import day3.BinaryDiagnoser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BingoTest {

    @Test
    void testBingoScore(){
        Bingo bingo = new Bingo("day4/example.txt");
        int winnerScore = bingo.plau();
        assertEquals(winnerScore, 4512);
    }

    @Test
    void testScoreOfLastBoardToWin(){
        Bingo bingo = new Bingo("day4/example.txt");
        int winnerScore = bingo.scoreOfLastBoardToWin();
        assertEquals(winnerScore, 1924);
    }

}