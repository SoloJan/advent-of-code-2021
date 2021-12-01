package day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SonarSweeperTest {

    /**
     * A test with test the result of the example, in the readme of day1
     */
    @Test
    void testExample(){
        SonarSweeper sonarSweeper = new SonarSweeper();
        int nrOfIncreases = sonarSweeper.calculateNrOfIncreasedDepths("day1/example.txt");
        assertEquals(nrOfIncreases, 7);
    }

}