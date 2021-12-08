package day8;

import java.util.List;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class SegmentDisplayRepairer {

    List<Display> displays;

    public SegmentDisplayRepairer(String fileName){
       displays =  readFilePerLine(fileName).stream().map(  s -> new Display(s.split("\\|"))).collect(Collectors.toList());
    }

    public long getUniqueDigitsInOutput(){
        return displays.stream().flatMap(display -> display.getOutputs().stream()).filter(Digit::isUniquelyDefinedBySizeOfSegments).count();
    }

    public int getSumOfOutputs(){
        return displays.stream().map(d -> d.getOutput()).reduce(0, Integer::sum);
    }

}
