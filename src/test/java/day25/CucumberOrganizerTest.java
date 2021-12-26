package day25;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CucumberOrganizerTest {


    /*
        Expected state of grid after one move
        ..vv>..
        .......
        >......
        v.....>
        >......
        .......
        ....v..
     */

    @Test
    void oneMove(){
        CucumberOrganizer cucumberOrganizer = new CucumberOrganizer("day25/example.txt");
        cucumberOrganizer.move();
        SeaCucumber[][] grid = cucumberOrganizer.getGrid();
        assertNotNull(grid[0][2]);
        assertNotNull(grid[0][3]);
        assertNotNull(grid[0][4]);

        assertNotNull(grid[2][0]);

        assertNotNull(grid[3][0]);
        assertNotNull(grid[3][6]);

        assertNotNull(grid[4][0]);

        assertNotNull(grid[6][4]);
    }

    @Test
    void movesToStabilize(){
        CucumberOrganizer cucumberOrganizer = new CucumberOrganizer("day25/example2.txt");
        long result = cucumberOrganizer.getRoundsToStabalize();
        assertEquals(58l, result);

    }



}