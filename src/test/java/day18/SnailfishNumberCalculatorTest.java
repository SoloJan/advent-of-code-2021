package day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnailfishNumberCalculatorTest {

    @Test
    void addAllFromFrile(){
        SnailfishNumberCalculator calculator = new SnailfishNumberCalculator();
        assertEquals("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", calculator.addAllInFile("day18/example.txt"));
        assertEquals("[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]", calculator.addAllInFile("day18/example2.txt"));
    }

    @Test
    void addTest(){
        SnailfishNumberCalculator calculator = new SnailfishNumberCalculator();
        assertEquals("[[1,2],[[3,4],5]]", calculator.add("[1,2]", "[[3,4],5]" ));
    }

    @Test
    void addAndReduce(){
        SnailfishNumberCalculator calculator = new SnailfishNumberCalculator();
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", calculator.add("[[[[4,3],4],4],[7,[[8,4],9]]]", "[1,1]" ));
        assertEquals("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]", calculator.add("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]", "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]" ));
        assertEquals("[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]" , calculator.add( "[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]", "[2,9]"));
    }


    @Test
    void splitTest(){
        SnailfishNumberCalculator calculator = new SnailfishNumberCalculator();
        assertEquals("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]", calculator.split("[[[[0,7],4],[15,[0,13]]],[1,1]]"));
        assertEquals("[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]", calculator.split("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]"));
    }

    @Test
    void maxMagnitude(){
        SnailfishNumberCalculator calculator = new SnailfishNumberCalculator();
        assertEquals(3993, calculator.maxMagnitude("day18/example2.txt"));
    }

    @Test
    void magnitude(){
        SnailfishNumberCalculator calculator = new SnailfishNumberCalculator();
        assertEquals(4140l, calculator.magnitudeOfFile("day18/example2.txt"));

    }


    @Test
    void explodeTest(){
        SnailfishNumberCalculator calculator = new SnailfishNumberCalculator();
        assertEquals("[[[[12,12],[6,14]],[[15,0],[25,0]]],[3,9]]", calculator.explode("[[[[12,12],[6,14]],[[15,0],[17,[8,1]]]],[2,9]]"));
        assertEquals("[[[[0,9],2],3],4]", calculator.explode("[[[[[9,8],1],2],3],4]"));
        assertEquals("[7,[6,[5,[7,0]]]]", calculator.explode("[7,[6,[5,[4,[3,2]]]]]"));
        assertEquals("[[6,[5,[7,0]]],3]", calculator.explode("[[6,[5,[4,[3,2]]]],1]"));
        assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]", calculator.explode("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"));
    }


}