package day7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class PositionAligner {


    /**
     * Imagine a group of people all living in one straight line, the question is where should all these people meet if they want to minimize the total combined travel distance.
     * @param fileName a file with the horizontal positions as comma separated integers
     * @return the total distance traveled
     */
    public int findShortestDistanceToCommonPoint(String fileName) {
        List<Integer> horizontalPositions = Arrays.asList(readFilePerLine(fileName).get(0).split(",")).stream().map(s -> Integer.valueOf(s)).sorted().collect(Collectors.toList());
        Long median =  Math.round((double)horizontalPositions.size() / 2) - 1;
        Integer pointToTravelTo = horizontalPositions.get(median.intValue());
        return horizontalPositions.stream().map(p ->  Math.abs(pointToTravelTo-p)).reduce(Integer::sum).orElse(0);

    }

    /**
     * Imagine a group of people all living in one straight line, the question is where should all these people meet if they want to minimize the combined fuel consumption.
     * Where fuel consumption is increased when more distance is travelled, the first mile 1 gallon of fuel is consumed, the second mile 2 gallon, and the third mile 3 gallons and so on
     * This means that outliers weight more heavy than in the scenario of the minimum distance.
     * @param fileName a file with the horizontal positions as comma separated integers
     * @return the total fuel spend
     */

    public int findFuelConsumption(String fileName) {
        List<Integer> horizontalPositions = Arrays.asList(readFilePerLine(fileName).get(0).split(",")).stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
        // start at average as default
        int average  =  Long.valueOf(Math.round((double) horizontalPositions.stream().reduce(0, Integer::sum)/horizontalPositions.size())).intValue();

        int minValue = Integer.MAX_VALUE;

        //test numbers just below average
        for(int i= average; i>0;  i-- ){
            int finalI = i;
            int newValue = horizontalPositions.stream().map(p ->  getFuelSpent(p, finalI)).reduce(0, Integer::sum);
            if(newValue > minValue) {
              break;
            }
            else{
                minValue = newValue;
            }
        }

        //test numbers just above average
        for(int i= average; i<horizontalPositions.stream().mapToInt(p -> p).max().orElse(average);  i++ ){
            int finalI = i;
            int newValue = horizontalPositions.stream().map(p ->  getFuelSpent(p, finalI)).reduce(0, Integer::sum);
            if(newValue > minValue) {
                break;
            }
            else{
                minValue = newValue;
            }
        }

        return minValue;
    }

    private int getFuelSpent(int position, int positionToMoveTo){
        int steps = Math.abs(positionToMoveTo - position);
        return Long.valueOf(Math.round((double)(steps + 1) * steps / 2 )).intValue();
    }



}
