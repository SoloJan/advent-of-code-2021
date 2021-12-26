package day23;

import java.util.*;
import java.util.stream.Collectors;

public class AmphipodOrganizer {

    private List<List<Amphipod>> visitedConfigs = new ArrayList<>();
    long globalMinValue = Long.MAX_VALUE;

    private Set<Long> possibleSolutions = new HashSet<>();


    public long organize(List<Amphipod> amphipods, int maxRow){
        List<List<Optional<Amphipod>>>  grid = getEmptyGrid(maxRow);
        for(Amphipod amphipod: amphipods){
            addAmphipod(amphipod, grid);
        }
        long result =  move(grid, 0l, new ArrayList<>());

        System.out.println(possibleSolutions);
        return result;
    }


    public long organize(List<Amphipod> amphipods){
       return  organize(amphipods, 5);
    }

    public long move(List<List<Optional<Amphipod>>>  grid, long currentEnergySpend, List<Move> moves){
        List<Amphipod>  amphipods = grid.stream().flatMap(Collection::stream).filter(a -> a.isPresent()).map(a -> a.get()).collect(Collectors.toList());
        List<Amphipod> finalAmphipods = amphipods;
       // if( visitedConfigs.stream().anyMatch(c -> c.containsAll(finalAmphipods))){
         //   return Long.MAX_VALUE;
       // }

        List<Amphipod>  amphiPodsGoingHome = amphipods.stream().filter(a -> a.isInHallway() && a.canMoveToBase(grid)).collect(Collectors.toList());
        while(amphiPodsGoingHome.size()>0){
            Amphipod amphipod =    amphiPodsGoingHome.get(0);
            Coordinate move = amphipod.possibleMoves(grid).get(0);
            moves.add(new Move(amphipod.getRow(), amphipod.getColumn(), move.getRow(), move.getColumn(), amphipod.getType()));
            currentEnergySpend += amphipod.move(grid, move);
            amphiPodsGoingHome = amphipods.stream().filter(a -> a.isInHallway() && a.canMoveToBase(grid)).collect(Collectors.toList());
        }
        amphipods = grid.stream().flatMap(Collection::stream).filter(a -> a.isPresent()).map(a -> a.get()).collect(Collectors.toList());
        if(currentEnergySpend >= globalMinValue){
            visitedConfigs.add(amphipods);
           // moves = new ArrayList<>();
            return Long.MAX_VALUE;
        }
        if(isSuccesfullOganised(amphipods)){
            visitedConfigs.add(amphipods);
            possibleSolutions.add(currentEnergySpend);
            System.out.println("Winning with energy of " +currentEnergySpend );
            for(Move move: moves){
                System.out.println(move);
            }
            return currentEnergySpend;
        }

        if(!amphipods.stream().anyMatch(a -> a.canMove(grid))){
            visitedConfigs.add(amphipods);
            return Long.MAX_VALUE;
        }


        long minValue = Long.MAX_VALUE;
        for(Amphipod amphipod: amphipods){
            List<Coordinate> possibleMoves = amphipod.possibleMoves(grid);
            for(Coordinate move: possibleMoves){
                List<List<Optional<Amphipod>>> clonedGrid =  grid.stream().map(c -> c.stream().map(this::clone).collect(Collectors.toList())).collect(Collectors.toList());
                Optional<Amphipod> a = clonedGrid.stream().flatMap(Collection::stream).filter(am -> am.isPresent() &&  am.get().getRow() == amphipod.getRow() && am.get().getColumn() == amphipod.getColumn()).findFirst().get();

                List<Move> clonedMoves = moves.stream().map(m -> new Move(m.getFromX(), m.getFromY(), m.getToX(), m.getToY(), m.getType())).collect(Collectors.toList());
                clonedMoves.add(new Move(a.get().getRow(), a.get().getColumn(), move.getRow(), move.getColumn(), a.get().getType()));

                int energySpent = a.get().move(clonedGrid, move);

                long value = move(clonedGrid, currentEnergySpend+energySpent, clonedMoves);
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


}
