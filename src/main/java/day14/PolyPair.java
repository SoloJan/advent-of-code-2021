package day14;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

import static day14.Polymerizator.characterCount;
import static day14.Polymerizator.rules;

@Getter
@Setter
public class PolyPair {

    private String code;
    private Optional<PolyPair> rightNeighbour;


    public PolyPair(String polyTemplate){
        code = polyTemplate.substring(0, 1);
        addOrUpdateCharacterCount();
        if(polyTemplate.length()==1){
            rightNeighbour = Optional.empty();
        }
        else{
            rightNeighbour = Optional.of(new PolyPair(polyTemplate.substring(1)));
        }
    }

    public PolyPair(String code, PolyPair neighbour){
        this.code = code;
        addOrUpdateCharacterCount();
        rightNeighbour = Optional.of(neighbour);
    }



    private void addOrUpdateCharacterCount(){
        characterCount.putIfAbsent(code, 0l);
        characterCount.put(code, characterCount.get(code)+1);
    }


    public String toString(){
        StringBuilder sb  = new StringBuilder(code);
        if(rightNeighbour.isPresent()){
            sb.append(rightNeighbour.get());
        }
        return sb.toString();
    }



}
