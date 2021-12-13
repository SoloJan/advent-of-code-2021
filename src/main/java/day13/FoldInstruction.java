package day13;

import lombok.Getter;

@Getter
public class FoldInstruction {

    private String direction;
    private int value;

    public FoldInstruction(String line){
        String[] lineSplit = line.split("=");
        value = Integer.valueOf(lineSplit[1]);
        direction = lineSplit[0].substring(lineSplit[0].length()-1);
    }




}
