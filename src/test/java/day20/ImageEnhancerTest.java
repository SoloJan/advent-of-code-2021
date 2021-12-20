package day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageEnhancerTest {

    @Test
    void getIndex(){
        ImageEnhancer imageEnhancer = new ImageEnhancer("day20/example.txt");
        assertEquals(34, imageEnhancer.getIndexOfTranslation(2,2));
    }

    @Test
    void getCountOfLightPixels(){
        ImageEnhancer imageEnhancer = new ImageEnhancer("day20/example.txt");
        assertEquals(35, imageEnhancer.countOfLightPixelsAfterTwoEnhancements());
    }

    @Test
    void getCountOfLightPixelsAfter50Steps(){
        ImageEnhancer imageEnhancer = new ImageEnhancer("day20/example.txt");
        assertEquals(3351, imageEnhancer.countOfLightPixelsAfter50Enhancements());
    }


}