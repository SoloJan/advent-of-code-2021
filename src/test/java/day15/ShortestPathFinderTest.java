package day15;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathFinderTest {


    @Test
    void findShortestPathDijkstra(){
        ShortestPathFinder pathFinder = new ShortestPathFinder("day15/example.txt");
        long actualAnswer = pathFinder.findShortestPathDijkstra();
        assertEquals(40L, actualAnswer);
    }

    @Test
    void findShortestPathNaive(){
        ShortestPathFinder pathFinder = new ShortestPathFinder("day15/example.txt");
        long actualAnswer = pathFinder.findShortestPathNaive();
        assertEquals(40L, actualAnswer);
    }

    @Test
    void findShortestPathNaiveInBigMap(){
        ShortestPathFinder pathFinder = new ShortestPathFinder("day15/example.txt");
        long actualAnswer = pathFinder.findShortestPathNaiveInBigMap();
        assertEquals(315L, actualAnswer);
    }

    @Test
    void findShortestPathDijkstraInBigMap(){
        ShortestPathFinder pathFinder = new ShortestPathFinder("day15/example.txt");
        long actualAnswer = pathFinder.findShortestPathDijkstraInBigMap();
        assertEquals(315L, actualAnswer);
    }

    @Test
    @Disabled("Test with the real input, takes around 15 seconds to complete and is therefore disabled by default")
    void testFindShortestPathInBigMapOfRealInput(){
        ShortestPathFinder pathFinder = new ShortestPathFinder("day15/realInput.txt");
        long actualAnswer = pathFinder.findShortestPathDijkstraInBigMap();
        assertEquals(2844L, actualAnswer);
    }

}