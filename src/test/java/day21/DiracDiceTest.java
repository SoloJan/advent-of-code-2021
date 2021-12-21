package day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiracDiceTest {

    @Test
    void playWithDeterministicDice(){
        DiracDice diracDice = new DiracDice();
        long player1Wins =   diracDice.play(4,8);
        assertEquals(444356092776315L, player1Wins);
    }


}