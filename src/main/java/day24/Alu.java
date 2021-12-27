package day24;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;
import static util.StringUtil.toIntegerList;

public class Alu {

    List<Instruction> instructions;

    public Alu(String fileName){
        instructions = readFilePerLine(fileName).stream().map(Instruction::new).collect(Collectors.toList());
    }

    public Map<String, Long> followInstructions(Map<String, Long> variables,  String modelNumber){
        List<Integer>  inputs =  toIntegerList(modelNumber);
        int i = 0;
        for(Instruction instruction: instructions){
            if(instruction.operation.equals("inp")){
                variables.put(instruction.getVar1(), (long) inputs.get(i));
                i++;
            }
            else {
                try {
                    variables = followInstruction(variables, instruction);
                }
                catch (Exception e){
                    variables.put("z", Long.MAX_VALUE);
                }
            }
        }
        return variables;
    }

    public Map<String, Long> followInstructions(String modelNumber){
        Map<String, Long> variables = new HashMap<>();
        variables.put("x", 0l);
        variables.put("y", 0l);
        variables.put("z", 0l);
        variables.put("w", 0l);
        return followInstructions(variables, modelNumber);

    }

    private Map<String, Long> followInstruction(Map<String, Long> variables, Instruction instruction){
        switch (instruction.operation){
            case "add" :
                variables.put(instruction.getVar1(), variables.get(instruction.getVar1()) + getValue(variables, instruction.getVar2()));
                return variables;
            case "mul" :
                variables.put(instruction.getVar1(), variables.get(instruction.getVar1()) * getValue(variables, instruction.getVar2()));
                return variables;
            case "div" :
                long var1 = variables.get(instruction.getVar1());
                long var2 = getValue(variables, instruction.getVar2());
                if(var2 == 0){
                    throw new AluException();
                }
                variables.put(instruction.getVar1(), (long)Math.floor((double)var1/(double)var2));
                return variables;
            case "mod" :
                if(variables.get(instruction.getVar1())  < 0 || getValue(variables, instruction.getVar2()) <= 0) {
                    throw new AluException();
                 }
                variables.put(instruction.getVar1(), variables.get(instruction.getVar1()) % getValue(variables, instruction.getVar2()));
                return variables;
            case "eql" :
                long result  = variables.get(instruction.getVar1()) ==  getValue(variables, instruction.getVar2()) ? 1 : 0;
                variables.put(instruction.getVar1(), result);
                return variables;
        }
        return variables;
    }

    private Long getValue(Map<String, Long > variables, String variable){
        if(variables.containsKey(variable)){
            return variables.get(variable);
        }
        return Long.valueOf(variable);
    }

}
