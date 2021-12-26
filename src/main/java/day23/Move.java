package day23;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Move {

    int fromX;
    int fromY;
    int toX;
    int toY;
    String type;

    public String toString(){
        return "From: " + fromX +  "," + fromY + " To : " + toX + "," + toY + " type = " + type;
    }
}
