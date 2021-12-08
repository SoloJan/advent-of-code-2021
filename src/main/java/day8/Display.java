package day8;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Display {

    private Map<Integer, Set<Segment>> displayTranslation = new HashMap<>();
    List<Digit> inputs;
    List<Digit> outputs;

    public Display(String[] displayInput){
        inputs =   toDigitList(displayInput[0]);
        outputs =  toDigitList(displayInput[1]);
        createTranslationMapFromInputs();
    }

    private void createTranslationMapFromInputs() {
        mapUniqueValues();
        mapZeroNineAndSix();
        mapTwoThreeAndVifes();
    }

    private void mapZeroNineAndSix() {
        inputs.stream().filter(d -> d.getSegments().size() == 6).forEach(digit -> displayTranslation.put(digit.getValue(displayTranslation), digit.getSegments()));
    }

    private void mapTwoThreeAndVifes(){
        inputs.stream().filter(d -> d.getSegments().size() == 5).forEach(digit -> displayTranslation.put(digit.getValue(displayTranslation), digit.getSegments()));
    }

    public int getOutput(){
        StringBuilder sb = new StringBuilder();
        outputs.stream().forEach( output ->  sb.append(output.getValue(displayTranslation)));
        return Integer.valueOf(sb.toString());
    }

    private void mapUniqueValues(){
        inputs.stream().filter(Digit::isUniquelyDefinedBySizeOfSegments).forEach(digit -> displayTranslation.put(digit.getValue(displayTranslation), digit.getSegments()));
    }

    private List<Digit> toDigitList(String digits){
        return Arrays.asList(digits.trim().split(" ")).stream().map(s -> new Digit(s)).collect(Collectors.toList());
    }







}
