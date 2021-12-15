package day15;

import day11.Octopus;
import util.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

import static util.CollectionUtil.getDirectNeighbours;
import static util.FileUtil.readFilePerLine;

public class ShortestPathFinder {


    private List<List<Node>> nodes = new ArrayList<>();;
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


    public long findShortestPath(){
        Node startNode = nodes.get(0).get(0);
        startNode.setShortestDistanceToStart(0l);
        startNode.markVisited();
        countOfVisitedNodes++;


        List<Node> neighbouringNodes = getNeighbours(startNode);
        neighbouringNodes.forEach(node -> node.setShortestDistanceToStart(startNode));
        neighbouringNodes.sort(Comparator.comparing(n -> n.getShortestDistanceToStart().get()));
        neighbouringNodes.get(0).markVisited();
        countOfVisitedNodes++;


        while(!nodes.stream().flatMap(Collection::stream).allMatch(Node::isVisited)) {
            List<Node> visitedNodes = nodes.stream().flatMap(Collection::stream).filter(Node::isVisited).collect(Collectors.toList());
            Node minNode = null;
            for (Node node : visitedNodes) {
                List<Node> neighbours = getNeighbours(node);
                for (Node neighbourNode : neighbours) {
                    if(neighbourNode.isVisited()){
                        continue;
                    }
                    neighbourNode.setShortestDistanceToStart(node);
                    if(minNode == null ||  minNode.getShortestDistanceToStart().get() > neighbourNode.getShortestDistanceToStart().get()){
                        minNode = neighbourNode;
                    }
                }
            }
            Node nodeToUpdate =  nodes.get(minNode.getRow()).get(minNode.getColumn());
            nodeToUpdate.markVisited();
            countOfVisitedNodes++;
        }

        return nodes.get(nodes.size()-1).get(nodes.get(0).size()-1).getShortestDistanceToStart().get();

    }


    

    private List<Node> getNeighbours(Node node){
        return getDirectNeighbours(nodes, node.getRow(),node.getColumn()).stream().filter(n -> !n.isVisited()).collect(Collectors.toList());
    }








}
