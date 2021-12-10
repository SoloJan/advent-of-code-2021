package day10;

import java.util.*;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class SyntaxChecker {

    private Map<Character, Integer> syntaxErrorScores = new HashMap<>();
    private Map<Character, Character> tags = new HashMap<>();
    private Map<Character, Integer> autoCompleteScores = new HashMap<>();



    public SyntaxChecker(){
        syntaxErrorScores.put(')', 3);
        syntaxErrorScores.put(']', 57);
        syntaxErrorScores.put('}', 1197);
        syntaxErrorScores.put('>', 25137);
        tags.put(')', '(');
        tags.put(']', '[');
        tags.put('}', '{');
        tags.put('>', '<');
        autoCompleteScores.put('(', 1);
        autoCompleteScores.put('[', 2);
        autoCompleteScores.put('{', 3);
        autoCompleteScores.put('<', 4);
    }

    public Integer getTotalSyntaxErrorScore(String fileName){
        return readFilePerLine(fileName).stream().map(this::getSyntaxErrorScore).filter(s ->s.isPresent()).map(s -> s.get()).reduce(0, Integer::sum);
    }

    public Long getMedianAutoCompleteScore(String fileName){
        List<Long> scores = readFilePerLine(fileName).stream().filter(s -> getSyntaxErrorScore(s).isEmpty()).map(this::getAutoCompleteScore).filter(s ->s.isPresent()).map(s -> s.get()).sorted().collect(Collectors.toList());
        Long median =  Math.round((double)scores.size() / 2) - 1;
        return scores.get(median.intValue());
    }



    public Optional<Integer> getSyntaxErrorScore(String string){
        List<Character> toBeCLosed = new LinkedList<>();
        for(Character character: string.toCharArray()){
            if(tags.values().contains(character)){
                toBeCLosed.add(character);
            }
            if(tags.containsKey(character)){
                if(toBeCLosed.size()>0 && toBeCLosed.get(toBeCLosed.size()-1).equals(tags.get(character))){
                    toBeCLosed.remove(toBeCLosed.size()-1);
                }
                else{
                    return Optional.of(syntaxErrorScores.get(character));
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Long> getAutoCompleteScore(String string){
        List<Character> toBeCLosed = new LinkedList<>();
        for(Character character: string.toCharArray()){
            if(tags.values().contains(character)){
                toBeCLosed.add(character);
            }
            if(tags.containsKey(character)){
                if(toBeCLosed.size()>0 && toBeCLosed.get(toBeCLosed.size()-1).equals(tags.get(character))){
                    toBeCLosed.remove(toBeCLosed.size()-1);
                }
            }
        }
        if(toBeCLosed.isEmpty()){
            return Optional.empty();
        }
        long score = 0;
        for(int i=toBeCLosed.size()-1; i>=0; i--){
            score = score * 5;
            score += autoCompleteScores.get(toBeCLosed.get(i));
        }
        return Optional.of(score);

    }





}
