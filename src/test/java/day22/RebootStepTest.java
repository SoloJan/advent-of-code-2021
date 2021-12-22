package day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RebootStepTest {

    @Test
    void testConstructor(){
        RebootStep rebootStep = new RebootStep("on x=11..13,y=12..15,z=10..17");
        assertTrue(rebootStep.isOn());
        assertEquals(11, rebootStep.getLowerX());
        assertEquals(13, rebootStep.getUpperX());
        assertEquals(12, rebootStep.getLowerY());
        assertEquals(15, rebootStep.getUpperY());
        assertEquals(10, rebootStep.getLowerZ());
        assertEquals(17, rebootStep.getUpperZ());
    }

}