package org.example;

import java.util.ArrayList;

public class Node {

    //内部属性
    private String content;
    private ArrayList<Edge> edges;

    public Node(String content){
        this.content = content;
        this.edges = new ArrayList<>();
    }
    public String getContent(){
        return this.content;
    }
    public void addEdge(Node targetnode){
        Edge newedge = new Edge(targetnode);
        edges.add(newedge);
    }
    public int getdegree(){
        return edges.size();
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
}
