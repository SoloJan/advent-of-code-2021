package day12;

import day11.FlashCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {

    @Test
    void findPath() {
        PathFinder pathFinder = new PathFinder("day12/example.txt");
        assertEquals(10L, pathFinder.findNrOfPaths());
    }

    @Test
    void findPathWithLagerExample() {
        PathFinder pathFinder = new PathFinder("day12/example2.txt");
        assertEquals(19L, pathFinder.findNrOfPaths());
    }

    @Test
    void findPathWithBiggestExample() {
        PathFinder pathFinder = new PathFinder("day12/example3.txt");
        assertEquals(226L, pathFinder.findNrOfPaths());
    }

    @Test
    void findNrOfPathsRevisitingSmallCaves(){
        PathFinder pathFinder = new PathFinder("day12/example.txt");
        assertEquals(36L, pathFinder.findNrOfPathsRevisitingSmallCaves());

    }


}