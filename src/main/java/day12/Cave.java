package day12;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static day12.PathFinder.START;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(exclude = "directlyConnectedCaves")
public class Cave {

    private Set<Cave> directlyConnectedCaves = new HashSet<>();
    private String code;
    private boolean isSmallCave;

    public Cave(String code){
        this.code = code;
        if(code.toUpperCase() != code){
            isSmallCave = true;
        }
    }

    private boolean isStartCave(){
        return this.code.equals(START);
    }

    long nrOfPathsFoundWithRevisitOfSmallCave(Cave destination, long allowedNrOfVisitsToSmallCave){
        return nrOfPathsFoundWithRevisitOfSmallCave(destination, new ArrayList(), allowedNrOfVisitsToSmallCave);
    }

    long nrOfPathsFoundWithRevisitOfSmallCave(Cave destination, List<Cave> visitedPaths, long allowedNrOfVisitsToSmallCave){
        if(this.code.equals(destination.code)){
            return 1;
        }
        else if(visitedPaths.stream().filter( s -> s.getCode().equals(this.code)).count() >= allowedNrOfVisitsToSmallCave  && isSmallCave){
            return 0;
        }
        else{
            if(visitedPaths.contains(this) && isSmallCave && allowedNrOfVisitsToSmallCave > 1){
                allowedNrOfVisitsToSmallCave--;
            }
            visitedPaths.add(this);
            long allowedNrOfVisitsToRemainingSmallCaves = allowedNrOfVisitsToSmallCave;
            return directlyConnectedCavesExcludingStart().stream().map(c ->  c.nrOfPathsFoundWithRevisitOfSmallCave(destination, new ArrayList(visitedPaths), allowedNrOfVisitsToRemainingSmallCaves)).reduce(0L, Long::sum);
        }
    }

    private List<Cave> directlyConnectedCavesExcludingStart(){
        return directlyConnectedCaves.stream().filter(c -> !c.isStartCave()).collect(Collectors.toList());
    }


    public void addDirectlyConnectedCave(Cave cave){
        directlyConnectedCaves.add(cave);
    }

}
