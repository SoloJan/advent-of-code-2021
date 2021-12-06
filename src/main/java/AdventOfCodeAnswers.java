import day5.HydrothermalVents;
import day1.SonarSweeper;
import day2.Navigator;
import day3.BinaryDiagnoser;
import day4.Bingo;
import day6.LanternFishCalculator;

public class AdventOfCodeAnswers {


    public static void main(String[] args) {
        System.out.println(String.format("The answer to the day 1 exercise 1 is: %d", new SonarSweeper().calculateNrOfIncreasedDepths("day1/input.txt")));
        System.out.println(String.format("The answer to the day 1 exercise 2 is: %d", new SonarSweeper().calculateNrOfIncreasedDepthsWithSlidingWindow("day1/input.txt")));
        System.out.println(String.format("The answer to the day 2 exercise 1 is: %d", new Navigator().calculatePosition("day2/input.txt")));
        System.out.println(String.format("The answer to the day 2 exercise 2 is: %d", new Navigator().calculatePositionWithAim("day2/input.txt")));
        System.out.println(String.format("The answer to the day 3 exercise 1 is: %d", new BinaryDiagnoser().getPower("day3/input.txt")));
        System.out.println(String.format("The answer to the day 3 exercise 2 is: %d", new BinaryDiagnoser().getLifeSupport("day3/input.txt")));
        System.out.println(String.format("The answer to the day 4 exercise 1 is: %d", new Bingo("day4/input.txt").play()));
        System.out.println(String.format("The answer to the day 4 exercise 2 is: %d", new Bingo("day4/input.txt").scoreOfLastBoardToWin()));
        System.out.println(String.format("The answer to the day 5 exercise 1 is: %d", new HydrothermalVents("day5/input.txt").getNumberOfOverlappingPointsOnHorizontalAndVerticalLines()));
        System.out.println(String.format("The answer to the day 5 exercise 2 is: %d", new HydrothermalVents("day5/input.txt").getNumberOfOverlappingPoints()));
        System.out.println(String.format("The answer to the day 6 exercise 1 is: %d", new LanternFishCalculator().calculateAmountOfFish("day6/input.txt", 80)));
        System.out.println(String.format("The answer to the day 6 exercise 2 is: %d", new LanternFishCalculator().calculateAmountOfFish("day6/input.txt", 256)));
    }

}
