package day13;

import Common.Coordinate;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class PaperFolder {

    List<Coordinate> coordinates = new ArrayList<>();
    List<FoldInstruction> foldInstructions = new ArrayList<>();

    public PaperFolder(String fileName){
        List<String> fileContent = readFilePerLine(fileName);
        boolean isFirstSection = true;
        for(String line:  fileContent){
            if(line.isEmpty()){
                isFirstSection = false;
                continue;
            }
            if(isFirstSection){
                coordinates.add(new Coordinate(line));
            }
            else{
                foldInstructions.add(new FoldInstruction(line));
            }
        }
    }


    public void keepOnFolding(){
        foldInstructions.forEach(f -> fold(f));
        printCoordinates();
    }

    public int nrOfDotsAfterFirstInstruction(){
        fold(foldInstructions.get(0));
        return getNrOfDots();
    }

    public int getNrOfDots(){
        return coordinates.size();
    }

    private void printCoordinates(){
        int maxX = coordinates.stream().map(Coordinate::getX).reduce(0, Integer::max);
        int maxY = coordinates.stream().map(Coordinate::getY).reduce(0, Integer::max);
        for(int y=0; y<= maxY; y++){
            StringBuilder line= new StringBuilder();
            for(int x=0; x<=maxX; x++){
                int finalX = x;
                int finalY = y;
                Optional<Coordinate> coordinate = coordinates.stream().filter(c -> c.getX() == finalX && c.getY() == finalY).findAny();
                if(coordinate.isPresent()){
                    line.append("#");
                }
                else{
                    line.append(" ");
                }
            }
            System.out.println(line);
        }
    }


    public void fold(FoldInstruction foldInstruction){
        if(foldInstruction.getDirection().equals("y")){
            foldUp(foldInstruction.getValue());
        }
        else{
            foldLeft(foldInstruction.getValue());
        }
    }

    public void foldUp(int value){
        coordinates = coordinates.stream().filter(coordinate -> coordinate.getY() != value).collect(Collectors.toList());
        for(Coordinate coordinate: coordinates){
            if(coordinate.getY() > value){
                int difference = coordinate.getY() - value;
                coordinate.setY(value - difference);
            }
        }
        coordinates = coordinates.stream().distinct().collect(Collectors.toList());
    }

    public void foldLeft(int value){
        coordinates = coordinates.stream().filter(coordinate -> coordinate.getX() != value).collect(Collectors.toList());
        for(Coordinate coordinate: coordinates){
            if(coordinate.getX() > value){
                int difference = coordinate.getX() - value;
                coordinate.setX(value - difference);
            }
        }
        coordinates = coordinates.stream().distinct().collect(Collectors.toList());
    }



}
