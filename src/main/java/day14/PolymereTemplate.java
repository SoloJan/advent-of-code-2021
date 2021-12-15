package day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static util.FileUtil.readFilePerLine;

public class PolymereTemplate {

    private List<PairInsertionRule> rules = new ArrayList<>();
    private Map<String, Long> pairCount;
    private Map<String, Long> charCount = new HashMap<>();

    public PolymereTemplate(String fileName){
        List<String> fileContent = readFilePerLine(fileName);
        for(int i=2; i<fileContent.size(); i++){
            rules.add(new PairInsertionRule(fileContent.get(i)));
        }

        pairCount = getInitialPairCount();
        String polymerTemplate = fileContent.get(0);
        for(int i = 0; i<polymerTemplate.length()-1; i++){
            addOrUpdateCharacterCount(polymerTemplate.substring(i, i+1), 1l);
            String codePair = polymerTemplate.substring(i,i+2);
            pairCount.put(codePair, pairCount.get(codePair)+1);
        }
        addOrUpdateCharacterCount(polymerTemplate.substring(polymerTemplate.length()-1), 1l);
    }


    public long mostCommonCharacterMinusLeastCommonCharacterAfterSteps(int steps){
        updatePolymerTemplate(steps);
        List<Long> counts = charCount.values().stream().sorted().collect(Collectors.toList());
        return counts.get(counts.size()-1) - counts.get(0);
    }

    public void updatePolymerTemplate(int steps){
        for(int i=1; i<=steps; i++){
            step();
        }
    }


    private void step(){
        Map<String, Long> newPairCount = getInitialPairCount();
        for(PairInsertionRule rule: rules){
            String codeCombination = rule.getCodeCombination();
            long timesToApplyRule = pairCount.get(codeCombination);
            String code1 = new StringBuilder(rule.getCodeCombination().substring(0,1)).append(rule.getReplacementCode()).toString();
            String code2 = new StringBuilder(rule.getReplacementCode()).append(rule.getCodeCombination().substring(1,2)).toString();
            newPairCount.put(code1, newPairCount.get(code1)+timesToApplyRule);
            newPairCount.put(code2, newPairCount.get(code2)+timesToApplyRule);
            addOrUpdateCharacterCount(rule.getReplacementCode(), timesToApplyRule);

        }
        pairCount = newPairCount;
    }

    private void addOrUpdateCharacterCount(String code, long ammountToUpdate){
        charCount.putIfAbsent(code, 0l);
        charCount.put(code, charCount.get(code)+ammountToUpdate);
    }


    private Map<String, Long> getInitialPairCount(){
        Map<String, Long> initialPairCount = new HashMap<>();
        rules.forEach( r -> initialPairCount.put(r.getCodeCombination(), 0L));
        return initialPairCount;
    }




}
