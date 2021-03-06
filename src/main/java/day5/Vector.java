package day5;

import Common.Coordinate;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
public class Vector {

    Coordinate start;
    Coordinate end;

    public Vector(String vector){
        this(new Coordinate(vector.split(" -> ")[0]), new Coordinate(vector.split(" -> ")[1]));
    }

    public List<Coordinate> calculatePointsOnHorizontalAndVerticalLines(){
        if(isVertical()){
            return calculatePointsOnVerticalLine();
        }
        if(isHorizontal()){
            return calculatePointsOnHorizontalLine();
        }
        return new ArrayList<>();
    }

    public List<Coordinate> calculatePointsOnLine(){
        if(isVertical()){
            return calculatePointsOnVerticalLine();
        }
        if(isHorizontal()){
            return calculatePointsOnHorizontalLine();
        }
        return calculatePointsOnDiagonalLine();
    }

    private List<Coordinate> calculatePointsOnDiagonalLine(){
        List<Coordinate> points = new ArrayList<>();
        int steps = getHighestX() - getLowestX();
        for (int i = 0; i <= steps; i++) {
            if(isDiagonalInSameDirection()) {
                points.add(new Coordinate(getLowestX() + i, getLowestY() + i));
            }
            else{
                points.add(new Coordinate(getLowestX()+i, getHighestY()-i));
            }
        }
        return points;
    }

    private List<Coordinate> calculatePointsOnHorizontalLine() {
        List<Coordinate> points = new ArrayList<>();
        for(int i=getLowestX(); i<=getHighestX(); i++){
            points.add(new Coordinate(i, start.getY()));
        }
        return points;
    }

    private List<Coordinate> calculatePointsOnVerticalLine () {
        List<Coordinate> points = new ArrayList<>();
        for(int i=getLowestY(); i<=getHighestY(); i++){
            points.add(new Coordinate(start.getX(), i));
        }
        return points;
    }

    private boolean isHorizontal() {
        return start.getY() == end.getY();
    }

    private boolean isVertical() {
        return start.getX() == end.getX();
    }

    private boolean isDiagonalInSameDirection() {
        return (start.getX() > end.getX() && start.getY() > end.getY()) || (start.getX() < end.getX() && start.getY() < end.getY());
    }

    private int getHighestY() {
        return Math.min(start.getY(), end.getY());
    }

    private int getHighestX(){
        return Math.max(start.getX(), end.getX());
    }

    private int getLowestY(){return Math.min(start.getY(), end.getY());}

    private int getLowestX(){
        return Math.min(start.getX(), end.getX());
    }

}
