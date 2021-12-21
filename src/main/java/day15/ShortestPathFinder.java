package day15;

import util.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

import static util.CollectionUtil.getDirectNeighbours;
import static util.CollectionUtil.getFromGrid;
import static util.FileUtil.readFilePerLine;

public class ShortestPathFinder {


    private List<List<Node>> nodes = new ArrayList<>();
    private List<List<Node>>  bigMap = new ArrayList<>();
    private int countOfVisitedNodes = 0;

    public ShortestPathFinder(String fileName){
        List<List<Integer>>  riskLevels = readFilePerLine(fileName).stream().map(StringUtil::toIntegerList).collect(Collectors.toList());
        for(int row = 0; row< riskLevels.size(); row++){
            List<Node> nodeRow = new ArrayList<>();
            for(int column = 0; column< riskLevels.get(row).size(); column++){
                nodeRow.add(new Node(row, column, riskLevels.get(row).get(column)));
            }
            nodes.add(nodeRow);
        }

    }


    public long findShortestPathInBigMap(){
        bigMap = getBigMap();
        int squareSize = bigMap.size();
        Node lastNode = bigMap.get(squareSize-1).get(squareSize-1);
        lastNode.setShortestDistanceToEnd(lastNode.getRiskLevel());
        for(int i = squareSize-2;   i>= 0; i--){
            for(int j = squareSize-1; j>=i; j--){
                Node rowNode =   bigMap.get(i).get(j);
                if(rowNode.getColumn() == squareSize-1){
                    rowNode.setShortestDistanceToEnd( rowNode.getRiskLevel() + bigMap.get(i+1).get(j).getShortestDistanceToEnd().get());
                }
                else {
                    rowNode.setShortestDistanceToEnd(rowNode.getRiskLevel() + getLowest(bigMap.get(i + 1).get(j).getShortestDistanceToEnd().get(), bigMap.get(i).get(j + 1).getShortestDistanceToEnd().get()));
                }
                Node columnNode =   bigMap.get(j).get(i);
                if(columnNode.getRow() == squareSize-1){
                    columnNode.setShortestDistanceToEnd(columnNode.getRiskLevel() + bigMap.get(j).get(i+1).getShortestDistanceToEnd().get());
                }
                else {
                    columnNode.setShortestDistanceToEnd(columnNode.getRiskLevel() + getLowest(bigMap.get(j).get(i+1).getShortestDistanceToEnd().get(), bigMap.get(j+1).get(i).getShortestDistanceToEnd().get()));
                }
            }
        }
        return bigMap.get(0).get(0).getShortestDistanceToEnd().get() - bigMap.get(0).get(0).getRiskLevel();
    }



    public long findShortestPathDijkstra(){
        bigMap = getBigMap();
        Node startNode = bigMap.get(0).get(0);
        startNode.setShortestDistanceToStart(0l);
        startNode.markVisited();
        countOfVisitedNodes++;


        List<Node> neighbouringNodes = getNeighbours(startNode);
        neighbouringNodes.forEach(node -> node.setShortestDistanceToStart(startNode));
        neighbouringNodes.sort(Comparator.comparing(n -> n.getShortestDistanceToStart().get()));
        neighbouringNodes.get(0).markVisited();
        countOfVisitedNodes++;

        List<Node> visitedNodes = new ArrayList<>();
        visitedNodes.add(startNode);
        visitedNodes.add(neighbouringNodes.get(0));



        while(countOfVisitedNodes < (bigMap.size() * bigMap.size())) {
            Node minNode = null;
            for (Node node :  visitedNodes) {
                List<Node> neighbours = getNeighbours(node);
                for (Node neighbourNode : neighbours) {
                    if(neighbourNode.isVisited()){
                        continue;
                    }
                    neighbourNode.setShortestDistanceToStart(node);
                    if(minNode == null ||  minNode.getShortestDistanceToStart().get() >= neighbourNode.getShortestDistanceToStart().get()){
                        minNode = neighbourNode;
                    }
                }
            }
          //  Node nodeToUpdate =  nodes.get(minNode.getRow()).get(minNode.getColumn());

                minNode.markVisited();
                visitedNodes.add(minNode);
                countOfVisitedNodes++;

        }

        return bigMap.get(bigMap.size()-1).get(bigMap.get(0).size()-1).getShortestDistanceToStart().get();

    }



    public long findShortestPath(){
        int squareSize = nodes.size();
        Node lastNode = nodes.get(squareSize-1).get(squareSize-1);
        lastNode.setShortestDistanceToEnd(lastNode.getRiskLevel());
        for(int i = squareSize-2;   i>= 0; i--){
            for(int j = squareSize-1; j>=i; j--){
              Node rowNode =   nodes.get(i).get(j);
              if(rowNode.getColumn() == squareSize-1){
                  rowNode.setShortestDistanceToEnd( rowNode.getRiskLevel() + nodes.get(i+1).get(j).getShortestDistanceToEnd().get());
              }
              else {
                  rowNode.setShortestDistanceToEnd(rowNode.getRiskLevel() + getLowest(nodes.get(i + 1).get(j).getShortestDistanceToEnd().get(), nodes.get(i).get(j + 1).getShortestDistanceToEnd().get()));
              }
                Node columnNode =   nodes.get(j).get(i);
                if(columnNode.getRow() == squareSize-1){
                    columnNode.setShortestDistanceToEnd(columnNode.getRiskLevel() + nodes.get(j).get(i+1).getShortestDistanceToEnd().get());
                }
                else {
                    columnNode.setShortestDistanceToEnd(columnNode.getRiskLevel() + getLowest(nodes.get(j).get(i+1).getShortestDistanceToEnd().get(), nodes.get(j+1).get(i).getShortestDistanceToEnd().get()));
                }


            }
       }
        return nodes.get(0).get(0).getShortestDistanceToEnd().get() - nodes.get(0).get(0).getRiskLevel();
    }




    private long getLowest(long x, long x2) {
        return x <= x2 ? x : x2;
    }


    public List<List<Node>> getBigMap(){
        List<List<Node>> bigMap = new ArrayList<>();
        int originalSize = nodes.size();
        for(int row = 0; row < originalSize ; row++){
            List<Node> nodeRow =  nodes.get(row);
            for(int column = originalSize; column < 5 * originalSize; column++){
                Node node = new Node(row, column, newRiskLevel(nodeRow.get(column-originalSize)));
                nodeRow.add(node);
            }
            bigMap.add(nodeRow);
        }
        for(int row = originalSize; row < 5 * originalSize;  row++){
            List<Node> nodeRow =  new ArrayList<>();
            for(int column = 0; column < 5 * originalSize; column++){
                nodeRow.add(new Node(row, column, newRiskLevel(bigMap.get(row-originalSize).get(column))));
            }
            bigMap.add(nodeRow);
        }
        return bigMap;
    }


    private Node mapToNodeInRow(Node node, int size){
        return new Node(node.getRow(),  node.getColumn() + size, newRiskLevel(node));
    }

    private Node nodeMapToNewRow(Node node, int size){
        return new Node(node.getRow() + size,  node.getColumn(), newRiskLevel(node));
    }

    private int newRiskLevel(Node node){
        if(node.getRiskLevel() <9){
            return node.getRiskLevel() +1;
        }
        return 1;
    }

    private List<Node> getNeighbours(Node node){
        return getDirectNeighbours(bigMap, node.getRow(),node.getColumn()).stream().filter(n -> !n.isVisited()).collect(Collectors.toList());
    }








}
