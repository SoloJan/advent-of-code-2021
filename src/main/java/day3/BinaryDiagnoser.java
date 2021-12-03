package day3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class BinaryDiagnoser {

    public int getPower(String fileName) {
        char[] gammaRate = getGammaRate(fileName);
        char[] epsilonRate = getEpsilonRate(gammaRate);
        int gamma = Integer.parseInt(String.valueOf(gammaRate), 2);
        int epsilon = Integer.parseInt(String.valueOf(epsilonRate), 2);
        System.out.println("gamma: " + gamma);
        System.out.println("epsilon: " + epsilon);
        return gamma * epsilon;

    }

    public int getLifeSupport(String fileName) {
        char[] gammaRate = getGammaRate(fileName);
        char[] epsilonRate = getEpsilonRate(gammaRate);
        List<String> fileContent = readFilePerLine(fileName);
        List<char[]> chars = fileContent.stream().map(s -> s.toCharArray()).collect(Collectors.toList());
        char[] oxygenRate = filterMax(chars,  0).get(0);
        char[] scrubberRate = filterMin(chars,  0).get(0);
        int oxygen = Integer.parseInt(String.valueOf(oxygenRate), 2);
        int scrubber = Integer.parseInt(String.valueOf(scrubberRate), 2);
        System.out.println("oxygen: " + oxygen);
        System.out.println("scrubber: " + scrubber);
        return oxygen * scrubber;
    }


    List<char[]> filterMin(List<char[]> chars, final int pos){
        List<char[]> filteredChars = chars.stream().filter(c -> c[pos] == charGetLeastCommonBitAtPosition(chars, pos)).collect(Collectors.toList());
        if (filteredChars.size() == 1) {
            return filteredChars;
        }

        return filterMin(filteredChars, pos + 1);


    }

    List<char[]> filterMax(List<char[]> chars, final int pos) {
        List<char[]> filteredChars = chars.stream().filter(c -> c[pos] == getMostCommonBitAtPosition(chars, pos)).collect(Collectors.toList());
        if (filteredChars.size() == 1) {
            return filteredChars;
        }
        return filterMax(filteredChars, pos + 1);
    }

    private char charGetLeastCommonBitAtPosition(List<char[]> chars, final int pos){
        char mostCommonBit = getMostCommonBitAtPosition(chars, pos);
        if(mostCommonBit == '1'){
            return '0';
        }
        else return '1';
    }

    private char getMostCommonBitAtPosition(List<char[]> chars, final int pos) {
        int nrsOfOnesOnPos = 0;
        for (char[] charArray : chars) {
            if (charArray[pos] == '1') {
                nrsOfOnesOnPos++;
            }
        }
        if(nrsOfOnesOnPos >= ((double) chars.size())/2){
            return '1';
        }
        else{
            return '0';
        }
}


    private char[] getGammaRate(String fileName) {
        List<String> fileContent =  readFilePerLine(fileName);
        List<char[]> chars= fileContent.stream().map(s -> s.toCharArray()).collect(Collectors.toList());
        int binarySize = chars.get(0).length;
        int[] nrsOfOnesOnPos = new int[binarySize];
        for (char[] charArray: chars) {
            for(int i=0; i<charArray.length; i++){
                if(charArray[i]=='1'){
                    nrsOfOnesOnPos[i]++;
                }
            }
        }
        char[] gammaRate = new char[binarySize];

        for(int i=0; i<binarySize; i++){
            if(nrsOfOnesOnPos[i] >= fileContent.size()/2 ){
                gammaRate[i] = '1';
            }
            else {
                gammaRate[i] = '0';
            }
        }
        return gammaRate;
    }

    private char[] getEpsilonRate(char[] gammaRate){
        char[] epsilonRate = new char[gammaRate.length];
        for(int i=0; i<gammaRate.length; i++){
            if(gammaRate[i]=='1'){
                epsilonRate[i] = '0';
            }
            else {
                epsilonRate[i] = '1';
            }
        }
        return epsilonRate;
    }





}
