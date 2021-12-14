package day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolymerizatorTest {

    @Test
    void testSetup(){
        Polymerizator polymerizator = new Polymerizator("day14/example.txt");
        assertEquals("NNCB", polymerizator.getPolyPair().toString());
    }

    @Test
    void testPerformFourSteps(){
        Polymerizator polymerizator = new Polymerizator("day14/example.txt");
        polymerizator.updatePolymerTemplate(4);
        assertEquals("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB", polymerizator.getPolyPair().toString());
    }

    @Test
    void testPerThreeSteps(){
        Polymerizator polymerizator = new Polymerizator("day14/example.txt");
        polymerizator.updatePolymerTemplate(3);
        assertEquals("NBBBCNCCNBBNBNBBCHBHHBCHB", polymerizator.getPolyPair().toString());
    }

    @Test
    void testWith10Steps(){
        Polymerizator polymerizator = new Polymerizator("day14/example.txt");
        assertEquals(1588, polymerizator.mostCommonCharacterMinusLeastCommonCharacterAfterSteps(10));
    }

    @Test
    void testWithFourSteps(){
        Polymerizator polymerizator = new Polymerizator("day14/example.txt");
        assertEquals(18, polymerizator.mostCommonCharacterMinusLeastCommonCharacterAfterSteps(4));
    }

    @Test
    void testWithThreeSteps(){
        Polymerizator polymerizator = new Polymerizator("day14/example.txt");
        assertEquals(7, polymerizator.mostCommonCharacterMinusLeastCommonCharacterAfterSteps(3));
    }




}