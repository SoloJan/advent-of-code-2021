package day4;

public class BingoNumber {

    private int number;
    private boolean isDrawn;

    public BingoNumber(int number){
        this.number = number;
        this.isDrawn = false;
    }

    public void hit(int number){
        if(number == this.number){
            isDrawn = true;
        }
    }

    public int getNumber(){
        return this.number;
    }

    public boolean isDrawn(){
        return isDrawn;
    }


}
