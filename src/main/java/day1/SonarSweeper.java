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
}
