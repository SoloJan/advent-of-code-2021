package day22;

import day20.ImageEnhancer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReactorRebooterTest {

    @Test
    void rebootTest() {
        ReactorRebooter rebooter = new ReactorRebooter("day22/example.txt");
        assertEquals(39, rebooter.initialReboot());
    }

    @Test
    void rebootTestWithlargerExample() {
        ReactorRebooter rebooter = new ReactorRebooter("day22/biggerExample.txt");
        assertEquals(590784, rebooter.initialReboot());
    }

    @Test
    void completeReboot() {
        ReactorRebooter rebooter = new ReactorRebooter("day22/completeRebootExample.txt");
        assertEquals(2758514936282235L, rebooter.totalReboot());
    }

}