package day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanternFishCalculatorTest {

    @Test
    void calculateAmountOfFish() {
        LanternFishCalculator lanternFishCalculator = new LanternFishCalculator();
        long nrOfFish = lanternFishCalculator.calculateAmountOfFish("day6/example.txt", 80);
        assertEquals(nrOfFish, 5934);
    }

    @Test
    void calculateAmountOfFishWithBigNumbers() {
        LanternFishCalculator lanternFishCalculator = new LanternFishCalculator();
        long nrOfFish = lanternFishCalculator.calculateAmountOfFish("day6/example.txt", 256);
        assertEquals(nrOfFish, 26984457539L);
    }
}