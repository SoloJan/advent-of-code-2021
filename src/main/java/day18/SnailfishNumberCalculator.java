package day18;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static util.FileUtil.readFilePerLine;

public class SnailfishNumberCalculator {


    public long maxMagnitude(String fileName){
        List<String>  numbers = readFilePerLine(fileName);
        long maxMagnitude = 0l;
        for(int i= 0; i<numbers.size(); i++){
            for(int j=0; j<numbers.size(); j++){
                if(i == j){
                    continue;
                }
                long magnitude = new SnailfishNumber(add(numbers.get(i), numbers.get(j))).magnitude();
                if(magnitude > maxMagnitude){
                    maxMagnitude = magnitude;
                }
            }
        }
        return maxMagnitude;
    }

    public long magnitudeOfFile(String fileName){
        SnailfishNumber snailfishNumber = new SnailfishNumber(addAllInFile(fileName));
        return snailfishNumber.magnitude();
    }

    public String add(String firstNumber, String secondNumber){
        //System.out.println("Firstnumber : " +firstNumber);
     //   System.out.println("Secondnumber : " +secondNumber);
        String  snailFishNumber = new StringBuilder("[").append(firstNumber).append(",").append(secondNumber).append("]").toString();
        while(canReduce(snailFishNumber)){
        //    System.out.println("intermediate : " + snailFishNumber);
            snailFishNumber = reduce(snailFishNumber);
        }
       // System.out.println("Result: " +snailFishNumber);
        return  snailFishNumber;
    }

    public String addAllInFile(String fileName){
        List<String>  numbers = readFilePerLine(fileName);
        String snailFishNumber = numbers.get(0);
        for(int i =1; i<numbers.size(); i++){
       //     System.out.println(snailFishNumber);
            snailFishNumber = add(snailFishNumber, numbers.get(i));
        }
        return snailFishNumber;
    }


    String reduce(String snailFishNumber){
        if(canExplode(snailFishNumber)){
            return  explode(snailFishNumber);
        }
        return split(snailFishNumber);
    }

    public boolean canReduce(String snailFishString){
        return indexOfSnailNumberToSplit(snailFishString).isPresent() || canExplode(snailFishString);
    }

    boolean canExplode(String snailFishString){
        return indexOfSnailNumberToExplode(snailFishString).isPresent();
    }

    public String explode(String snailFishString){
        Optional<Integer> index = indexOfSnailNumberToExplode(snailFishString);
        if(index.isEmpty()){
            return snailFishString;
        }
        StringBuilder sb = new StringBuilder(snailFishString);
        int x = Integer.valueOf(String.valueOf(snailFishString.charAt(index.get()+1)));
        int indexOfYCoordinate = index.get()+3;
        int indexOfFinalBracket = indexOfYCoordinate+1;
        if(snailFishString.charAt(index.get()+2) != ','){
            x = Integer.valueOf(snailFishString.substring(index.get()+1, index.get()+3));
            indexOfYCoordinate ++;
            indexOfFinalBracket ++;
        }
        int y = Integer.valueOf(String.valueOf(snailFishString.charAt(indexOfYCoordinate)));
        if(snailFishString.charAt(indexOfYCoordinate+1)!= ']'){
            y = Integer.valueOf(snailFishString.substring(indexOfYCoordinate, indexOfYCoordinate+2));
            indexOfFinalBracket++;
        }

        Optional<Integer> digitToTheRightIndex = indexOfFirstDigitToTheRight(snailFishString, indexOfFinalBracket);
        Optional<Integer> digitToTheLeft = indexOfFirstDigitToTheLeft(snailFishString, index.get());
        if(digitToTheRightIndex.isPresent()){
            int startIndex = digitToTheRightIndex.get();
            int endIndex = startIndex+1;
            if(Character.isDigit(snailFishString.charAt(endIndex))){
                endIndex++;
            }
            sb.replace(startIndex, endIndex, String.valueOf(Integer.valueOf(snailFishString.substring(startIndex, endIndex)) + y));
        }
        sb.replace(index.get(), indexOfFinalBracket+1, "0");
        if(digitToTheLeft.isPresent()){
            int startIndex = digitToTheLeft.get();
            int endIndex = startIndex+1;
            if(Character.isDigit(snailFishString.charAt(endIndex))){
                endIndex++;
            }
            if(Character.isDigit(snailFishString.charAt(startIndex-1))){
                startIndex--;
            }
            sb.replace(startIndex, endIndex, String.valueOf(Integer.valueOf(snailFishString.substring(startIndex, endIndex)) + x));
        }
        return sb.toString();
    }


    public String split(String snailFishString){
        Optional<Integer> index = indexOfSnailNumberToSplit(snailFishString);
        if(index.isEmpty()){
            return snailFishString;
        }
        int digit = Integer.valueOf(snailFishString.substring(index.get(), index.get()+2));
        StringBuilder sb = new StringBuilder(snailFishString);
        int firstDigit  =   (int) Math.floor((double)digit/2.0);
        int secondDigit  =   (int) Math.ceil((double)digit/2.0);
        sb.replace(index.get(), index.get()+2, "[" +firstDigit+","+secondDigit +"]" );
        return  sb.toString();
    }

    private Optional<Integer> indexOfSnailNumberToSplit(String snailFishString) {
        boolean previousIndexIsDigit = false;
        for(int index = 0; index< snailFishString.length(); index++) {
            if (Character.isDigit(snailFishString.charAt(index))) {
                if (previousIndexIsDigit) {
                    return Optional.of(index - 1);
                }
                    previousIndexIsDigit = true;
            }
            else {
                previousIndexIsDigit = false;
            }
        }
        return Optional.empty();
    }


    public Optional<Integer> indexOfFirstDigitToTheLeft(String string, int index){
        for(int i = index; i>=0; i--) {
            if (Character.isDigit(string.charAt(i))) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    public Optional<Integer> indexOfFirstDigitToTheRight(String string, int index){
        for(int i = index; i< string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }


    public Optional<Integer> indexOfSnailNumberToExplode(String string) {
        List<Character> toBeCLosed = new LinkedList<>();
        for(int index = 0; index< string.length(); index++){
            if (string.charAt(index) == '[') {
                toBeCLosed.add(string.charAt(index));
                if (toBeCLosed.size() == 5) {
                    return Optional.of(index);
                }
            }
            if (string.charAt(index) == ']') {
                toBeCLosed.remove(toBeCLosed.size() - 1);

            }
        }
        return  Optional.empty();
    }

}
