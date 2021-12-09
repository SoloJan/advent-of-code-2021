package day9;

import java.util.*;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;
import static util.StringUtil.toIntegerList;

public class HeatMap {

    List<List<Integer>> heatMap;
    List<HeatPoint> lowestPoints = new ArrayList<>();
    List<Set<HeatPoint>> basins = new ArrayList<>();

    public HeatMap(String fileName){
       heatMap = readFilePerLine(fileName).stream().map(s -> toIntegerList(s)).collect(Collectors.toList());
       findLowesPoints();
       findBasins();
    }

    public long findProductOfThreeBiggestBasinSizes(){
        List<Integer> basinLengths = basins.stream().map(Set::size).sorted().collect(Collectors.toList());
        int size = basinLengths.size();
        return basinLengths.get(size-1) * basinLengths.get(size-2) * basinLengths.get(size-3);

    }

    public int findSumOfRiskLevels() {
       return lowestPoints.stream().map(hp ->  hp.getValue()+1).reduce(0, Integer::sum);
    }



    public void findBasins(){
        for(HeatPoint lowPoint: lowestPoints){
            HashSet basin = new HashSet();
            basin.add(lowPoint);
            basin.addAll(getMembersOfBasin(lowPoint, basin));
            basins.add(basin);
        }
    }

    private void findLowesPoints(){
        for (int row = 0; row < heatMap.size(); row++) {
            for (int column = 0; column < heatMap.get(row).size(); column++) {
                if (isLowPoint(row, column)) {
                    HeatPoint heatPoint = new HeatPoint(row, column, heatMap.get(row).get(column));
                    lowestPoints.add(heatPoint);
                }
            }
        }
    }



    public Set<HeatPoint> getMembersOfBasin(HeatPoint heatPoint, Set<HeatPoint> heatPoints){
        heatPoints.addAll(getBasinMembersAbove(heatPoint, heatPoints));
        heatPoints.addAll(getBasinMembersBelow(heatPoint, heatPoints));
        heatPoints.addAll(getBasinMembersToTheRight(heatPoint, heatPoints));
        heatPoints.addAll(getBasinMembersToTheLeft(heatPoint, heatPoints));
        return heatPoints;
    }

    public Set<HeatPoint> getBasinMembersAbove(HeatPoint heatPoint, Set<HeatPoint> heatPoints){
        if( heatPoint.getRow() != 0){
            HeatPoint adjacentHeatPoint = new HeatPoint(heatPoint.getRow() -1, heatPoint.getColumn(), heatMap.get(heatPoint.getRow()-1).get(heatPoint.getColumn()));
            addAdjacentHeatPoint(heatPoint, heatPoints, adjacentHeatPoint);
        }
        return heatPoints;
    }

    public Set<HeatPoint> getBasinMembersBelow(HeatPoint heatPoint, Set<HeatPoint> heatPoints){
        if( heatPoint.getRow() != heatMap.size() -1 ){
            HeatPoint adjacentHeatPoint = new HeatPoint(heatPoint.getRow() +1, heatPoint.getColumn(), heatMap.get(heatPoint.getRow()+1).get(heatPoint.getColumn()));
            addAdjacentHeatPoint(heatPoint, heatPoints, adjacentHeatPoint);
        }
        return heatPoints;
    }

    public Set<HeatPoint> getBasinMembersToTheRight(HeatPoint heatPoint, Set<HeatPoint> heatPoints){
        if( heatPoint.getColumn() != heatMap.get(0).size() -1 ){
            HeatPoint adjacentHeatPoint = new HeatPoint(heatPoint.getRow(), heatPoint.getColumn()+1, heatMap.get(heatPoint.getRow()).get(heatPoint.getColumn()+1));
            addAdjacentHeatPoint(heatPoint, heatPoints, adjacentHeatPoint);
        }
        return heatPoints;
    }

    public Set<HeatPoint> getBasinMembersToTheLeft(HeatPoint heatPoint, Set<HeatPoint> heatPoints){
        if( heatPoint.getColumn() != 0 ){
            HeatPoint adjacentHeatPoint = new HeatPoint(heatPoint.getRow(), heatPoint.getColumn()-1, heatMap.get(heatPoint.getRow()).get(heatPoint.getColumn()-1));
            addAdjacentHeatPoint(heatPoint, heatPoints, adjacentHeatPoint);
        }
        return heatPoints;
    }

    private void addAdjacentHeatPoint(HeatPoint heatPoint, Set<HeatPoint> heatPoints, HeatPoint adjacentHeatPoint) {
        if(adjacentHeatPoint.getValue() != 9 && adjacentHeatPoint.getValue() > heatPoint.getValue() && !heatPoints.contains(adjacentHeatPoint)){
            heatPoints.add(adjacentHeatPoint);
            heatPoints.addAll(getMembersOfBasin(adjacentHeatPoint, heatPoints));
        }
    }


    private boolean isLowPoint(int row, int column){
        return isLowerThanUpperNeighbour(row, column) && isLowerThanDownNeighbour(row, column) && isLowerThanRightNeighbour(row, column) && isLowerThanLeftNeighbour(row, column);
    }

    private boolean isLowerThanUpperNeighbour(int row, int column){
        return row == 0 || heatMap.get(row).get(column) < heatMap.get(row-1).get(column);
    }

    private boolean isLowerThanDownNeighbour(int row, int column){
        return row == heatMap.size() -1 || heatMap.get(row).get(column) < heatMap.get(row+1).get(column);
    }

    private boolean isLowerThanLeftNeighbour(int row, int column){
        return column == 0 || heatMap.get(row).get(column) < heatMap.get(row).get(column -1);
    }

    private boolean isLowerThanRightNeighbour(int row, int column){
        return column == heatMap.get(0).size() -1 || heatMap.get(row).get(column) < heatMap.get(row).get(column +1);
    }


}
