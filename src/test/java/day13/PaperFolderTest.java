package day13;

import day11.FlashCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaperFolderTest {

    @Test
    void nrOfDotsAfterFirstInstruction() {
        PaperFolder folder = new PaperFolder("day13/example.txt");
        assertEquals(17, folder.nrOfDotsAfterFirstInstruction());
    }

    @Test
    void keepOnFolding() {
        PaperFolder folder = new PaperFolder("day13/example.txt");
        folder.keepOnFolding();
        assertEquals(16, folder.getNrOfDots());
    }



    @Test
    void foldUp() {
        PaperFolder folder = new PaperFolder("day13/example.txt");
        folder.foldUp(7);
        assertEquals(17, folder.getNrOfDots());
    }

    @Test
    void foldUpAndLeft() {
        PaperFolder folder = new PaperFolder("day13/example.txt");
        folder.foldUp(7);
        folder.foldLeft(5);
        assertEquals(16, folder.getNrOfDots());
    }


}