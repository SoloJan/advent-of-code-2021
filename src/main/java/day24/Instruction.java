package day24;

import lombok.Getter;

@Getter
public class Instruction {

    public Instruction(String instruction){
        String [] in = instruction.split(" ");
        operation = in[0];
        var1 = in[1];
        if(in.length > 2) {
            var2 = in[2];
        }
    }

    String operation;
    String var1;
    String var2;

}
