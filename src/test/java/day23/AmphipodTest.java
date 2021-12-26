package day23;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AmphipodTest {

    /*
     Sketch of situation, a can move to all spots
     ...........
       .#.#.#.
       .#.#.#.
       .#.#.#.
       .#A#.#.

     */
     @Test
    void amberCanMoveToAllSpotsInHallway(){
         List<List<Optional<Amphipod>>> grid = getEmptyGrid();
         Amphipod a = new AmberAmphipod(4,4);
         addAmphipod(a, grid);
         assertTrue(a.canMove(grid));
         assertEquals(7, a.possibleMoves(grid).size());
         assertTrue(a.possibleMoves(grid).stream().allMatch(m -> m.getRow() == 0));
     }

    /*
        Sketch of situation, A can only move to spots left of B
        ......B....
          .#.#.#.
          .#.#.#.
          .#.#.#.
          .#A#.#.

    */
    @Test
    void amberCanMoveOnlyToTheLeftOfBronze(){
        List<List<Optional<Amphipod>>> grid = getEmptyGrid();
        Amphipod a = new AmberAmphipod(4,4);
        addAmphipod(a, grid);
        addAmphipod(new BronzeAmphipod(0,7), grid);
        assertTrue(a.canMove(grid));
        List<Coordinate> possibleMoves  =  a.possibleMoves(grid);
        assertEquals(4, possibleMoves.size());
        assertTrue(possibleMoves.stream().allMatch(m -> m.getRow() == 0));
        assertTrue(possibleMoves.stream().allMatch(m -> m.getColumn() < 7));
    }

        /*
        Sketch of situation, A can not move it is completly blocked by B
        ...........
          .#.#.#.
          .#.#.#.
          .#B#.#.
          .#A#.#.

    */
    @Test
    void amberIsBlockedByBronze(){
        List<List<Optional<Amphipod>>> grid = getEmptyGrid();
        Amphipod a = new AmberAmphipod(3,5);
        addAmphipod(a, grid);
        addAmphipod(new BronzeAmphipod(2,5), grid);
        assertFalse(a.canMove(grid));
        List<Coordinate> possibleMoves  =  a.possibleMoves(grid);
        assertEquals(0, possibleMoves.size());
    }


    /*
        Sketch of situation, A can not move back to its base column because B is there
        A..........
          .#.#.#.
          .#.#.#.
          .#.#.#.
          B#.#.#.

    */
    @Test
    void aCanNotGoBackToBaseColumnBecauseBIsThere(){
        List<List<Optional<Amphipod>>> grid = getEmptyGrid();
        Amphipod a = new AmberAmphipod(0,0);
        addAmphipod(a, grid);
        addAmphipod(new BronzeAmphipod(4,2), grid);
        assertFalse(a.canMove(grid));
        List<Coordinate> possibleMoves  =  a.possibleMoves(grid);
        assertEquals(0, possibleMoves.size());
    }

    /*
     Sketch of situation, A can move to the place on top of the other A
     A..........
       .#.#.#.
       .#.#.#.
       .#.#.#.
       A#.#.#.

 */
    @Test
    void aCanGoBackToBaseColumn(){
        List<List<Optional<Amphipod>>> grid = getEmptyGrid();
        Amphipod a = new AmberAmphipod(0,0);
        addAmphipod(a, grid);
        addAmphipod(new AmberAmphipod(4,2), grid);
        assertTrue(a.canMove(grid));
        List<Coordinate> possibleMoves  =  a.possibleMoves(grid);
        assertEquals(1, possibleMoves.size());
        assertEquals(2, possibleMoves.get(0).getColumn());
        assertEquals(3, possibleMoves.get(0).getRow());
    }

    /*
     Sketch of situation, A can move to the place on top of the other A
     ..........A
       .#.#.#.
       .#.#.#.
       .#.#.#.
       A#.#.#.

 */
    @Test
    void aCanComeFromTheRight(){
        List<List<Optional<Amphipod>>> grid = getEmptyGrid();
        Amphipod a = new AmberAmphipod(0,10);
        addAmphipod(a, grid);
        addAmphipod(new AmberAmphipod(4,2), grid);
        assertTrue(a.canMove(grid));
        List<Coordinate> possibleMoves  =  a.possibleMoves(grid);
        assertEquals(1, possibleMoves.size());
        assertEquals(2, possibleMoves.get(0).getColumn());
        assertEquals(3, possibleMoves.get(0).getRow());
    }


    /*
     Sketch of situation, A can not move to base column because B blocks it
     A.B........
       .#.#.#.
       .#.#.#.
       .#.#.#.
       .#.#.#.

 */
    @Test
    void aCanNotGoToBaseBecauseBBlocksIt(){
        List<List<Optional<Amphipod>>> grid = getEmptyGrid();
        Amphipod a = new AmberAmphipod(0,0);
        addAmphipod(a, grid);
        addAmphipod(new BronzeAmphipod(0,2), grid);
        assertFalse(a.canMove(grid));
        List<Coordinate> possibleMoves  =  a.possibleMoves(grid);
        assertEquals(0, possibleMoves.size());
    }


    @Test
    void move(){
        List<List<Optional<Amphipod>>> grid = getEmptyGrid();
        Amphipod c = new CopperAmphipod(4,2);
        addAmphipod(c, grid);
        int energySpent = c.move(grid, new Coordinate(0, 5));
        assertEquals(700, energySpent);
        assertEquals(c, grid.get(0).get(5).get());
        assertEquals(0, c.getRow());
        assertEquals(5, c.getColumn());
    }




    private List<List<Optional<Amphipod>>> getEmptyGrid(){
         List<List<Optional<Amphipod>>> amphipods = new ArrayList<>();
         for(int row = 0; row<5; row++){
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

}