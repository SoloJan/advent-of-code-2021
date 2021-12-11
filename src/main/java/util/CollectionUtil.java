package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class CollectionUtil {

    /**
     * @param lists a list of lists of type T
     * @param filterFunction
     * @param <T>
     * @return The function filters out all inner lists where the element at the first position matches the condition of the filter function,
     * it then applies the filter function on the second element of the inner list for all inner list which matched the first filter. It then goes on
     * matching the third element of all inner list which matched the first and second filter and then continuous until either one list is left, or it reached the final element of the inner list.
      */
    public static <T>  List<List<T>> filterUntilOneIsLeft(List<List<T>> lists, BiFunction<List, Integer, Character> filterFunction){
        return filterUntilOneIsLeft(lists, 0, filterFunction);
    }

    private static <T>  List<List<T>> filterUntilOneIsLeft(List<List<T>> lists, final int position, BiFunction<List, Integer, Character> filterFunction){
        List<List<T>> filteredList = lists.stream().filter(c -> c.get(position) == filterFunction.apply(lists, position)).collect(Collectors.toList());
        if (filteredList.size() == 1 || position == filteredList.get(0).size()) {
            return filteredList;
        }
        return filterUntilOneIsLeft(filteredList, position + 1, filterFunction);
    }

    /**
     *
     * @param lists a list of lists of type T
     * @param filterFunction
     * @param <T>
     * @return The function applies the filter function to every element of the inner lists, and returns the results of each of these calculations in a list
     * for example if the outer list represents footbal teams, and the inner list represents different seasons, and the filter function calculates the maximum goals made per season
     * than the result will be a list of the maximum goals score for every season.
     *
     */
    public static  <T> List<T> applyToEveryElement(List<List<T>> lists, BiFunction<List, Integer, T> filterFunction){
        List<T>  result  = new ArrayList<>();
        for(int i=0; i<lists.get(0).size(); i++){
            result.add(filterFunction.apply(lists, i));
        }
        return result;
    }

    public static <T> List<T> getAllNeighbours(List<List<T>> grit, int row, int column){
       List<T> neighbours =  getDirectNeighbours(grit, row, column);
       neighbours.addAll(getDiagonalNeighbours(grit, row, column));
       return neighbours;
    }

    public static <T> List<T> getDirectNeighbours(List<List<T>> grit, int row, int column){
        List<T> directNeighbours = new ArrayList<>();
        getFromGrid(grit, row-1, column).ifPresent(neighbour ->  directNeighbours.add(neighbour));
        getFromGrid(grit, row+1, column).ifPresent(neighbour ->  directNeighbours.add(neighbour));
        getFromGrid(grit, row, column+1).ifPresent(neighbour ->  directNeighbours.add(neighbour));
        getFromGrid(grit, row, column-1).ifPresent(neighbour ->  directNeighbours.add(neighbour));
        return  directNeighbours;
    }

    private static <T> List<T> getDiagonalNeighbours(List<List<T>> grit, int row, int column){
        List<T> diagonalNeighbours = new ArrayList<>();
        getFromGrid(grit, row-1, column-1).ifPresent(neighbour ->  diagonalNeighbours.add(neighbour));
        getFromGrid(grit, row-1, column+1).ifPresent(neighbour ->  diagonalNeighbours.add(neighbour));
        getFromGrid(grit, row+1, column+1).ifPresent(neighbour ->  diagonalNeighbours.add(neighbour));
        getFromGrid(grit, row+1, column-1).ifPresent(neighbour ->  diagonalNeighbours.add(neighbour));
        return  diagonalNeighbours;
    }

    private static <T> Optional<T> getFromGrid(List<List<T>> grit, int row, int column){
        try{
            return Optional.of(grit.get(row).get(column));
        }
        catch (IndexOutOfBoundsException e){
            return Optional.empty();
        }
    }




}
