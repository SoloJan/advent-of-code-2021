import day1.SonarSweeper;

public class AdventOfCodeAnswers {


    public static void main(String[] args) {
        System.out.println("The answer to the day 1 exercise 1 is: "+ new SonarSweeper().calculateNrOfIncreasedDepths("day1/input.txt"));
        System.out.println("The answer to the day 1 exercise 2 is: "+ new SonarSweeper().calculateNrOfIncreasedDepthsWithSlidingWindow("day1/input.txt"));
    }

}
