package Day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HydrothermalVentsTest {

    @Test
    void testNrOfOverlappingPointsOnHorizontalAndVerticalPoints(){
        HydrothermalVents hydrothermalVents = new HydrothermalVents("day5/example.txt");
        long nrOfOverlappingPoints = hydrothermalVents.getNumberOfOverlappingPointsOnHorizontalAndVerticalLines();
        assertEquals(nrOfOverlappingPoints, 5l);
    }

    @Test
    void testNrOfOverlappingPoints(){
        HydrothermalVents hydrothermalVents = new HydrothermalVents("day5/example.txt");
        long nrOfOverlappingPoints = hydrothermalVents.getNumberOfOverlappingPoints();
        assertEquals(nrOfOverlappingPoints, 12l);
    }

}