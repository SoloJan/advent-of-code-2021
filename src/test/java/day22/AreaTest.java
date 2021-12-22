package day22;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AreaTest {

    @Test
    void count(){
        Area area = new Area(10, 12, 10, 12, 10, 12);
        assertEquals(27L, area.getCount());
    }

    @Test
    void doOverlap() {
        Area area = new Area(10, 12, 10, 12, 10, 12);
        Area overlappingArea = new Area(9, 11, 11, 13, 9, 13);
        Area nonOverlappingArea = new Area(7, 9, 11, 13, 9, 13);
        assertTrue(area.doOverlap(overlappingArea));
        assertFalse(area.doOverlap(nonOverlappingArea));
    }

    @Test
    void getOverlappingArea(){
        Area area = new Area(10, 12, 10, 12, 10, 12);
        Area otherArea = new Area(9, 11, 11, 13, 9, 13);
        Area overlapArea = area.getOverlappingArea(otherArea);
        assertEquals(10, overlapArea.getLowerX());
        assertEquals(11, overlapArea.getUpperX());
        assertEquals(11, overlapArea.getLowerY());
        assertEquals(12, overlapArea.getUpperY());
        assertEquals(10, overlapArea.getLowerZ());
        assertEquals(12, overlapArea.getUpperZ());
    }

    @Test
    void addExample(){
        List<Area> areas = new ArrayList<>();
        Area area = new Area(10,12, 10, 12,10,12);
        Area otherArea = new Area(11,13, 11, 13,11,13);
        Area overLap = area.getOverlappingArea(otherArea);
        areas.addAll(area.subtractArea(overLap));
        areas.addAll(otherArea.subtractArea(overLap));
        areas.add(overLap);
        assertEquals(46, areas.stream().map(Area::getCount).reduce(0l, Long::sum));
    }

    @Test
    void addFromBiggerExample(){
        List<Area> areas = new ArrayList<>();
        Area area = new Area(20,26, 10, 12,10,12);
        Area otherArea = new Area(11,13, 11, 13,11,13);
        Area overLap = area.getOverlappingArea(otherArea);
        areas.addAll(area.subtractArea(overLap));
        areas.addAll(otherArea.subtractArea(overLap));
        areas.add(overLap);
        assertEquals(174L, areas.stream().map(Area::getCount).reduce(0l, Long::sum));
    }



    @Test
    void subtractArea() {
        Area area = new Area(10, 12, 10, 12, 10, 12);
        Area otherArea = new Area(9, 11, 11, 13, 9, 13);
        Area overlapArea = area.getOverlappingArea(otherArea);
        List<Area> areas = area.subtractArea(overlapArea);
        assertEquals(2, areas.size());
        assertEquals(areas.get(0).getCount() + areas.get(1).getCount(), area.getCount() - overlapArea.getCount()) ;
        Area rightOfOverlap = areas.get(0);
        assertEquals(12, rightOfOverlap.getLowerX());
        assertEquals(12, rightOfOverlap.getUpperX());
        assertEquals(10, rightOfOverlap.getLowerY());
        assertEquals(12, rightOfOverlap.getUpperY());
        assertEquals(10, rightOfOverlap.getLowerZ());
        assertEquals(12, rightOfOverlap.getUpperZ());
        Area belowOverlap = areas.get(1);
        assertEquals(10, belowOverlap.getLowerX());
        assertEquals(11, belowOverlap.getUpperX());
        assertEquals(10, belowOverlap.getLowerY());
        assertEquals(10, belowOverlap.getUpperY());
        assertEquals(10, belowOverlap.getLowerZ());
        assertEquals(12, belowOverlap.getUpperZ());


    }

}