package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static List<String> readFileAndExtractWords(String filePath) {
        List<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 使用正则表达式匹配单词，并将大写字母转换为小写字母
                String[] wordArray = line.toLowerCase().split("\\W+");
                for (String word : wordArray) {
                    if (!word.isEmpty()) {
                        words.add(word);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
    public static Graph loadtext(String filePath){
        Graph thisgraph = new Graph();
        Map<String,Integer> nodemap = new HashMap<String,Integer>();
        List<String> words = readFileAndExtractWords(filePath);
        // 输出结果
        for(int i = 0;i<words.size();i++){
            String word = words.get(i);
            if (!nodemap.containsKey(word)){
                Node newnode = new Node(word);
                if(i!=0){
                    int label = nodemap.get(words.get(i-1));
                    thisgraph.getNodes().get(label).addEdge(newnode);
                    //newnode.addEdge(thisgraph.getNodes().get(label));
                }
                nodemap.put(word,thisgraph.getNodenum());
                thisgraph.addnodes(newnode);
            }
            else{
                if(i!=0){
                    int lastlabel = nodemap.get(words.get(i-1));
                    int thislabel = nodemap.get(words.get(i));
                    thisgraph.getNodes().get(lastlabel).addEdge(thisgraph.getNodes().get(thislabel));
                    //newnode.addEdge(thisgraph.getNodes().get(label));
                }
            }
        }
        return thisgraph;

    }
    public static void printgraph(Graph graph){
        int num = graph.getNodenum();
        ArrayList<Node> nodes = graph.getNodes();
        for(int i = 0;i<num;i++){
            ArrayList<Edge> edges = nodes.get(i).getEdges();
            for(int j = 0;j<edges.size();j++) {
                System.out.println(nodes.get(i).getContent() + ":" + edges.get(j).getTargetnode().getContent());
                System.out.println(edges.get(j).getTargetnode().getdegree());
            }

        }
    }
    public static void main(String[] args) {
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        //System.out.printf("Hello and welcome!");
        Graph graph = loadtext("/home/hrq2021112608/IdeaProjects/softwareproject1/src/main/java/org/example/test.txt");
        printgraph(graph);
        //System.out.println(test);
        for (int i = 1; i <= 5; i++) {
            //TIP 按 <shortcut actionId="Debug"/> 开始调试代码。我们已经设置了一个 <icon src="AllIcons.Debugger.Db_set_breakpoint"/> 断点
            // 但您始终可以通过按 <shortcut actionId="ToggleLineBreakpoint"/> 添加更多断点。
            System.out.println("i = " + i);
        }
    }
}