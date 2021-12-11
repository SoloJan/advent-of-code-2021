package day11;


import java.util.List;

import static util.CollectionUtil.getAllNeighbours;

public class Octopus {

    private static final int MAX_ENERGY_LEVEL = 9;
    private int row;
    private int column;
    private int energyLevel;
    private boolean justFlashed;

    public Octopus(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.energyLevel = value;
        justFlashed = false;
    }

    public void increaseEnergy(List<List<Octopus>> octopuses ){
        energyLevel++;
        if(energyLevel> MAX_ENERGY_LEVEL && !justFlashed){
            flash(octopuses);
        }
    }

    public void resetAfterFlash(){
        energyLevel = 0;
        justFlashed = false;
    }

    public boolean justFlashed(){
        return justFlashed;
    }

    private void flash(List<List<Octopus>> octopuses) {
        justFlashed = true;
        increaseEnergyOfNeighbours(octopuses);
    }

    private void increaseEnergyOfNeighbours(List<List<Octopus>> octopusses){
        getAllNeighbours(octopusses, row, column).stream().forEach(octopus -> octopus.increaseEnergy(octopusses));
    }




}
