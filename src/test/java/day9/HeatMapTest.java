package day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeatMapTest {

    @Test
    void findLowestPoints() {
        HeatMap heatMap  = new HeatMap("day9/example.txt");
        long sumOfRiskLevel =  heatMap.findSumOfRiskLevels();
        assertEquals(15L,  sumOfRiskLevel);
    }

    @Test
    void findProductOfThreeBiggestBasins() {
        HeatMap heatMap  = new HeatMap("day9/example.txt");
        long  productOfBiggestBasins =  heatMap.findProductOfThreeBiggestBasinSizes();
        assertEquals(1134L,  productOfBiggestBasins);
    }
}