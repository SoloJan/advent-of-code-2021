package day15;

import day14.PolymereTemplate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathFinderTest {



    @Test
    void testAlternativeShortestPath(){
        ShortestPathFinder pathFinder = new ShortestPathFinder("day15/example.txt");
        long actualAnswer = pathFinder.findShortestPath2();
        assertEquals(40L, actualAnswer);
    }

    @Test
    void testFindShortestPathInBigMap(){
        ShortestPathFinder pathFinder = new ShortestPathFinder("day15/example.txt");
        long actualAnswer = pathFinder.findShortestPathInBigMap();
        assertEquals(315L, actualAnswer);
    }

}