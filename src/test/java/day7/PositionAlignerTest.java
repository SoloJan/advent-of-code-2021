package day7;

import day6.LanternFishCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionAlignerTest {

    @Test
    void findShortestDistanceToCommonPoint() {
        PositionAligner positionAligner = new PositionAligner();
        long distance = positionAligner.findShortestDistanceToCommonPoint("day7/example.txt");
        assertEquals(distance, 37L);
    }



    @Test
    void findFuelConsumption() {
        PositionAligner positionAligner = new PositionAligner();
        int fuelConsumption  = positionAligner.findFuelConsumption("day7/example.txt");
        assertEquals(fuelConsumption, 168);
    }
}
