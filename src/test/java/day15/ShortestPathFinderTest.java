package day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathFinderTest {


    @Test
    void findShortestPathDijkstra(){
        ShortestPathFinder pathFinder = new ShortestPathFinder("day15/example.txt");
        long actualAnswer = pathFinder.findShortestPathDijkstra();
        assertEquals(315L, actualAnswer);
    }

    @Test
    void testAlternativeShortestPath(){
        ShortestPathFinder pathFinder = new ShortestPathFinder("day15/example.txt");
        long actualAnswer = pathFinder.findShortestPath();
        assertEquals(40L, actualAnswer);
    }

    @Test
    void testFindShortestPathInBigMap(){
        ShortestPathFinder pathFinder = new ShortestPathFinder("day15/example.txt");
        long actualAnswer = pathFinder.findShortestPathInBigMap();
        assertEquals(315L, actualAnswer);
    }

    @Test
    void testFindShortestPathInBigMapOfRealInput(){
        ShortestPathFinder pathFinder = new ShortestPathFinder("day15/realInput.txt");
        long actualAnswer = pathFinder.findShortestPathInBigMap();
        assertEquals(2844L, actualAnswer);
    }

}