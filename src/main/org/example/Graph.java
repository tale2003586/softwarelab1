package org.example;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Node> nodes;
    public Graph(){
        nodes = new ArrayList<>();
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
    public void addnodes(Node node){
        this.nodes.add(node);
    }
    public int getNodenum(){
        return nodes.size();
    }
}
