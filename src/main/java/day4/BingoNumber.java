package day4;

import lombok.Getter;

@Getter
public class BingoNumber {

    private int number;
    private boolean drawn;

    public BingoNumber(int number){
        this.number = number;
        this.drawn = false;
    }

    public void hit(int number){
        if(number == this.number){
            drawn = true;
        }
    }

}
