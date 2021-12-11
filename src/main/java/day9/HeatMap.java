package day9;

import java.util.*;
import java.util.stream.Collectors;

import static util.CollectionUtil.getDirectNeighbours;
import static util.FileUtil.readFilePerLine;
import static util.StringUtil.toIntegerList;

public class HeatMap {

    List<HeatPoint> lowestPoints;
    List<Set<HeatPoint>> basins = new ArrayList<>();
    List<List<HeatPoint>> heatMap = new ArrayList<>();

    public HeatMap(String fileName){
        List<List<Integer>> temperatures = readFilePerLine(fileName).stream().map(s -> toIntegerList(s)).collect(Collectors.toList());
        for (int row = 0; row < temperatures.size(); row++) {
            List<HeatPoint> heatMapRow = new ArrayList<>();
            for (int column = 0; column < temperatures.get(row).size(); column++) {
                heatMapRow.add(new HeatPoint(row, column, temperatures.get(row).get(column)));
            }
            heatMap.add(heatMapRow);
        }
        lowestPoints = heatMap.stream().flatMap(Collection::stream).filter(hp -> isLowPoint(hp)).collect(Collectors.toList());
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
            getMembersOfBasin(lowPoint, basin);
            basins.add(basin);
        }
    }

    public void getMembersOfBasin(HeatPoint heatPoint, Set<HeatPoint> heatPoints){
        getDirectNeighbours(heatMap, heatPoint.getRow(), heatPoint.getColumn()).forEach(hp ->  addAdjacentHeatPoint(heatPoint, heatPoints, hp) );
    }

    private void addAdjacentHeatPoint(HeatPoint heatPoint, Set<HeatPoint> heatPoints, HeatPoint adjacentHeatPoint) {
        if(adjacentHeatPoint.getValue() != 9 && adjacentHeatPoint.getValue() > heatPoint.getValue() && !heatPoints.contains(adjacentHeatPoint)){
            heatPoints.add(adjacentHeatPoint);
            getMembersOfBasin(adjacentHeatPoint, heatPoints);
        }
    }

    private boolean isLowPoint(HeatPoint heatPoint){
        return getDirectNeighbours(heatMap, heatPoint.getRow(), heatPoint.getColumn()).stream().allMatch(neighbour -> heatPoint.getValue() < neighbour.getValue());
    }

}
