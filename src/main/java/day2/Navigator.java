package day2;

import java.util.List;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class Navigator {

    public int calculatePosition(String fileName){
        List<String> fileContent =  readFilePerLine(fileName);
        int horizontalPos = 0;
        int depth = 0;
        for (String directionLine: fileContent) {
            String[] direction = directionLine.split(" ");
            switch (direction[0]){
                case "forward":
                    horizontalPos = horizontalPos + Integer.valueOf(direction[1]);
                    break;
                case "down":
                    depth = depth + Integer.valueOf(direction[1]);
                    break;
                case "up":
                    depth = depth - Integer.valueOf(direction[1]);
                    break;
            }
        }
        return horizontalPos * depth;
    }

    public int calculatePositionWithAim(String fileName){
        List<String> fileContent =  readFilePerLine(fileName);
        int horizontalPos = 0;
        int depth = 0;
        int aim = 0;
        for (String directionLine: fileContent) {
            String[] direction = directionLine.split(" ");
            switch (direction[0]){
                case "forward":
                    horizontalPos = horizontalPos + Integer.valueOf(direction[1]);
                    depth = depth + (aim * Integer.valueOf(direction[1]));
                    break;
                case "down":
                    aim = aim + Integer.valueOf(direction[1]);
                    break;
                case "up":
                    aim = aim - Integer.valueOf(direction[1]);
                    break;
            }
        }
        return horizontalPos * depth;
    }

}
