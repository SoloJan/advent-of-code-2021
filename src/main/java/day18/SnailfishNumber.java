package day18;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Getter
public class SnailfishNumber {

    Optional<Integer> xNumber = Optional.empty();
    Optional<Integer> yNumber = Optional.empty();
    Optional<SnailfishNumber> xSnailFish = Optional.empty();
    Optional<SnailfishNumber> ySnailFish = Optional.empty();
    Optional<SnailfishNumber>  parent = Optional.empty();
    String snailFishNumberString;
    int depth = 0;



    public SnailfishNumber(String snailFishNumberString){
        this.snailFishNumberString = snailFishNumberString;
        if(Character.isDigit(snailFishNumberString.charAt(1))) {
            xNumber = Optional.of(Integer.valueOf(String.valueOf(snailFishNumberString.charAt(1))));
        }
        else{
            xSnailFish = Optional.of(new SnailfishNumber(snailFishNumberString.substring(1)));
        }
        int startOfY = getIndexWhereYStarts(snailFishNumberString);
        if(Character.isDigit(snailFishNumberString.charAt(startOfY))){
            yNumber = Optional.of(Integer.valueOf(String.valueOf(snailFishNumberString.charAt(startOfY))));
        }
        else{
            ySnailFish = Optional.of(new SnailfishNumber(snailFishNumberString.substring(startOfY)));
        }
    }


    public long magnitude(){
        long leftValue = 0;
        if(xNumber.isPresent()){
            leftValue = xNumber.get();
        }
        else{
            leftValue = xSnailFish.get().magnitude();
        }
        long rightValue = 0;
        if(yNumber.isPresent()){
            rightValue = yNumber.get();
        }
        else{
            rightValue = ySnailFish.get().magnitude();
        }
        return (3*leftValue) +  (2*rightValue);
    }


    private int getIndexWhereYStarts(String snailFishNumberString){
        if(Character.isDigit(snailFishNumberString.charAt(1))){
            return 3;
        }
        return indexOfClosingBracket(snailFishNumberString);
    }

    public Integer indexOfClosingBracket(String string) {
        List<Character> toBeCLosed = new LinkedList<>();
        int index = 1;
        toBeCLosed.add('[');
        while (!toBeCLosed.isEmpty()) {
            index++;
            if (string.charAt(index) == '[') {
                toBeCLosed.add(string.charAt(index));
            }
            if (string.charAt(index) == ']') {
                toBeCLosed.remove(toBeCLosed.size() - 1);
                if (toBeCLosed.isEmpty()) {
                    return index + 2;
                }
            }
        }
        return  index+2;
    }




}
