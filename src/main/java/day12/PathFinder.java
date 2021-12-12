package day12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static util.FileUtil.readFilePerLine;

public class PathFinder {

    public static final String START = "start";
    public static final String END = "end";
    private Set<Cave> caves = new HashSet<>();

    public PathFinder(String fileName){
        List<String> pathStrings = readFilePerLine(fileName);
        for(String pathString: pathStrings){
            Cave firstCave = getOrCreateCave(pathString.split("-")[0]);
            Cave secondCave = getOrCreateCave(pathString.split("-")[1]);
            firstCave.addDirectlyConnectedCave(secondCave);
            secondCave.addDirectlyConnectedCave(firstCave);
            caves.add(firstCave);
            caves.add(secondCave);
        }
    }

    private Cave getOrCreateCave(String caveCode){
        return caves.stream().filter(c -> c.getCode().equals(caveCode)).findFirst().orElse(new Cave(caveCode));
    }

    public long findNrOfPaths(){
        return findNrOfPaths(1);
    }

    private long findNrOfPaths(long allowedNrOfVisitsToSmallCave){
       return getOrCreateCave(START).nrOfPathsFoundWithRevisitOfSmallCave(getOrCreateCave(END), allowedNrOfVisitsToSmallCave);
    }

    public long findNrOfPathsRevisitingSmallCaves(){
        return findNrOfPaths(2);
    }




}
