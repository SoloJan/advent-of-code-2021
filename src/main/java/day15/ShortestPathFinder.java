package day15;

import util.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

import static util.FileUtil.readFilePerLine;

public class ShortestPathFinder {

    private List<List<Node>> nodes = new ArrayList<>();

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

    public long findShortestPathNaive(){
        return findShortestPathNaive(nodes);
    }

    public long findShortestPathNaiveInBigMap(){
        List<List<Node>>  bigMap = getBigMap();
        return findShortestPathNaive(bigMap);
    }

    public long findShortestPathDijkstraInBigMap(){
        return findShortestPathDijkstra(getBigMap());
    }

    public long findShortestPathDijkstra(){
        return findShortestPathDijkstra(nodes);
    }

    private Long findShortestPathDijkstra(List<List<Node>> nodes) {
        nodes.forEach(row -> row.forEach(node -> node.setNeighbours(nodes)));

        Node startNode = nodes.get(0).get(0);
        startNode.setShortestDistanceToStart(0l);
        startNode.markVisited();

        int countOfVisitedNodes = 1;

        List<Node> visitedNodesOnEdge = new ArrayList<>();
        visitedNodesOnEdge.add(startNode);

        while(countOfVisitedNodes < (nodes.size() * nodes.size())) {
            Node minNode = null;
            List<Node> cloneOfVisitedNodes  = new ArrayList<>(visitedNodesOnEdge);
            for (Node node :  cloneOfVisitedNodes) {
                List<Node> neighbours = node.getNonVisitedNeighbours();
                for (Node neighbourNode : neighbours) {
                    if(neighbourNode.isVisited()){
                        node.removeNeighbour(neighbourNode);
                        if(node.getNonVisitedNeighbours().isEmpty()){
                            visitedNodesOnEdge.remove(node);
                        }
                        continue;
                    }
                    neighbourNode.setShortestDistanceToStart(node);
                    if(minNode == null ||  minNode.getShortestDistanceToStart().get() >= neighbourNode.getShortestDistanceToStart().get()){
                        minNode = neighbourNode;
                    }
                }
            }
            minNode.markVisited();
            visitedNodesOnEdge.add(minNode);
            countOfVisitedNodes++;
        }
        return nodes.get(nodes.size()-1).get(nodes.get(0).size()-1).getShortestDistanceToStart().get();
    }

    public long findShortestPathNaive(List<List<Node>> nodes){
        int squareSize = nodes.size();
        Node lastNode = nodes.get(squareSize-1).get(squareSize-1);
        lastNode.setShortestDistanceToEnd(lastNode.getRiskLevel());
        // bottom half
        for(int diagonal = squareSize-2; diagonal>= 0; diagonal--){
           int row = squareSize-1;
           int column = diagonal;
           while(column < squareSize){
               updateShortestDistanceToEndForNode(nodes, row, column);
               row --;
               column++;
           }
        }
        // upper half
        for(int diagonal =  squareSize-2; diagonal>= 0; diagonal--){
            int row = 0;
            int column = diagonal;
            while(column >= 0){
                updateShortestDistanceToEndForNode(nodes,  row, column);
                row ++;
                column--;
            }
        }
        return nodes.get(0).get(0).getShortestDistanceToEnd().get() -  nodes.get(0).get(0).getRiskLevel();
    }

    private void updateShortestDistanceToEndForNode(List<List<Node>> nodes, int row, int column) {
        int squareSize = nodes.size();
        Node node  = nodes.get(row).get(column);
        if(node.getColumn() == squareSize -1){
            node.setShortestDistanceToEnd(node.getRiskLevel() + nodes.get(row +1).get(column).getShortestDistanceToEnd().get());
        }
        else if(node.getRow() == squareSize -1){
            node.setShortestDistanceToEnd(node.getRiskLevel() + nodes.get(row).get(column +1).getShortestDistanceToEnd().get());
        }
        else {
            node.setShortestDistanceToEnd(node.getRiskLevel() + Math.min(nodes.get(row + 1).get(column).getShortestDistanceToEnd().get(), nodes.get(row).get(column + 1).getShortestDistanceToEnd().get()));
        }
    }

    private List<List<Node>> getBigMap(){
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

    private int newRiskLevel(Node node){
        if(node.getRiskLevel() <9){
            return node.getRiskLevel() +1;
        }
        return 1;
    }

}
