package day19;

import lombok.Getter;

import java.util.*;

import static util.FileUtil.readFilePerLine;

@Getter
public class BeaconScannerMap {

    List<Scanner> scanners = new ArrayList<>();

    public BeaconScannerMap(String fileName){
        List<String> fileContent = readFilePerLine(fileName);
        Scanner scanner = new Scanner("scanner0");
        for(int i=1; i<fileContent.size(); i++){
            if(fileContent.get(i).isEmpty()){
                continue;
            }
            if(fileContent.get(i).contains("--- scanner")){
                scanners.add(scanner);
                scanner = new Scanner("scanner" +i);
            }
            else{
                scanner.addBeacon(new Beacon(fileContent.get(i)));
            }

        }
        scanners.add(scanner);
    }



    public long getBiggestManhattanDistance(){
        matchScanners();
        long biggestManhattanDistance = 0l;
        for(int i=0; i<=scanners.size(); i++){
            for(int j=i+1; j<scanners.size(); j++){
                long manhattanDistance = getManhattanDistance(scanners.get(i), scanners.get(j));
                if(manhattanDistance > biggestManhattanDistance){
                    biggestManhattanDistance = manhattanDistance;
                }
            }
        }
        return biggestManhattanDistance;
    }

    private long getManhattanDistance(Scanner firstScanner, Scanner secondScanner){
        return Math.abs(firstScanner.getX() - secondScanner.getX()) + Math.abs(firstScanner.getY() - secondScanner.getY()) + Math.abs(firstScanner.getZ() - secondScanner.getZ());
    }

    public long getCountOfUniqueBeacons(){
        matchScanners();
        return scanners.stream().map(s -> s.getBeacons()).flatMap(Collection::stream).distinct().count();
    }

    void matchScanners(){
        Scanner startScanner = scanners.get(0);
        startScanner.setMatched();
        HashMap<String, Set<String>> nonMatchingScanners = new HashMap<>();
        int scannersMatchedCounter =1;
        while(scannersMatchedCounter < scanners.size()){
            for(Scanner scanner: scanners){
                if(scanner.isMatched()){
                    continue;
                }
                for (Scanner matchingScanner : scanners) {
                    if(!matchingScanner.isMatched()){
                        continue;
                    }
                    if(!scanner.isMatched() &&  scanner.getCode() != matchingScanner.getCode()){
                        nonMatchingScanners.putIfAbsent(scanner.getCode(), new HashSet<>());
                        if(nonMatchingScanners.get(scanner.getCode()).contains(matchingScanner.getCode())){
                            continue;
                        }
                        if (scanner.doMatch(matchingScanner)) {
                         scannersMatchedCounter++;
                        }
                        else{
                            nonMatchingScanners.get(scanner.getCode()).add(matchingScanner.getCode());
                        }
                    }
                }
            }
        }
    }

}
