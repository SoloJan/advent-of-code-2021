package day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnailfishNumberTest {

    @Test
    void regularNumbersOnlyPair(){
        SnailfishNumber snailfishNumber = new SnailfishNumber("[1,2]");
        assertEquals(1, snailfishNumber.xNumber.get());
        assertEquals(2, snailfishNumber.yNumber.get());
    }

    @Test
    void leftSnailfishRightRegular(){
        SnailfishNumber snailfishNumber = new SnailfishNumber("[[1,2],3]");
        assertEquals(1, snailfishNumber.getXSnailFish().get().getXNumber().get());
        assertEquals(2, snailfishNumber.getXSnailFish().get().getYNumber().get());
        assertEquals(3, snailfishNumber.getYNumber().get());
    }

    @Test
    void leftRegularRightSnailfish(){
        SnailfishNumber snailfishNumber = new SnailfishNumber("[9,[8,7]]");
        assertEquals(9, snailfishNumber.getXNumber().get());
        assertEquals(8, snailfishNumber.getYSnailFish().get().getXNumber().get());
        assertEquals(7, snailfishNumber.getYSnailFish().get().getYNumber().get());
    }

    @Test
    void bothSnailfishNumbers(){
        SnailfishNumber snailfishNumber = new SnailfishNumber("[[1,9],[8,5]]");
        assertEquals(1, snailfishNumber.getXSnailFish().get().getXNumber().get());
        assertEquals(9, snailfishNumber.getXSnailFish().get().getYNumber().get());
        assertEquals(8, snailfishNumber.getYSnailFish().get().getXNumber().get());
        assertEquals(5, snailfishNumber.getYSnailFish().get().getYNumber().get());
    }

    @Test
    void aMoreComplexNumber(){
        SnailfishNumber snailfishNumber = new SnailfishNumber("[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]");
        assertEquals(1, snailfishNumber.getXSnailFish().get().getXSnailFish().get().getXSnailFish().get().getXNumber().get());
        assertEquals(3, snailfishNumber.getXSnailFish().get().getXSnailFish().get().getXSnailFish().get().getYNumber().get());
        assertEquals(5, snailfishNumber.getXSnailFish().get().getXSnailFish().get().getYSnailFish().get().getXNumber().get());
        assertEquals(3, snailfishNumber.getXSnailFish().get().getXSnailFish().get().getYSnailFish().get().getYNumber().get());
        assertEquals(4, snailfishNumber.getYSnailFish().get().getXSnailFish().get().getXSnailFish().get().getXNumber().get());
        assertEquals(9, snailfishNumber.getYSnailFish().get().getXSnailFish().get().getXSnailFish().get().getYNumber().get());
    }

    @Test
    void magnitude(){
      assertEquals(29L, new SnailfishNumber("[9,1]").magnitude());
      assertEquals(143L, new SnailfishNumber("[[1,2],[[3,4],5]]").magnitude());
      assertEquals(3488L, new SnailfishNumber("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]").magnitude());
    }




}