package day8;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

import static util.StringUtil.toCharacterList;

@Getter
public class Digit {

    private Set<Segment> segments;

    public Digit(String input){
         segments = toCharacterList(input).stream().map(this::toSegment).collect(Collectors.toSet());
    }

    public Integer getValue(Map<Integer, Set<Segment>> displayTranslation) {
        switch (segments.size()) {
            case 2:
                return 1;
            case 3:
                return 7;
            case 4:
                return 4;
            case 5:
                return getTwoThreeOrFive(displayTranslation);
            case 6:
                return getZeroNineOrSix(displayTranslation);
            case 7:
                return 8;
            default:
                throw  new RuntimeException("No number could be defined with the given segments" + segments);
        }
    }

    public boolean isUniquelyDefinedBySizeOfSegments(){
        return   segments.size() == 7 || segments.size() == 2 || segments.size() == 3 || segments.size() == 4;
    }
    
    private int getZeroNineOrSix(Map<Integer, Set<Segment>> displayTranslation){
        if(segments.containsAll(displayTranslation.get(4))){
            return 9;
        }
        else if(segments.containsAll(displayTranslation.get(1))){
            return 0;
        }
        else{
            return 6;
        }
    }

    private int getTwoThreeOrFive(Map<Integer, Set<Segment>> displayTranslation){
        if(segments.containsAll(displayTranslation.get(1))){
          return 3;
        }
        else if(segments.contains(getCSegment(displayTranslation))){
            return 2;
        }
        else{
          return 5;
        }
    }

    private Segment getCSegment(Map<Integer, Set<Segment>> displayTranslation){
        Set<Segment> six = displayTranslation.get(6);
        for(Segment segment: Segment.values()){
            if(!six.contains(segment)){
                return segment;
            }
        }
        throw  new RuntimeException("C segment not found");
    }

    private Segment toSegment(Character character){
       return Segment.valueOf(Character.toString(character).toUpperCase(Locale.ROOT));
    }




}
