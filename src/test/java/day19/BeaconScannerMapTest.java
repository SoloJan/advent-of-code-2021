package day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeaconScannerMapTest {

    @Test
    void testDoMatch(){
        BeaconScannerMap beaconScannerMap = new BeaconScannerMap("day19/example.txt");
        assertTrue(beaconScannerMap.getScanners().get(1).doMatch(beaconScannerMap.getScanners().get(0)));
        assertEquals(68, beaconScannerMap.getScanners().get(1).getX());
        assertEquals(-1246, beaconScannerMap.getScanners().get(1).getY());
        assertEquals(-43, beaconScannerMap.getScanners().get(1).getZ());
        assertTrue(beaconScannerMap.getScanners().get(1).getBeacons().contains(new Beacon(-618, -824, -621)));
    }

    @Test
    void testMatchOfScannerOneAndFour(){
        BeaconScannerMap beaconScannerMap = new BeaconScannerMap("day19/example.txt");
        beaconScannerMap.getScanners().get(1).doMatch(beaconScannerMap.getScanners().get(0));


        assertTrue(beaconScannerMap.getScanners().get(4).doMatch(beaconScannerMap.getScanners().get(1)));
        assertEquals(-20, beaconScannerMap.getScanners().get(4).getX());
        assertEquals(-1133, beaconScannerMap.getScanners().get(4).getY());
        assertEquals(1061, beaconScannerMap.getScanners().get(4).getZ());
        assertTrue(beaconScannerMap.getScanners().get(4).getBeacons().contains(new Beacon(459,-707,401)));
    }

    @Test
    void biggestManhattanDistance(){
        BeaconScannerMap beaconScannerMap = new BeaconScannerMap("day19/example.txt");
        assertEquals(3621l, beaconScannerMap.getBiggestManhattanDistance());
    }


    @Test
    void testNrOfUniqueBeacons(){
        BeaconScannerMap beaconScannerMap = new BeaconScannerMap("day19/example.txt");
        assertEquals(79, beaconScannerMap.getCountOfUniqueBeacons());

    }


}