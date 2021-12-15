package day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolymerTemplateTest {


    @Test
    void testWith10Steps(){
        PolymereTemplate polymereTemplate = new PolymereTemplate("day14/example.txt");
        assertEquals(1588, polymereTemplate.mostCommonCharacterMinusLeastCommonCharacterAfterSteps(10));
    }


}