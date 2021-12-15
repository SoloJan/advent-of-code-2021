package day14;

import lombok.Getter;

@Getter
public class PairInsertionRule {

    private String codeCombination;
    private String replacementCode;

    public PairInsertionRule(String rule){
        codeCombination = rule.substring(0,2);
        replacementCode = rule.substring(rule.length()-1);
    }




}
