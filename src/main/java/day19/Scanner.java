package day19;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

import static day19.Rotation.XX;

@NoArgsConstructor
@Getter
public class Scanner {

    List<Beacon> beacons = new ArrayList<>();
    int x;
    int y;
    int z;
    boolean matched = false;
    String code;

    public Scanner(String code){
        this.code = code;
    }

    void addBeacon(Beacon beacon){
        beacons.add(beacon);
    }

    void setMatched(){
        matched = true;
    }

    public boolean doMatch(Scanner scanner) {
        Map<MapToZeroScanner, Integer> xDifference = new HashMap<>();
        Map<MapToZeroScanner, Integer> yDifference = new HashMap<>();
        Map<MapToZeroScanner, Integer> zDifference = new HashMap<>();
        for (Beacon beacon : scanner.getBeacons()) {
            for (Beacon innerBeacon : beacons) {
                populateXMap(xDifference, beacon.getX(), innerBeacon);
                populateYMap(yDifference, beacon.getY(), innerBeacon);
                populateZMap(zDifference, beacon.getZ(), innerBeacon);
                }
        }
        MapToZeroScanner proposedX = getMostProbableValue(xDifference);
        MapToZeroScanner proposedY = getMostProbableValue(yDifference);
        MapToZeroScanner proposedZ = getMostProbableValue(zDifference);
        if(xDifference.get(proposedX) >= 12  && yDifference.get(proposedY) >= 12 && zDifference.get(proposedZ) >= 12){
           beacons.stream().forEach(b -> b.mapToZeroScanner(proposedX, proposedY, proposedZ));
           x =  proposedX.getMagnitude();
           y =  proposedY.getMagnitude();
           z =  proposedZ.getMagnitude();
           matched = true;
       }
        return matched;
    }

    private MapToZeroScanner getMostProbableValue(Map<MapToZeroScanner, Integer> map){
        MapToZeroScanner mostProbableKey = new MapToZeroScanner(XX, 0);
        int mostProbableXValue = 0;
        for(MapToZeroScanner key: map.keySet()){
            int value = map.get(key);
            if(value > mostProbableXValue){
                mostProbableKey = key;
                mostProbableXValue = value;
            }
        }
        return  mostProbableKey;
    }

    void populateXMap(Map<MapToZeroScanner, Integer> map, Integer coordinate, Beacon beacon){
        addOrUpdate(map, new MapToZeroScanner( XX, coordinate - beacon.getX()));
        addOrUpdate(map ,new MapToZeroScanner( Rotation.XY, coordinate - beacon.getY()));
        addOrUpdate(map, new MapToZeroScanner( Rotation.XZ, coordinate - beacon.getZ()));
        addOrUpdate(map, new MapToZeroScanner( Rotation.XMINUSX, coordinate - (-1* beacon.getX())));
        addOrUpdate(map ,new MapToZeroScanner( Rotation.XMINUSY, coordinate - (-1* beacon.getY())));
        addOrUpdate(map, new MapToZeroScanner( Rotation.XMINUSZ, coordinate - (-1* beacon.getZ())));
    }

    void populateYMap(Map<MapToZeroScanner, Integer> map, Integer coordinate, Beacon beacon){
        addOrUpdate(map, new MapToZeroScanner( Rotation.YX, coordinate - beacon.getX()));
        addOrUpdate(map ,new MapToZeroScanner( Rotation.YY, coordinate - beacon.getY()));
        addOrUpdate(map, new MapToZeroScanner( Rotation.YZ, coordinate - beacon.getZ()));
        addOrUpdate(map, new MapToZeroScanner( Rotation.YMINUSX, coordinate - (-1* beacon.getX())));
        addOrUpdate(map ,new MapToZeroScanner( Rotation.YMINUSY, coordinate - (-1* beacon.getY())));
        addOrUpdate(map, new MapToZeroScanner( Rotation.YMINUSZ, coordinate - (-1* beacon.getZ())));
    }

    void populateZMap(Map<MapToZeroScanner, Integer> map, Integer coordinate, Beacon beacon){
        addOrUpdate(map, new MapToZeroScanner( Rotation.ZX, coordinate - beacon.getX()));
        addOrUpdate(map ,new MapToZeroScanner( Rotation.ZY, coordinate - beacon.getY()));
        addOrUpdate(map, new MapToZeroScanner( Rotation.ZZ, coordinate - beacon.getZ()));
        addOrUpdate(map, new MapToZeroScanner( Rotation.ZMINUSX, coordinate - (-1* beacon.getX())));
        addOrUpdate(map ,new MapToZeroScanner( Rotation.ZMINUSY, coordinate - (-1* beacon.getY())));
        addOrUpdate(map, new MapToZeroScanner( Rotation.ZMINUSZ, coordinate - (-1* beacon.getZ())));
    }

    private void addOrUpdate(Map<MapToZeroScanner, Integer> map, MapToZeroScanner key){
        map.putIfAbsent(key, 1);
        map.put(key, map.get(key)+1);
    }




}
