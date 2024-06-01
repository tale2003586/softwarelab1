package org.example;

public class Edge {
    private int weight;
    private Node targetnode;
    public Edge(Node targetnode){
        this.targetnode = targetnode;
        this.weight = 1;
    }

    public void addWeight() {
        this.weight ++;
    }
    public Node getTargetnode(){
        return this.targetnode;
    }
    public int getWeight(){
        return this.weight;
    }
}
