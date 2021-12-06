package day6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class LanternFishCalculator {

    public static final int DAYS_BETWEEN_REPRODUCTION = 7;
    public static final int DAYS_UNTIL_FIRST_REPRODUCTION = 9;

    HashMap<Integer, Long> offSpring  = new HashMap<>();

    public long calculateAmountOfFish(String fileName, int days){
        List<Integer> fish = Arrays.asList(readFilePerLine(fileName).get(0).split(",")).stream().map(s-> Integer.valueOf(s)).collect(Collectors.toList());
        long sum = fish.size();
        for(Integer daysUntilReproduction : fish){
            sum += countOffSpring(daysUntilReproduction, days);
        }
        return sum;
    }

    private long countOffSpring(int daysUntilOffspring, int days){
        int daysToReproduce = days - daysUntilOffspring;
        if(offSpring.containsKey(daysToReproduce)) {
            return offSpring.get(daysToReproduce);
        }
        else {
            long nrOfOffspring = 0L;
            for (int i = 0; i < daysToReproduce; i = i + DAYS_BETWEEN_REPRODUCTION) {
                nrOfOffspring = nrOfOffspring + 1 + countOffSpring(DAYS_UNTIL_FIRST_REPRODUCTION, daysToReproduce - i);
            }
            offSpring.put(daysToReproduce, nrOfOffspring);
            return nrOfOffspring;
        }
    }

}
