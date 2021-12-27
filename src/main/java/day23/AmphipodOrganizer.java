package day23;

import java.util.*;
import java.util.stream.Collectors;

public class AmphipodOrganizer {

    HashMap<String, Long> visitedConfigs = new HashMap<>();
    long globalMinValue = Long.MAX_VALUE;


    /*
        The real puzzle input for puzzle 1
            #############
            #...........#
            ###A#C#C#D###
              #B#D#A#B#
              #########

     */
    public long puzzle1(){
        List<Amphipod> amphipods = new ArrayList<>();

        amphipods.add(new AmberAmphipod(1,2));
        amphipods.add(new BronzeAmphipod(2,2));

        amphipods.add(new CopperAmphipod(1,4));
        amphipods.add(new DessertAmphipod(2,4));

        amphipods.add(new CopperAmphipod(1,6));
        amphipods.add(new AmberAmphipod(2,6));

        amphipods.add(new DessertAmphipod(1,8));
        amphipods.add(new BronzeAmphipod(2,8));

        AmphipodOrganizer organizer = new AmphipodOrganizer();
        return organizer.organize(amphipods, 3);
    }

     /*
        The real puzzle input for puzzle 2
            #############
            #...........#
            ###A#C#C#D###
              #D#C#B#A#
              #D#B#A#C#
              #B#D#A#B#
              #########

     */

    public long puzzle2(){
        List<Amphipod> amphipods = new ArrayList<>();
        amphipods.add(new BronzeAmphipod(4,2));
        amphipods.add(new DessertAmphipod(3,2));
        amphipods.add(new DessertAmphipod(2,2));
        amphipods.add(new AmberAmphipod(1,2));

        amphipods.add(new DessertAmphipod(4,4));
        amphipods.add(new BronzeAmphipod(3,4));
        amphipods.add(new CopperAmphipod(2,4));
        amphipods.add(new CopperAmphipod(1,4));

        amphipods.add(new AmberAmphipod(4,6));
        amphipods.add(new AmberAmphipod(3,6));
        amphipods.add(new BronzeAmphipod(2,6));
        amphipods.add(new CopperAmphipod(1,6));

        amphipods.add(new BronzeAmphipod(4,8));
        amphipods.add(new CopperAmphipod(3,8));
        amphipods.add(new AmberAmphipod(2,8));
        amphipods.add(new DessertAmphipod(1,8));

        AmphipodOrganizer organizer = new AmphipodOrganizer();
        return organizer.organize(amphipods);
    }




    public long organize(List<Amphipod> amphipods, int maxRow){
        List<List<Optional<Amphipod>>>  grid = getEmptyGrid(maxRow);
        for(Amphipod amphipod: amphipods){
            addAmphipod(amphipod, grid);
        }
        return move(grid, 0l);
    }


    public long organize(List<Amphipod> amphipods){
       return  organize(amphipods, 5);
    }

