package day15;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;


@Getter
@EqualsAndHashCode(exclude = "shortestDistanceToStart")
public class Node {

    private int row;
    private int column;
    private int riskLevel;
    private boolean visited;
    private Optional<Long> shortestDistanceToStart;

    public Node(int row, int column, int riskLevel){
        this.row = row;
        this.column = column;
        this.riskLevel = riskLevel;
        shortestDistanceToStart = Optional.empty();
        visited = false;
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

    private long getLowest(long x, long x2) {
        return x <= x2 ? x : x2;
    }

    public void markVisited(){
        visited = true;
    }




}