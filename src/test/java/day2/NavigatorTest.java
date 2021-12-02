package day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NavigatorTest {

    /**
     * A test with test the result of the example, in the readme of day2
     */
    @Test
    void testCalculatePosition(){
        Navigator navigator = new Navigator();
        int position = navigator.calculatePosition("day2/example.txt");
        assertEquals(position, 150);
    }

    /**
     * A test with test the result of the example, in the readme of day2
     */
    @Test
    void testCalculatePositionWithAim(){
        Navigator navigator = new Navigator();
        int position = navigator.calculatePositionWithAim("day2/example.txt");
        assertEquals(position, 900);
    }


}