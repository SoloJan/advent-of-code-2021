package day15;

import day14.PolymereTemplate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathFinderTest {

    @Test
    void testWith10Steps(){
        ShortestPathFinder pathFinder = new ShortestPathFinder("day15/example.txt");
        long actualAnswer = pathFinder.findShortestPath();
        assertEquals(40L, actualAnswer);
    }

}