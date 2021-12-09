package util;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    public static List<Integer> toIntegerList(String string){
        return toCharacterList(string).stream().map(c -> Character.getNumericValue(c)).collect(Collectors.toList());
    }

    public static List<Character> toCharacterList(String string){
        return string.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
    }

    public static String toString(List<Character> characters){
        return characters.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
