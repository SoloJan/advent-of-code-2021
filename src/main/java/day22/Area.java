package day22;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Area {

    long lowerX;
    long upperX;
    long lowerY;
    long upperY;
    long lowerZ;
    long upperZ;

    public Area(RebootStep step){
        this(step.getLowerX(), step.getUpperX(), step.getLowerY(), step.getUpperY(), step.getLowerZ(), step.getUpperZ());
    }
    
    public long getCount(){
        return (1+ (upperX-lowerX)) * (1+upperY - lowerY) * (1+upperZ - lowerZ);
    }
    
    boolean doOverlap(Area otherArea){
        boolean xOverlap = doOverlap(lowerX, upperX, otherArea.getLowerX(), otherArea.getUpperX());
        boolean yOverlap = doOverlap(lowerY, upperY, otherArea.getLowerY(), otherArea.getUpperY());
        boolean zOverlap = doOverlap(lowerZ, upperZ, otherArea.getLowerZ(), otherArea.getUpperZ());
        return xOverlap && zOverlap && yOverlap;
    }


    /**
     * Subtracts the argument from this area, and returns all the new areas
     * @param overlapArea an area which must be included within this area
     * @return
     */
    public List<Area> subtractArea(Area overlapArea){
        Area left = new Area(lowerX, overlapArea.getLowerX()-1, lowerY, upperY, lowerZ, upperZ);
        Area right = new Area(overlapArea.getUpperX()+1,  upperX, lowerY, upperY, lowerZ, upperZ);
        Area below = new Area(overlapArea.getLowerX(), overlapArea.getUpperX(),  lowerY, overlapArea.getLowerY()-1, lowerZ, upperZ);
        Area above = new Area(overlapArea.getLowerX(), overlapArea.getUpperX(),  overlapArea.getUpperY()+1, upperY, lowerZ, upperZ);
        Area behind = new Area(overlapArea.getLowerX(), overlapArea.getUpperX(),  overlapArea.getLowerY(), overlapArea.getUpperY(), lowerZ, overlapArea.getLowerZ()-1);
        Area front = new Area(overlapArea.getLowerX(), overlapArea.getUpperX(),  overlapArea.getLowerY(), overlapArea.getUpperY(), overlapArea.getUpperZ()+1, upperZ);
        return  Arrays.asList(left, right, below, above, behind, front).stream().filter( area ->  area.getCount() > 0).collect(Collectors.toList());
    }

    public Area getOverlappingArea(Area otherArea){
        long newLowerX = Math.max(lowerX, otherArea.getLowerX());
        long newUpperX = Math.min(upperX, otherArea.getUpperX());
        long newLowerY = Math.max(lowerY, otherArea.getLowerY());
        long newUpperY = Math.min(upperY, otherArea.getUpperY());
        long newLowerZ = Math.max(lowerZ, otherArea.getLowerZ());
        long newUpperZ = Math.min(upperZ, otherArea.getUpperZ());
        return new Area(newLowerX, newUpperX, newLowerY, newUpperY, newLowerZ, newUpperZ);
    }


    private boolean doOverlap(long lowerBoundary, long upperBoundary, long otherLowerBoundary, long otherUpperBoundary) {
        return partialOverlap(lowerBoundary, upperBoundary, otherLowerBoundary, otherUpperBoundary) || completeOverlap(lowerBoundary, upperBoundary, otherLowerBoundary, otherUpperBoundary);
    }

    private boolean completeOverlap(long lowerBoundary, long upperBoundary, long otherLowerBoundary, long otherUpperBoundary) {
        return otherLowerBoundary <= lowerBoundary && otherUpperBoundary >= upperBoundary || lowerBoundary <= otherLowerBoundary && upperBoundary >= otherUpperBoundary;
    }

    private boolean partialOverlap(long lowerBoundary, long upperBoundary, long otherLowerBoundary, long otherUpperBoundary) {
        return liesBetween(otherLowerBoundary, lowerBoundary, upperBoundary) || liesBetween(otherUpperBoundary, lowerBoundary, upperBoundary);
    }


    boolean liesBetween(long value, long lowerBoundary, long upperBoundary){
        return value >= lowerBoundary && value <= upperBoundary;
    }
    
}
