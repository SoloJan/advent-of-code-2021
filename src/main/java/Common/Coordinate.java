package Common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Coordinate {

    private int x;
    private int y;

    public  Coordinate(String coordinate){
        this(Integer.valueOf(coordinate.split(",")[0]),  Integer.valueOf(coordinate.split(",")[1]));
    }

}
