package day8;

import day5.HydrothermalVents;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegmentDisplayRepairerTest {

    @Test
    void getSumOfOutputs(){
        SegmentDisplayRepairer repairer  = new SegmentDisplayRepairer("day8/example.txt");
        int sumOfOutputs =  repairer.getSumOfOutputs();
        assertEquals(61229,  sumOfOutputs);
    }


    @Test
    void getUniqueDigitsInOutput() {
        SegmentDisplayRepairer repairer  = new SegmentDisplayRepairer("day8/example.txt");
        long nrOfUniqueDigitsInOutput =  repairer.getUniqueDigitsInOutput();
        assertEquals( 26l, nrOfUniqueDigitsInOutput);
    }
}