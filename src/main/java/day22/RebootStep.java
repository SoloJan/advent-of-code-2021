package day22;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class RebootStep {

    long lowerX;
    long upperX;
    long lowerY;
    long upperY;
    long lowerZ;
    long upperZ;
    boolean on;

    public RebootStep(String rebootStep){
        if(rebootStep.startsWith("on")){
            on = true;
        }
        Matcher matcher = Pattern.compile("\\d+").matcher(rebootStep);
        lowerX = getNextInteger(matcher, rebootStep);
        upperX = getNextInteger(matcher, rebootStep);
        lowerY = getNextInteger(matcher, rebootStep);
        upperY = getNextInteger(matcher, rebootStep);
        lowerZ = getNextInteger(matcher, rebootStep);
        upperZ = getNextInteger(matcher, rebootStep);
    }

    private long getNextInteger(Matcher matcher, String rebootStep){
        matcher.find();
        long value = Long.valueOf(matcher.group());
        if(rebootStep.charAt(matcher.start()-1) == '-'){
            value = value *-1;
        }

        return value;
    }

}
