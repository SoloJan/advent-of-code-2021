package day15;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static util.CollectionUtil.getDirectNeighbours;


@Getter
@EqualsAndHashCode(exclude = "shortestDistanceToStart")
public class Node {

    private int row;
    private int column;
    private int riskLevel;
    private boolean visited;
    private Optional<Long> shortestDistanceToEnd;
    private Optional<Long> shortestDistanceToStart;
    private List<Node> nonVisitedNeighbours;



    public Node(int row, int column, int riskLevel){
        this.row = row;
        this.column = column;
        this.riskLevel = riskLevel;
        shortestDistanceToEnd = Optional.empty();
        shortestDistanceToStart = Optional.empty();
        visited = false;
    }

    public List<Node> getNonVisitedNeighbours(){
        return new ArrayList<>(nonVisitedNeighbours);
    }

    void removeNeighbour(Node node){
        nonVisitedNeighbours.remove(node);
    }

    void setNeighbours(List<List<Node>> nodes){
        nonVisitedNeighbours = getDirectNeighbours(nodes, row, column);
    }

    public void setShortestDistanceToStart(Node node){
        setShortestDistanceToStart(node.getShortestDistanceToStart().get() + riskLevel);
    }

    public void setShortestDistanceToStart(long distanceToStart){
        if(shortestDistanceToStart.isPresent()){
            this.shortestDistanceToStart = Optional.of(getLowest(distanceToStart, shortestDistanceToStart.get()));
        }
        else{
            this.shortestDistanceToStart = Optional.of(distanceToStart);
        }
    }

    public void setShortestDistanceToEnd(long distanceToStart){
        if(shortestDistanceToEnd.isPresent()){
            this.shortestDistanceToEnd = Optional.of(getLowest(distanceToStart, shortestDistanceToEnd.get()));
        }
        else{
            this.shortestDistanceToEnd = Optional.of(distanceToStart);
        }
    }

    private long getLowest(long x, long x2) {
        return x <= x2 ? x : x2;
    }

    public void markVisited(){
        visited = true;
    }




}