    public long move(List<List<Optional<Amphipod>>>  grid, long currentEnergySpend){
        List<Amphipod>  amphipods = grid.stream().flatMap(Collection::stream).filter(a -> a.isPresent()).map(a -> a.get()).collect(Collectors.toList());
        String gridString = gridToString(amphipods);
        if(visitedConfigs.containsKey(gridString) && visitedConfigs.get(gridString) <= currentEnergySpend){
            return Long.MAX_VALUE;
        }
        else{
            visitedConfigs.put(gridString, currentEnergySpend);
        }
        currentEnergySpend = moveAmphipodsToBaseIfPossible(grid, currentEnergySpend, amphipods);
        amphipods = grid.stream().flatMap(Collection::stream).filter(a -> a.isPresent()).map(a -> a.get()).collect(Collectors.toList());
        if(currentEnergySpend >= globalMinValue){
            return Long.MAX_VALUE;
        }
        if(isSuccesfullOganised(amphipods)){
            return currentEnergySpend;
        }
        if(!amphipods.stream().anyMatch(a -> a.canMove(grid))){
            return Long.MAX_VALUE;
        }

        long minValue = Long.MAX_VALUE;
        for(Amphipod amphipod: amphipods){
            List<Coordinate> possibleMoves = amphipod.possibleMoves(grid);
            for(Coordinate move: possibleMoves){
                List<List<Optional<Amphipod>>> clonedGrid =  grid.stream().map(c -> c.stream().map(this::clone).collect(Collectors.toList())).collect(Collectors.toList());
                Optional<Amphipod> a = clonedGrid.stream().flatMap(Collection::stream).filter(am -> am.isPresent() &&  am.get().getRow() == amphipod.getRow() && am.get().getColumn() == amphipod.getColumn()).findFirst().get();
                int energySpent = a.get().move(clonedGrid, move);
                long value = move(clonedGrid, currentEnergySpend+energySpent);
                if(value < minValue){
                    minValue = value;
                }
                if(value < globalMinValue){
                    globalMinValue = minValue;
                }
            }
        }
        return minValue;
    }

    private long moveAmphipodsToBaseIfPossible(List<List<Optional<Amphipod>>> grid, long currentEnergySpend, List<Amphipod> amphipods) {
        List<Amphipod>  amphiPodsGoingHome = amphipods.stream().filter(a -> a.isInHallway() && a.canMoveToBase(grid)).collect(Collectors.toList());
        while(amphiPodsGoingHome.size()>0){
            Amphipod amphipod =    amphiPodsGoingHome.get(0);
            Coordinate move = amphipod.possibleMoves(grid).get(0);
            currentEnergySpend += amphipod.move(grid, move);
            amphiPodsGoingHome = amphipods.stream().filter(a -> a.isInHallway() && a.canMoveToBase(grid)).collect(Collectors.toList());
        }
        return currentEnergySpend;
    }

    private List<List<Optional<Amphipod>>> getEmptyGrid(int maxRow){
        List<List<Optional<Amphipod>>> amphipods = new ArrayList<>();
        for(int row = 0; row<maxRow; row++){
            List<Optional<Amphipod>> amphipodsRow = new ArrayList<>();
            for(int column = 0; column<11; column++){
                amphipodsRow.add(Optional.empty());
            }
            amphipods.add(amphipodsRow);
        }
        return amphipods;
    }

    public void addAmphipod(Amphipod amphipod, List<List<Optional<Amphipod>>> grid) {
        List<Optional<Amphipod>> gridRow = grid.get(amphipod.getRow());
        gridRow.set(amphipod.getColumn(), Optional.of(amphipod));
        grid.set(amphipod.getRow(), gridRow);
    }

    boolean isSuccesfullOganised(List<Amphipod>  amphipods){
        boolean amberIsInSpot = amphipods.stream().filter(a -> a.getType() == "amber").allMatch(amphipod -> amphipod.getColumn() == 2);
        boolean bronzeIsInSpot = amphipods.stream().filter(a -> a.getType() == "bronze").allMatch(amphipod -> amphipod.getColumn() == 4);
        boolean copperIsInSpot = amphipods.stream().filter(a -> a.getType() == "copper").allMatch(amphipod -> amphipod.getColumn() == 6);
        boolean dessertIsInSpot = amphipods.stream().filter(a -> a.getType() == "dessert").allMatch(amphipod -> amphipod.getColumn() == 8);
        return  amberIsInSpot && bronzeIsInSpot && copperIsInSpot && dessertIsInSpot;
    }

    private Optional<Amphipod> clone(Optional<Amphipod> amphipod){
        if(amphipod.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(amphipod.get().cloneAmphipod());
    }

    private String gridToString(List<Amphipod> amphipods){
        StringBuilder sb  = new StringBuilder();
        amphipods.forEach(a ->  sb.append(a.toString()));
        return sb.toString();
    }


}
