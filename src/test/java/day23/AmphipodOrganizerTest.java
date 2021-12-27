package day23;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AmphipodOrganizerTest {


    @Test
    void allReadyWon(){
        AmphipodOrganizer organizer = new AmphipodOrganizer();
        Long minValueToOrganize =  organizer.organize(Arrays.asList(new AmberAmphipod(4,2, 2), new BronzeAmphipod(4,4, 2), new CopperAmphipod(4, 6, 2), new DessertAmphipod(4,8,2)));
        assertEquals(0, minValueToOrganize);
        assertTrue(organizer.isSuccesfullOganised(Arrays.asList(new AmberAmphipod(4,2, 2), new BronzeAmphipod(4,4, 2), new CopperAmphipod(4, 6, 2), new DessertAmphipod(4,8,2))));
    }

    @Test
    void oneMoreMove(){
        AmphipodOrganizer organizer = new AmphipodOrganizer();
        Long minValueToOrganize =  organizer.organize(Arrays.asList(new AmberAmphipod(4,2,2), new BronzeAmphipod(4,4,2), new CopperAmphipod(4, 6, 2), new DessertAmphipod(0,6, 1)));
        assertEquals(6000, minValueToOrganize);
    }

    @Test
    void twoMoreMoves(){
        AmphipodOrganizer organizer = new AmphipodOrganizer();
        Long minValueToOrganize =  organizer.organize(Arrays.asList(new AmberAmphipod(4,2,2), new BronzeAmphipod(0,5,1), new CopperAmphipod(4, 6, 2), new DessertAmphipod(0,6, 1)));
        assertEquals(6050, minValueToOrganize);
    }

    @Test
    void impossibleToOrganise(){
        AmphipodOrganizer organizer = new AmphipodOrganizer();
        Long minValueToOrganize =  organizer.organize(Arrays.asList(new AmberAmphipod(4,2,2), new BronzeAmphipod(0,6,1), new CopperAmphipod(4, 6, 2), new DessertAmphipod(0,4, 1)));
        assertEquals(Long.MAX_VALUE, minValueToOrganize);
    }


    @Test
    void puzzle1(){
        AmphipodOrganizer organizer = new AmphipodOrganizer();
        Long minValueToOrganize =  organizer.puzzle1();
        assertEquals(13066L, minValueToOrganize);
    }

    @Test
    void puzzle2(){
        AmphipodOrganizer organizer = new AmphipodOrganizer();
        Long minValueToOrganize =  organizer.puzzle2();
        assertEquals(47328L, minValueToOrganize);
    }

    @Test
    void puzzle1ExampleInput(){
        List<Amphipod> amphipods = new ArrayList<>();
        amphipods.add(new AmberAmphipod(2,2));
        amphipods.add(new BronzeAmphipod(1,2));

        amphipods.add(new DessertAmphipod(2,4));
        amphipods.add(new CopperAmphipod(1,4));

        amphipods.add(new CopperAmphipod(2,6));
        amphipods.add(new BronzeAmphipod(1,6));

        amphipods.add(new AmberAmphipod(2,8));
        amphipods.add(new DessertAmphipod(1,8));
        AmphipodOrganizer organizer = new AmphipodOrganizer();
        Long minValueToOrganize =  organizer.organize(amphipods, 3);
        assertEquals(12521L, minValueToOrganize);
    }

    @Test
    void puzzle2ExampleInput(){
        List<Amphipod> amphipods = new ArrayList<>();
        amphipods.add(new AmberAmphipod(4,2));
        amphipods.add(new DessertAmphipod(3,2));
        amphipods.add(new DessertAmphipod(2,2));
        amphipods.add(new BronzeAmphipod(1,2));

        amphipods.add(new DessertAmphipod(4,4));
        amphipods.add(new BronzeAmphipod(3,4));
        amphipods.add(new CopperAmphipod(2,4));
        amphipods.add(new CopperAmphipod(1,4));

        amphipods.add(new CopperAmphipod(4,6));
        amphipods.add(new AmberAmphipod(3,6));
        amphipods.add(new BronzeAmphipod(2,6));
        amphipods.add(new BronzeAmphipod(1,6));

        amphipods.add(new AmberAmphipod(4,8));
        amphipods.add(new CopperAmphipod(3,8));
        amphipods.add(new AmberAmphipod(2,8));
        amphipods.add(new DessertAmphipod(1,8));
        AmphipodOrganizer organizer = new AmphipodOrganizer();
        Long minValueToOrganize =  organizer.organize(amphipods);
        assertEquals(44169L, minValueToOrganize);
    }

}