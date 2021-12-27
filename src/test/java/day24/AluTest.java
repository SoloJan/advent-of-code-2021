package day24;

import day20.ImageEnhancer;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AluTest {

    @Test
    void multiply(){
        Alu alu = new Alu("day24/multiply.txt");
        Map<String, Long> result  = alu.followInstructions("5");
        assertEquals(-5l, result.get("x"));

    }

    @Test
    void multipleInputs(){
        Alu alu = new Alu("day24/multipleInputs.txt");
        Map<String, Long> result  = alu.followInstructions("26");
        assertEquals(1l, result.get("z"));
        Map<String, Long> result2  = alu.followInstructions("27");
        assertEquals(0l, result2.get("z"));
    }

    @Test
    void binary(){
        Alu alu = new Alu("day24/binary.txt");
        Map<String, Long> result  = alu.followInstructions("9");
        assertEquals(0l, result.get("x"));
        assertEquals(0l, result.get("y"));
        assertEquals(1l, result.get("w"));
        assertEquals(1l, result.get("z"));
    }

    @Test
    void realInstructions(){
        Alu alu = new Alu("day24/realInstructions.txt");
        Map<String, Long> result  = alu.followInstructions("13579246899999");
        assertEquals(87602628l, result.get("z"));
    }

    @Test
    void puzzle1Answer(){
        Alu alu = new Alu("day24/realInstructions.txt");
        Map<String, Long> result  = alu.followInstructions("99911993949684");
        assertEquals(0l, result.get("z"));
    }


    @Test
    void puzzle2Answer(){
        Alu alu = new Alu("day24/realInstructions.txt");
        Map<String, Long> result  = alu.followInstructions("62911941716111");
        assertEquals(0l, result.get("z"));
    }

    @Test
    void partInstructions(){
        Alu alu = new Alu("day24/firstPartOfIstructions.txt");
        Map<String, Long> variables = new HashMap<>();
        variables.put("x", 9l);
        variables.put("y", 0l);
        variables.put("z", 8l);
        variables.put("w", 0l);
        Map<String, Long> result  = alu.followInstructions( "3999999");
        assertEquals(0l, result.get("z"));
    }

}