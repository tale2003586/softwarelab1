package matrixversion;

import java.util.ArrayList;
import java.util.Arrays;

public class myGraph {
    private ArrayList<myNode> nodes;
    private int[][] edges;

    public myGraph() {
    }

    public myGraph(ArrayList<myNode> nodes, int nodesNumber) {
        this.nodes = nodes;
        this.edges = new int[nodesNumber][nodesNumber];
        for(int i = 0; i < nodesNumber; i++){
            Arrays.fill(edges[i], 0);
        }
    }

    public ArrayList<myNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<myNode> nodes) {
        this.nodes = nodes;
    }

    public int[][] getEdges() {
        return edges;
    }

    public void setEdges(int[][] edges) {
        this.edges = edges;
    }

    //添加矩阵中的边
    public void addEdges(int x, int y) {
        //添加边的同时增加出度和入度
        this.nodes.get(x).addOutDegree();
        this.nodes.get(y).addInDegree();
        this.edges[x][y]++;

    }

    //为矩阵的构建添加节点
    public void addNodes(myNode node) {
        this.nodes.add(node);
    }

    //进行字符串匹配以获取下标
    public int getLabel(String target) {
        //找到匹配的就返回下标
        for(int i = 0; i < this.nodes.size(); i++){
            if(this.nodes.get(i).getContent().equals(target)){
                return i;
            }
        }
        //没找到想匹配的就返回-1
        return -1;
    }
    
    //获取集合的大小
    public int getNodesSize() {
        return this.nodes.size();
    }
    
    //通过序号获取节点中的内容
    public String getNodeContent(int index) {
        return this.nodes.get(index).getContent();
    }
}
