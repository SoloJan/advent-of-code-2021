package day14;

import lombok.Getter;

@Getter
public class PairInsertionRule {

    private String codeCombination;
    private String neighbourCode;
    private String replacementCode;

    public PairInsertionRule(String rule){
        codeCombination = rule.substring(0,1);
        neighbourCode = rule.substring(1,2);
        replacementCode = rule.substring(rule.length()-1);
    }




}
