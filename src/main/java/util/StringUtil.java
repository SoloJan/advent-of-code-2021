package util;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    public static List<Integer> toIntegerList(String string){
        return toCharacterList(string).stream().map(c -> Character.getNumericValue(c)).collect(Collectors.toList());
    }


    public static List<Integer> toLongList(String string){
        return toCharacterList(string).stream().map(c -> Character.getNumericValue(c)).collect(Collectors.toList());
    }

    public static List<Character> toCharacterList(String string){
        return string.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
    }

    public static String toString(List<Character> characters){
        return characters.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static String hexToBinaryString(String hex) {
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }

}
