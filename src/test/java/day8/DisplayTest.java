package day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisplayTest {

    @Test
     void test() {
        Display display = new Display("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf".split("\\|"));
        int sumOfOutputs = display.getOutput();
        assertEquals(5353, sumOfOutputs);

    }

}