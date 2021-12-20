package day20;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static util.CollectionUtil.getFromGrid;
import static util.FileUtil.readFilePerLine;
import static util.StringUtil.toCharacterList;
import static util.StringUtil.toIntegerList;

public class ImageEnhancer {

    char[] enhancementAlgorithm;
    List<List<Character>> image;
    int count = 0;


    public ImageEnhancer(String fileName){
        List<String> fileContent = readFilePerLine(fileName);
        enhancementAlgorithm = fileContent.get(0).toCharArray();
        image = fileContent.subList(2, fileContent.size()).stream().map(s -> toCharacterList(s)).collect(Collectors.toList());
    }

    public long countOfLightPixelsAfter50Enhancements(){
        return  countAfterSteps(50);
    }

    public long countOfLightPixelsAfterTwoEnhancements(){
       return  countAfterSteps(2);
    }

    private long countAfterSteps(int steps){
        for(int i = 1; i<=steps; i++){
            enhance();
        }
        return image.stream().flatMap(Collection::stream).filter(c ->  c == '#').count();
    }

   void enhance(){
       addEmptyRowAndColumnAroundImage();
       List<List<Character>> enhancedImage = new ArrayList<>();
       for(int row = 0; row<image.size(); row++){
           List<Character> pixelRow  = new ArrayList<>();
           for(int column = 0; column<image.get(row).size(); column++){
               Character newPixel  = enhancementAlgorithm[getIndexOfTranslation(row, column)];
               pixelRow.add(newPixel);
           }
           enhancedImage.add(pixelRow);
       }
       count++;
       image = enhancedImage;
   }

    private void addEmptyRowAndColumnAroundImage() {
        image.add(0, emptyRow());
        image.add(emptyRow());
        for(List<Character> row: image){
            row.add(0, getDefaultPixel());
            row.add(getDefaultPixel());
        }
    }

    List<Character> emptyRow(){
       return image.get(0).stream().map(c -> getDefaultPixel()).collect(Collectors.toList());
   }

   int getIndexOfTranslation(int row, int column){
        StringBuilder sb = new StringBuilder();
        sb.append(getBinaryValueOfPixel(row -1, column-1)).append(getBinaryValueOfPixel(row -1, column)).append(getBinaryValueOfPixel(row -1, column+1))
                .append(getBinaryValueOfPixel(row , column-1)).append(getBinaryValueOfPixel(row, column)).append(getBinaryValueOfPixel(row, column+1))
                .append(getBinaryValueOfPixel(row+1 , column-1)).append(getBinaryValueOfPixel(row+1, column)).append(getBinaryValueOfPixel(row+1, column+1));
        return Integer.valueOf(sb.toString(),2);
   }

   private Character getBinaryValueOfPixel(int row, int column){
        Character pixel = getPixel(row, column);
        return pixel == '.' ? '0' : '1';
   }

   private Character getPixel(int row, int column){
       Optional<Character> pixel = getFromGrid(image, row, column);
       if(pixel.isPresent()){
           return pixel.get();
       }
       return getDefaultPixel();
   }

    void printImage(){
        for(List<Character>  row: image){
            StringBuilder sb = new StringBuilder();
            for(Character character: row) {
                sb.append(character);
            }
            System.out.println(sb);
        }
    }


    private char getDefaultPixel(){
        if(enhancementAlgorithm[0] == '.'){
            return '.';
        }
        if(count == 0 || count% 2 == 0){
            return '.';
        }
        return '#';
    }

}
