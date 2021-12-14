package day14;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

@Getter
public class Polymerizator {

    public static List<PairInsertionRule> rules = new ArrayList<>();
    public static Map<String, Long> characterCount = new HashMap<>();

    PolyPair polyPair;

    public Polymerizator(String fileName){
        List<String> fileContent = readFilePerLine(fileName);
        polyPair = new PolyPair(fileContent.get(0));
        for(int i=2; i<fileContent.size(); i++){
            rules.add(new PairInsertionRule(fileContent.get(i)));
        }
        System.out.println("wait here");
    }





    private void updateNeighbour(Optional<PolyPair> polypair) {
        while (polypair.isPresent()) {
            Optional<PolyPair>  rightNeigbour = polypair.get().getRightNeighbour();
            if (rightNeigbour.isEmpty()) {
                break;
            } else {
                PolyPair neighbour = rightNeigbour.get();
                Optional<PolyPair> finalPolypair = polypair;
                String newNeighbourCode = rules.stream().filter(rule -> rule.getCodeCombination().equals(finalPolypair.get().getCode()) && rule.getNeighbourCode().equals(neighbour.getCode())).findFirst().orElseThrow().getReplacementCode();
                PolyPair newNeighbour = new PolyPair(newNeighbourCode, neighbour);
                polypair.get().setRightNeighbour(Optional.of(newNeighbour));
                polypair = rightNeigbour;
            }
        }
    }



    public long mostCommonCharacterMinusLeastCommonCharacterAfterSteps(int steps){
        updatePolymerTemplate(steps);
        List<Long> counts = characterCount.values().stream().sorted().collect(Collectors.toList());
        return counts.get(counts.size()-1) - counts.get(0);
    }

    public void updatePolymerTemplate(int steps){
        for(int i=1; i<=steps; i++){
            updateNeighbour(Optional.of(polyPair));
        }
    }

}
