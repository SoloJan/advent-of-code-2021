package util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class CollectionUtil {

    public static <T>  List<List<T>> filter(List<List<T>> chars, final int pos, BiFunction<List, Integer, Character> filterFunction){
        List<List<T>> filteredChars = chars.stream().filter(c -> c.get(pos) == filterFunction.apply(chars, pos)).collect(Collectors.toList());
        if (filteredChars.size() == 1 || pos == filteredChars.get(0).size()) {
            return filteredChars;
        }
        return filter(filteredChars, pos + 1, filterFunction);
    }

    public static  <T> List<T>  getBitString(List<List<Character>> chars, BiFunction<List, Integer, T> filterFunction){
        List<T>  binaryString  = new ArrayList<>();
        for(int i=0; i<chars.get(0).size(); i++){
            binaryString.add(filterFunction.apply(chars, i));
        }
        return binaryString;
    }
}
