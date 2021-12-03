package day3;

import util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

import static util.CollectionUtil.applyToEveryElement;
import static util.FileUtil.readFilePerLine;
import static util.CollectionUtil.filterUntilOneIsLeft;
import static util.StringUtil.toCharacterList;


public class BinaryDiagnoser {

    public int getPower(String fileName) {
        List<List<Character>> binaryInstructions = readFilePerLine(fileName).stream().map(s -> toCharacterList(s)).collect(Collectors.toList());
        List<Character> gammaRate = applyToEveryElement(binaryInstructions, this::getMostCommonBitAtPosition);
        List<Character> epsilonRate = applyToEveryElement(binaryInstructions, this::getLeastCommonBitAtPosition);
        return getDecimal(gammaRate) * getDecimal(epsilonRate);
    }

    public int getLifeSupport(String fileName) {
        List<List<Character>> binaryInstructions = readFilePerLine(fileName).stream().map(s -> toCharacterList(s)).collect(Collectors.toList());
        List<Character> oxygenRate = filterUntilOneIsLeft(binaryInstructions, this::getMostCommonBitAtPosition).get(0);
        List<Character> scrubberRate = filterUntilOneIsLeft(binaryInstructions, this::getLeastCommonBitAtPosition).get(0);
        return getDecimal(oxygenRate) * getDecimal(scrubberRate);
    }

    private char getMostCommonBitAtPosition(List<List<Character>> binaryInstructions, final int position) {
        long nrsOfOnesOnPos =  binaryInstructions.stream().filter(instruction -> instruction.get(position) == '1').count();
        return nrsOfOnesOnPos >= ((double) binaryInstructions.size()/2) ? '1' : '0';
    }

    private int getDecimal(List<Character> binaryString){
        return Integer.parseInt(StringUtil.toString(binaryString), 2);
    }

    private char getLeastCommonBitAtPosition(List<List<Character>>  binaryInstructions, final int position){
        return reverseBit(getMostCommonBitAtPosition(binaryInstructions, position));
    }

    private char reverseBit(char bit){
        return bit == '1' ? '0' : '1';
    }

}
