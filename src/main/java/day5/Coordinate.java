package day5;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Coordinate {

    private int x;
    private int y;

    public  Coordinate(String coordinate){
        this(Integer.valueOf(coordinate.split(",")[0]),  Integer.valueOf(coordinate.split(",")[1]));
    }

}
