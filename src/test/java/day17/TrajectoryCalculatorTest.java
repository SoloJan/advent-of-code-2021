package day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrajectoryCalculatorTest {

    @Test
    void findYVelocity(){
        TrajectoryCalculator calculator = new TrajectoryCalculator();
        assertEquals(9l,  calculator.findYVel(20, 30, -10, -5));
    }


    @Test
    void findMaxHeight(){
        TrajectoryCalculator calculator = new TrajectoryCalculator();
        assertEquals(45l,  calculator.findMaxHeight(20, 30, -10, -5));
    }

    @Test
    void findMaxHeightRealPuzzleInput(){
        TrajectoryCalculator calculator = new TrajectoryCalculator();
        assertEquals(13203,  calculator.findMaxHeight(85, 145, -163, -108));
    }

    @Test
    void calculateYVelocityRealPuzzleInput(){
        TrajectoryCalculator calculator = new TrajectoryCalculator();
        assertEquals(162,  calculator.findYVel(85, 145, -163, -108));
    }

    @Test
    void findCount(){
        TrajectoryCalculator calculator = new TrajectoryCalculator();
        assertEquals(112l,  calculator.findCountOfAllowedVelocities(20, 30, -10, -5));
    }


}