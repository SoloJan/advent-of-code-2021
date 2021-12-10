package day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SyntaxErrorCheckerTest {

    @Test
    void getSyntaxErrorScore() {
        SyntaxChecker syntaxChecker = new SyntaxChecker();
        assertTrue(syntaxChecker.getSyntaxErrorScore("[<>({}){}[([])<>]]").isEmpty());
        assertTrue(syntaxChecker.getSyntaxErrorScore(" (((((((((())))))))))").isEmpty());
        assertEquals(syntaxChecker.getSyntaxErrorScore("{([(<{}[<>[]}>{[]{[(<()>").get(), 1197);
        assertEquals(syntaxChecker.getSyntaxErrorScore("<{([([[(<>()){}]>(<<{{").get(), 25137);
        assertEquals(syntaxChecker.getSyntaxErrorScore("[[<[([]))<([[{}[[()]]]").get(), 3);
        assertEquals(syntaxChecker.getSyntaxErrorScore("[{[{({}]{}}([{[{{{}}([]").get(), 57);
    }

    @Test
    void getAutoCompleteScore() {
        SyntaxChecker syntaxChecker = new SyntaxChecker();
        assertTrue(syntaxChecker.getAutoCompleteScore("[<>({}){}[([])<>]]").isEmpty());
        assertTrue(syntaxChecker.getAutoCompleteScore(" (((((((((())))))))))").isEmpty());
        assertEquals(syntaxChecker.getAutoCompleteScore("[({(<(())[]>[[{[]{<()<>>").get(), 288957L);
        assertEquals(syntaxChecker.getAutoCompleteScore("[(()[<>])]({[<{<<[]>>(").get(), 5566L);
    }

    @Test
    void getTotalSyntaxErrorScore() {
        SyntaxChecker syntaxChecker = new SyntaxChecker();
        Integer totalscore = syntaxChecker.getTotalSyntaxErrorScore("day10/example.txt");
        assertEquals(26397, totalscore);
    }

    @Test
    void getMedianAutoCompleteScore() {
        SyntaxChecker syntaxChecker = new SyntaxChecker();
        Long score = syntaxChecker.getMedianAutoCompleteScore("day10/example.txt");
        assertEquals(288957L, score);
    }
}