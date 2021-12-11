package day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlashCounterTest {

    @Test
    void increaseEnergy() {
        assertEquals(0L, new FlashCounter("day11/example.txt").countFlashes(1));
        assertEquals(35L, new FlashCounter("day11/example.txt").countFlashes(2));
        assertEquals(80L, new FlashCounter("day11/example.txt").countFlashes(3));
        assertEquals(204L, new FlashCounter("day11/example.txt").countFlashes(10));
        assertEquals(1656L, new FlashCounter("day11/example.txt").countFlashes(100));
    }

    @Test
    void flashTogether() {
        assertEquals(195L, new FlashCounter("day11/example.txt").flashTogether());
    }
}