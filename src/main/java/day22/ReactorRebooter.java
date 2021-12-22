package day22;

import java.util.*;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class ReactorRebooter {

    List<RebootStep> rebootSteps;

    public ReactorRebooter(String fileName){
        rebootSteps = readFilePerLine(fileName).stream().map(RebootStep::new).collect(Collectors.toList());
    }

    public long initialReboot(){
        List<RebootStep> initialRebootSteps = rebootSteps.stream().filter(this::partOfIntialReboot).collect(Collectors.toList());
        return reboot(initialRebootSteps);
    }

    public long totalReboot(){
        return reboot(rebootSteps);
    }

    private Long reboot(List<RebootStep> rebootSteps) {
        List<Area> lightedAreas = new ArrayList<>();
        for(RebootStep rebootStep: rebootSteps){
            List<Area> updatedLightAreas = new ArrayList(lightedAreas);
            Area stepArea = new Area(rebootStep);
            boolean matched = false;
            for(int i = 0; i<lightedAreas.size(); i++){
                Area area = lightedAreas.get(i);
                if(area.doOverlap(stepArea)){
                    matched = true;
                    Area overlap = area.getOverlappingArea(stepArea);
                    if(rebootStep.isOn()){
                        addRebootstepArea(lightedAreas, updatedLightAreas, stepArea.subtractArea(overlap),  i+1);
                        break;
                    }
                    else{
                        updatedLightAreas.remove(area);
                        updatedLightAreas.addAll(area.subtractArea(overlap));
                    }
                }
            }
            if(!matched && rebootStep.isOn()){
                updatedLightAreas.add(stepArea);
            }
            lightedAreas = updatedLightAreas;
        }
        return lightedAreas.stream().map(Area::getCount).reduce(0l, Long::sum);
    }

    private void addRebootstepArea(List<Area> lightedAreas, List<Area> updatedLightAreas, List<Area> subtractArea, int index) {
        if(index >= lightedAreas.size()){
            updatedLightAreas.addAll(subtractArea);
            return;
        }
        for(Area stepArea: subtractArea){
            boolean matched = false;
            for(int i = index; i<lightedAreas.size(); i++){
                Area area = lightedAreas.get(i);
                if(area.doOverlap(stepArea)){
                    matched = true;
                    Area overlap = area.getOverlappingArea(stepArea);
                    addRebootstepArea(lightedAreas, updatedLightAreas, stepArea.subtractArea(overlap),  i+1);
                    break;
                }
            }
            if(!matched){
                updatedLightAreas.add(stepArea);
            }
        }
    }

    boolean partOfIntialReboot(RebootStep rebootStep){
        return rebootStep.getLowerX() >= -50 && rebootStep.getUpperX() <= 50 && rebootStep.getLowerY() >= -50 && rebootStep.getUpperY() <= 50 && rebootStep.getLowerZ() >= -50 && rebootStep.getUpperZ() <=50;
    }


}
