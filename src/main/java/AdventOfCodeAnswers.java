import day10.SyntaxChecker;
import day11.FlashCounter;
import day12.PathFinder;
import day13.PaperFolder;
import day14.PolymereTemplate;
import day15.ShortestPathFinder;
import day16.Packet;
import day17.TrajectoryCalculator;
import day18.SnailfishNumberCalculator;
import day19.BeaconScannerMap;
import day20.ImageEnhancer;
import day21.DeterministicDice;
import day21.DiracDice;
import day5.HydrothermalVents;
import day1.SonarSweeper;
import day2.Navigator;
import day3.BinaryDiagnoser;
import day4.Bingo;
import day6.LanternFishCalculator;
import day7.PositionAligner;
import day8.SegmentDisplayRepairer;
import day9.HeatMap;

import static util.FileUtil.readFilePerLine;

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
        System.out.println(String.format("The answer to the day 7 exercise 1 is: %d", new PositionAligner().findShortestDistanceToCommonPoint("day7/input.txt") ));
        System.out.println(String.format("The answer to the day 7 exercise 2 is: %d", new PositionAligner().findFuelConsumption("day7/input.txt") ));
        System.out.println(String.format("The answer to the day 8 exercise 1 is: %d", new SegmentDisplayRepairer("day8/input.txt").getUniqueDigitsInOutput()));
        System.out.println(String.format("The answer to the day 8 exercise 2 is: %d", new SegmentDisplayRepairer("day8/input.txt").getSumOfOutputs()));
        System.out.println(String.format("The answer to the day 9 exercise 1 is: %d", new HeatMap("day9/input.txt").findSumOfRiskLevels()));
        System.out.println(String.format("The answer to the day 9 exercise 2 is: %d", new HeatMap("day9/input.txt").findProductOfThreeBiggestBasinSizes()));
        System.out.println(String.format("The answer to the day 10 exercise 1 is: %d",new SyntaxChecker().getTotalSyntaxErrorScore("day10/input.txt")));
        System.out.println(String.format("The answer to the day 10 exercise 2 is: %d",new SyntaxChecker().getMedianAutoCompleteScore("day10/input.txt")));
        System.out.println(String.format("The answer to the day 11 exercise 1 is: %d", new FlashCounter("day11/input.txt").countFlashes(100)));
        System.out.println(String.format("The answer to the day 11 exercise 2 is: %d", new FlashCounter("day11/input.txt").flashTogether()));
        System.out.println(String.format("The answer to the day 12 exercise 1 is: %d", new PathFinder("day12/input.txt").findNrOfPaths()));
      //  System.out.println(String.format("The answer to the day 12 exercise 2 is: %d", new PathFinder("day12/input.txt").findNrOfPathsRevisitingSmallCaves()));
        System.out.println(String.format("The answer to the day 13 exercise 1 is: %d", new PaperFolder("day13/input.txt").nrOfDotsAfterFirstInstruction()));
        System.out.println("The answer to the day 13 exercise 2 is: ");
        new PaperFolder("day13/input.txt").keepOnFolding();
        System.out.println(String.format("The answer to the day 14 exercise 1 is: %d", new PolymereTemplate("day14/input.txt").mostCommonCharacterMinusLeastCommonCharacterAfterSteps(10)));
        System.out.println(String.format("The answer to the day 14 exercise 1 is: %d", new PolymereTemplate("day14/input.txt").mostCommonCharacterMinusLeastCommonCharacterAfterSteps(40)));
        System.out.println(String.format("The answer to the day 15 exercise 1 is: %d", new ShortestPathFinder("day15/input.txt").findShortestPath()));
       // System.out.println(String.format("The answer to the day 15 exercise 1 is: %d", new ShortestPathFinder("day15/input.txt").findShortestPathDijkstra()));
        System.out.println(String.format("The answer to the day 16 exercise 1 is: %d", new Packet(readFilePerLine("day16/input.txt").get(0)).getSumOfVersionNumbers()));
        System.out.println(String.format("The answer to the day 16 exercise 2 is: %d", new Packet(readFilePerLine("day16/input.txt").get(0)).getValue().longValue()));
        System.out.println(String.format("The answer to the day 17 exercise 1 is: %d", new TrajectoryCalculator().findMaxHeight(85, 145, -163, -108)));
        System.out.println(String.format("The answer to the day 17 exercise 2 is: %d", new TrajectoryCalculator().findCountOfAllowedVelocities(85, 145, -163, -108)));
        System.out.println(String.format("The answer to the day 18 exercise 1 is: %d", new SnailfishNumberCalculator().magnitudeOfFile("day18/input.txt")));
        System.out.println(String.format("The answer to the day 18 exercise 2 is: %d", new SnailfishNumberCalculator().maxMagnitude("day18/input.txt")));
        System.out.println(String.format("The answer to the day 19 exercise 1 is: %d", new BeaconScannerMap("day19/input.txt").getCountOfUniqueBeacons()));
        System.out.println(String.format("The answer to the day 19 exercise 2 is: %d", new BeaconScannerMap("day19/input.txt").getBiggestManhattanDistance()));
        System.out.println(String.format("The answer to the day 20 exercise 1 is: %d", new ImageEnhancer("day20/input.txt").countOfLightPixelsAfterTwoEnhancements()));
        System.out.println(String.format("The answer to the day 20 exercise 1 is: %d", new ImageEnhancer("day20/input.txt").countOfLightPixelsAfter50Enhancements()));
        System.out.println(String.format("The answer to the day 21 exercise 1 is: %d", new DeterministicDice().play(5,6)));
        System.out.println(String.format("The answer to the day 21 exercise 1 is: %d", new DiracDice().play(5,6)));
    }

}
