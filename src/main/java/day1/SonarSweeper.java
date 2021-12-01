package day1;

import java.util.List;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class SonarSweeper {

    public int calculateNrOfIncreasedDepths(String fileName){
        List<String> fileContent =  readFilePerLine(fileName);
        List<Integer> depths = fileContent.stream().map(Integer::valueOf).collect(Collectors.toList());
        int increasedDepths = 0;
        for(int i = 1; i<depths.size(); i++){
            if(depths.get(i) > depths.get(i-1)){
                increasedDepths++;
            }
        }
        return increasedDepths;
    }

    public int calculateNrOfIncreasedDepthsWithSlidingWindow(String fileName){
        List<String> fileContent =  readFilePerLine(fileName);
        List<Integer> depths = fileContent.stream().map(Integer::valueOf).collect(Collectors.toList());
        int increasedDepths = 0;
        for(int i = 1; i<depths.size()-2; i++){
            if(getSumOfWindow(i, depths) > getSumOfWindow(i-1, depths)){
                increasedDepths++;
            }
        }
        return increasedDepths;
    }


    private int getSumOfWindow(int step, List<Integer> depths){
        int sum = depths.get(step);
        sum = sum + depths.get(step+1);
        sum = sum+depths.get(step+2);
        return sum;
    }

}
