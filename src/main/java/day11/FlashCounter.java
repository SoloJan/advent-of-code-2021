package day11;

import util.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;
import static util.StringUtil.toIntegerList;

public class FlashCounter {

    protected List<List<Octopus>> octopuses = new ArrayList<>();

    public FlashCounter(String fileName){
        List<List<Integer>> energyLevels = readFilePerLine(fileName).stream().map(StringUtil::toIntegerList).collect(Collectors.toList());
        for(int row = 0; row< energyLevels.size(); row++){
            List<Octopus> octopusRow = new ArrayList<>();
            for(int column = 0; column< energyLevels.get(row).size(); column++){
                octopusRow.add(new Octopus(row, column, energyLevels.get(row).get(column)));
            }
            octopuses.add(octopusRow);
        }
    }

    public long countFlashes(int steps) {
        long totalFlashes = 0;
        for (int i = 1; i <= steps; i++) {
            totalFlashes += increaseEnergyAndCountFlashes();
            resetOctopusesAfterFlash();
        }
        return totalFlashes;
    }

    public long flashTogether() {
        long step = 0;
        while (true){
            step++;
            if(increaseEnergyAndCountFlashes() == 100){
                return step;
            }
            resetOctopusesAfterFlash();
        }
    }

    private void resetOctopusesAfterFlash() {
        octopuses.stream().flatMap(Collection::stream).filter(Octopus::justFlashed).forEach(Octopus::resetAfterFlash);
    }

    private long increaseEnergyAndCountFlashes(){
        octopuses.stream().flatMap(Collection::stream).forEach(octopus -> octopus.increaseEnergy(octopuses));
        return octopuses.stream().flatMap(Collection::stream).filter(Octopus::justFlashed).count();
    }
}
