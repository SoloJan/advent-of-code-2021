package day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeterministicDiceTest {

    @Test
    void playWithDeterministicDice(){
        DeterministicDice deterministicDice = new DeterministicDice();
        int gameScore = deterministicDice.play(4, 8);
        assertEquals(739785, gameScore);
    }

}