package day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.FileUtil.readFilePerLine;

public class Exercise {

    private List<PairInsertionRule> rules = new ArrayList<>();
    private Map<String, Integer> pairCount  = new HashMap<>();
    private Map<Character, Integer> charCount = new HashMap<>();

    public Exercise(String fileName){
        List<String> fileContent = readFilePerLine(fileName);
        for(int i=2; i<fileContent.size(); i++){
            rules.add(new PairInsertionRule(fileContent.get(i)));
        }

    }





}
