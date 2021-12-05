package day5;

import java.util.ArrayList;
import java.util.List;

public class Vector {

    Coordinate start;
    Coordinate end;

    public Vector(String vector){
        this(new Coordinate(vector.split(" -> ")[0]), new Coordinate(vector.split(" -> ")[1]));
    }

    public Vector(Coordinate start, Coordinate end){
        this.start = start;
        this.end = end;
    }


    public List<Coordinate> calculatePointsOnHorizontalAndVerticalLines(){
        List<Coordinate> points = new ArrayList<>();
        if(start.getX() == end.getX()){
            points.addAll(calculatePointsOnHorizontalLine());
        }
        if(start.getY() == end.getY()){
            points.addAll(calculatePointsOnVerticalLine());
        }
        return points;
    }

    public List<Coordinate> calculatePointsOnLine(){
        List<Coordinate> points = new ArrayList<>();
        if(start.getX() == end.getX()){
            points.addAll(calculatePointsOnHorizontalLine());
        }
        else if(start.getY() == end.getY()){
           points.addAll(calculatePointsOnVerticalLine());
        }
        else{
            points.addAll(calculatePointsOnDiagonalLine());
        }
        return points;
    }

    private List<Coordinate> calculatePointsOnDiagonalLine(){
        List<Coordinate> points = new ArrayList<>();
        int lowestX =  start.getX()  <=  end.getX() ? start.getX() : end.getX();

        int steps = Math.abs(start.getX() - end.getX());
        if((start.getX() > end.getX() && start.getY() > end.getY()) || (start.getX() < end.getX() && start.getY() < end.getY()))
        {
            int lowestY = start.getY() <= end.getY() ? start.getY() : end.getY();
            for (int i = 0; i <= steps; i++) {
                points.add(new Coordinate(lowestX + i, lowestY + i));
            }
        }
        else
        {
            int highestY =  start.getY()  >=  end.getY() ? start.getY() : end.getY();
            for(int i=0; i<=steps; i++){
                points.add(new Coordinate(lowestX+i, highestY-i));
            }
        }
        return points;
    }

    private List<Coordinate> calculatePointsOnVerticalLine () {
        List<Coordinate> points = new ArrayList<>();
        Coordinate startingPoint =  start.getX()  <=  end.getX() ? start : end;
        Coordinate endPoint =  start.getX()  <=  end.getX() ? end : start;
        for(int i=startingPoint.getX(); i<=endPoint.getX(); i++){
            points.add(new Coordinate(i, start.getY()));
        }
        return points;
    }

    private List<Coordinate> calculatePointsOnHorizontalLine () {
        List<Coordinate> points = new ArrayList<>();
        Coordinate startingPoint =  start.getY()  <=  end.getY() ? start : end;
        Coordinate endPoint =  start.getY()  <=  end.getY() ? end : start;
            for(int i=startingPoint.getY(); i<=endPoint.getY(); i++){
                points.add(new Coordinate(start.getX(), i));
            }
        return points;
    }


}
