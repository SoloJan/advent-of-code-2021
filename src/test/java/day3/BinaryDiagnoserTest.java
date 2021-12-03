package day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryDiagnoserTest {

    /**
     * A test with test the result of the example, in the readme of day2
     */
    @Test
    void testCalculatePower(){
        BinaryDiagnoser diagnoser = new BinaryDiagnoser();
        int power = diagnoser.getPower("day3/example.txt");
        assertEquals(power, 198);
    }

    @Test
    void testLifeSupport(){
        BinaryDiagnoser diagnoser = new BinaryDiagnoser();
        int power = diagnoser.getLifeSupport("day3/example.txt");
        assertEquals(power, 230);
    }

}