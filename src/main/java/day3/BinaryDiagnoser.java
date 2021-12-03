package day3;



import util.CollectionUtil;
import util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;
import static util.CollectionUtil.filter;
import static util.StringUtil.toCharacterList;


public class BinaryDiagnoser {

    public int getPower(String fileName) {
        List<List<Character>> chars = readFilePerLine(fileName).stream().map(s -> toCharacterList(s)).collect(Collectors.toList());
        List<Character> gammaRate = CollectionUtil.getBitString(chars, this::getMostCommonBitAtPosition);
        List<Character> epsilonRate = CollectionUtil.getBitString(chars, this::getLeastCommonBitAtPosition);
        return getDecimal(gammaRate) * getDecimal(epsilonRate);
    }

    public int getLifeSupport(String fileName) {
        List<List<Character>> chars = readFilePerLine(fileName).stream().map(s -> toCharacterList(s)).collect(Collectors.toList());
        List<Character> oxygenRate = filter(chars,  0, this::getMostCommonBitAtPosition).get(0);
        List<Character> scrubberRate = filter(chars, 0, this::getLeastCommonBitAtPosition).get(0);
        return getDecimal(oxygenRate) * getDecimal(scrubberRate);
    }

    private char getMostCommonBitAtPosition(List<List<Character>> chars, final int pos) {
        long nrsOfOnesOnPos =  chars.stream().filter(charArray -> charArray.get(pos) == '1').count();
        return nrsOfOnesOnPos >= ((double) chars.size()/2) ? '1' : '0';
    }

    private int getDecimal(List<Character> binaryString){
        return Integer.parseInt(StringUtil.toString(binaryString), 2);
    }

    private char getLeastCommonBitAtPosition(List<List<Character>>  chars, final int pos){
        return reverseBit(getMostCommonBitAtPosition(chars, pos));
    }

    private char reverseBit(char bit){
        return bit == '1' ? '0' : '1';
    }

}
