package day3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class BinaryDiagnoser {

    public int getPower(String fileName) {
        List<char[]> chars = readFilePerLine(fileName).stream().map(s -> s.toCharArray()).collect(Collectors.toList());
        char[] gammaRate = getBitString(chars, this::getMostCommonBitAtPosition);
        char[] epsilonRate = getBitString(chars, this::getLeastCommonBitAtPosition);
        return getDecimal(gammaRate) * getDecimal(epsilonRate);
    }

    public int getLifeSupport(String fileName) {
        List<char[]> chars = readFilePerLine(fileName).stream().map(s -> s.toCharArray()).collect(Collectors.toList());
        char[] oxygenRate = filter(chars,  0, this::getMostCommonBitAtPosition).get(0);
        char[] scrubberRate = filter(chars, 0, this::getLeastCommonBitAtPosition).get(0);
        return getDecimal(oxygenRate) * getDecimal(scrubberRate);
    }

    private List<char[]> filter(List<char[]> chars, final int pos, BiFunction<List,Integer,Character> filterFunction){
        List<char[]> filteredChars = chars.stream().filter(c -> c[pos] == filterFunction.apply(chars, pos)).collect(Collectors.toList());
        if (filteredChars.size() == 1 || pos == filteredChars.get(0).length) {
            return filteredChars;
        }
        return filter(filteredChars, pos + 1, filterFunction);
    }

    private char[]  getBitString(List<char[]> chars, BiFunction<List,Integer,Character> filterFunction){
        char[]  bitString  = new char[chars.get(0).length];
        for(int i=0; i<bitString.length; i++){
            bitString[i] = filterFunction.apply(chars, i);
        }
        return bitString;
    }

    private int getDecimal(char[] binaryString){
        return Integer.parseInt(String.valueOf(binaryString), 2);
    }

    private char getLeastCommonBitAtPosition(List<char[]> chars, final int pos){
        return reverseBit(getMostCommonBitAtPosition(chars, pos));
    }

    private char reverseBit(char bit){
        return bit == '1' ? '0' : '1';
    }

    private char getMostCommonBitAtPosition(List<char[]> chars, final int pos) {
        long nrsOfOnesOnPos =  chars.stream().filter(charArray -> charArray[pos] == '1').count();
        return nrsOfOnesOnPos >= ((double) chars.size()/2) ? '1' : '0';
    }

}
