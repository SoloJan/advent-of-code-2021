import day1.SonarSweeper;
import day2.Navigator;
import day3.BinaryDiagnoser;

public class AdventOfCodeAnswers {


    public static void main(String[] args) {
        System.out.println(String.format("The answer to the day 1 exercise 1 is: %d", new SonarSweeper().calculateNrOfIncreasedDepths("day1/input.txt")));
        System.out.println(String.format("The answer to the day 1 exercise 2 is: %d", new SonarSweeper().calculateNrOfIncreasedDepthsWithSlidingWindow("day1/input.txt")));
        System.out.println(String.format("The answer to the day 2 exercise 2 is: %d", new Navigator().calculatePosition("day2/input.txt")));
        System.out.println(String.format("The answer to the day 2 exercise 2 is: %d", new Navigator().calculatePositionWithAim("day2/input.txt")));
        System.out.println(String.format("The answer to the day 3 exercise 1 is: %d", new BinaryDiagnoser().getPower("day3/input.txt")));
        System.out.println(String.format("The answer to the day 3 exercise 2 is: %d", new BinaryDiagnoser().getLifeSupport("day3/input.txt")));
    }

}
