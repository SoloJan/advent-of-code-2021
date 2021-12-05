package day5;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class HydrothermalVents {

    List<Vector> vents;

    public HydrothermalVents(String fileName){
        vents =  readFilePerLine(fileName).stream().map(Vector::new).collect(Collectors.toList());
    }

    public long getNumberOfOverlappingPointsOnHorizontalAndVerticalLines(){
        Map<Coordinate, Long> coveredCoordinates = vents.stream().map(v -> v.calculatePointsOnHorizontalAndVerticalLines()).flatMap(Collection::stream).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return coveredCoordinates.keySet().stream().filter(c -> coveredCoordinates.get(c) >= 2).count();
    }

    public long getNumberOfOverlappingPoints(){
        Map<Coordinate, Long> coveredCoordinates = vents.stream().map(v -> v.calculatePointsOnLine()).flatMap(Collection::stream).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return coveredCoordinates.keySet().stream().filter(c -> coveredCoordinates.get(c) >= 2).count();
    }



}
