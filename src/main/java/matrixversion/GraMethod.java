package matrixversion;

import java.io.*;
import java.util.*;

public class GraMethod {
    public myGraph nodeGraph;
//    public static void main(String[] args) {
//        textToGraph("test.txt");
//        printGraph();
//        //生成有向图
//        graphGenerator();
//        System.out.println(queryBridgeWords("to", "explore"));
//        String TEST = "Seek to explore new and exciting synergies";
//        System.out.println(generateNewText(TEST));
//        //测试dijkstra算法
//        dijkstra(0);
//        //获取随机节点
//        Node startNode = getRandomNode(nodeGraph.getNodes());
//        System.out.println("Start node: " + startNode.getContent());
//        randomWalk(startNode);
//    }
    void GraMethod(){
        ;
    }
    //对单词进行过滤统计
    public List<String> readFileAndExtractWords(String fileName) {
        List<String> words = new ArrayList<>();
        //这种写法已经实现了与文件的连接自动关闭的功能
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while((line = br.readLine()) != null) {
               //将句子中的单词先全部转化为小写,然后将单词进行切割,将切割的单词存入数组当中
               String[] wordsArray = line.toLowerCase().split("\\W+");
                for (String s : wordsArray) {
                    if(!s.isEmpty()) {
                        words.add(s);
                    }
                }
            }
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        for (String word : words) {
            System.out.println(word);
        }
        return words;
    }

    //用来构建图的二维矩阵
    public myGraph textToGraph(String filePath) {
        //用一个map来存储每一个单词以及图的大小
        Map<String, Integer> nodesMap = new HashMap<>();
        List<String> words = readFileAndExtractWords(filePath);
        int countt = 0;
        for(int i = 0;i<words.size();i++) {

            String word = words.get(i);
            if (!nodesMap.containsKey(word)){
                nodesMap.put(word, countt);
                countt+=1;
            }
        }
        nodeGraph = new myGraph(new ArrayList<>(), nodesMap.size());
        nodesMap.clear();
        //for(int i = 0;i< nodesMap.size();i++)
            //nodeGraph.addNodes(new myNode(nodesMap.get()));
        //System.out.println(words.size());
        //进行map填充
        for(int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if(i == 0) {
                nodeGraph.addNodes(new myNode(word));
                //首先对第一个元素进行添加
                nodesMap.put(word, nodeGraph.getNodesSize() - 1);
            } else if (!nodesMap.containsKey(word)) { //首先要对元素是否重复出现进行判断
                //如果元素之前没有出现过的话
                nodeGraph.addNodes(new myNode(word));
                nodeGraph.addEdges(nodesMap.get(words.get(i - 1)), nodeGraph.getNodesSize() -1);
                nodesMap.put(word, nodeGraph.getNodesSize() - 1);
            } else {
                //如果该元素之前出现过,那么就不往图中添加节点,只需要添加一条指向已存在节点的边即可
                nodeGraph.addEdges(nodeGraph.getLabel(words.get(i-1)), nodeGraph.getLabel(words.get(i)));
                //注意这一步是不需要改变word的value值的,因为这个值代表了其具体的位置
                //nodesMap.put(word, nodeGraph.getNodesSize() - 1);
            }
        }
        System.out.println(nodesMap.size());
        return nodeGraph;
    }

    //打印有向图
    public void printGraph() {
        int[][] edges = nodeGraph.getEdges();
        for(int i = 0; i < nodeGraph.getNodesSize(); i++) {
            for(int j = 0; j < nodeGraph.getNodesSize(); j++) {
                if(edges[i][j] >= 1) {
                    System.out.println(nodeGraph.getNodeContent(i) + ":" + nodeGraph.getNodeContent(j)+"->"+edges[i][j]);
                }
            }
        }
    }
    public List<String> parseStringToList(String str) {
        List<String> list = new ArrayList<>();
        System.out.println(str);
        if (str.length()==2)return list;
        // 移除方括号
        str = str.substring(1, str.length() - 1);
        // 根据逗号分隔字符串
        String[] elements = str.split(", ");

        // 创建一个新的 ArrayList
        //List<String> list = new ArrayList<>();

        // 添加元素到列表
        for (String element : elements) {
            list.add(element);
        }

        return list;
    }
    //用来查询桥接词
    public String queryBridgeWords(String word1, String word2) {
        List<String> bridgeWords = new ArrayList<>();
        //用SB来存储语句并可以进行返回
        StringBuilder temp = new StringBuilder();
        int label1 = nodeGraph.getLabel(word1);
        int label2 = nodeGraph.getLabel(word2);
        //如果有没有查询到的词,则返回空字符串
        if(label1 == -1 || label2 == -1) {
            temp.append("No ").append(word1).append(" or ").append(word1).append(" in the graph!");
            //System.out.println(temp);
            return bridgeWords.toString();
        }

        //通过变得数量来判断是否连接
        int[][] tempArray = nodeGraph.getEdges();
        for(int i = 0; i < nodeGraph.getNodesSize(); i++) {
            if(tempArray[label1][i] >= 1 && tempArray[i][label2] >= 1) {
                bridgeWords.add(nodeGraph.getNodeContent(i));
            }
        }
        //如果集合为空
        if(bridgeWords.isEmpty()) {
            temp.append("No bridge words from ").append(word1).append(" to ").append(word2).append("!");
        } else {
            //集合中存在一个或者多个桥接词
            if(bridgeWords.size() == 1) {
                temp.append("The bridge word from ").append(word1).append(" to ").append(word2).append(" is:").append(bridgeWords.get(0));
            } else {
                temp.append("The bridge words from ").append(word1).append(" to ").append(word2).append(" are:");
                for (String bridgeWord : bridgeWords) {
                    if(!bridgeWord.equals(bridgeWords.get(bridgeWords.size() - 1))) {
                        temp.append(bridgeWord).append(",");
                    } else {
                        temp.append("and").append(bridgeWord);
                    }
                }
            }
        }
        //System.out.println(temp);
        return bridgeWords.toString();
    }

    //根据bridge word生成新文本
    public String generateNewText(String inputText) {
        //同理先对输入的文本进行数组化处理
        List<String> inputWords = new ArrayList<>();
        String[] wordsArray = inputText.split("\\W+");
        for (String s : wordsArray) {
            if(s != null) {
                inputWords.add(s);
            }
        }
        for(int i = 0; i < inputWords.size() - 1; i++) {
            List<String> bridgeList = parseStringToList(queryBridgeWords(inputWords.get(i).toLowerCase(), inputWords.get(i + 1).toLowerCase()));
            //如果返回的不为空,则在集合中指定的位置添加新的元素
            if(!bridgeList.isEmpty()) {
                //如果返回的集合中只有一个元素
                if(bridgeList.size() == 1) {
                    inputWords.add(i + 1, bridgeList.get(0));
                } else {
                    //如果返回的元素中有多个元素
                    //生成随机数
                    Random random = new Random();
                    random.nextInt(bridgeList.size());
                    inputWords.add(i + 1, bridgeList.get(random.nextInt(bridgeList.size())));
                }
            }
        }
        return inputWords.toString();
    }

    //将矩阵写入文件
    private void writeMatrixToFile(int[][] matrix, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int[] row : matrix) {
                writer.write(Arrays.toString(row).replaceAll("[\\[\\] ]", ""));
                writer.newLine();
            }
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    //将矩阵节点的内容写入文件
    private void writeNodesToFile(myNode[] nodes, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (myNode node : nodes) {
                writer.write(node.getContent());
                writer.newLine();
            }
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    //调用python脚本
     @SuppressWarnings("CallToPrintStackTrace")
     private void callPythonScript(String nodesFile, String matrixFile) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "/home/hrq2021112608/IdeaProjects/softwareproject1/generate_graph_plus.py", nodesFile, matrixFile, "output.svg");
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //生成有向图
    public void showDirectedGraph() throws IOException {
        int[][] matrix = nodeGraph.getEdges();
        myNode[] nodes = nodeGraph.getNodes().toArray(new myNode[0]);
        String matrixFile = "/home/hrq2021112608/IdeaProjects/softwareproject1/matrix.txt";
        String nodesFile = "/home/hrq2021112608/IdeaProjects/softwareproject1/node.txt";
        writeMatrixToFile(matrix, matrixFile);
        writeNodesToFile(nodes, nodesFile);
        GraphGenerator graphGenerator = new GraphGenerator();
        graphGenerator.generate(matrixFile,nodesFile);
        //调用python脚本
        //callPythonScript(nodesFile, matrixFile);

    }
    public void graphGenerator2(){
        //GraphGenerator grator = new
    }

    //计算单源最短路径
    public String dijkstra(String word1,String word2) throws IOException {
        int numberOfVertices = nodeGraph.getNodesSize();
        int[] distances = new int[numberOfVertices]; //每一个节点的最小距离
        int[] predecessors = new int[numberOfVertices]; //前驱节点集合
        boolean[] visited = new boolean[numberOfVertices]; //已标记数组
        int sourceVertex = nodeGraph.getLabel(word1);
        int disvertex = nodeGraph.getLabel(word2);
        System.out.println(word1);
        System.out.println(word2);
        //对数组进行初始化
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        Arrays.fill(visited, false);
        distances[sourceVertex] = 0;  //到自己的距离要初始为0

        for (int i = 0; i < numberOfVertices; i++) {
            int u = minDistance(distances, visited); //找到当前未访问的且到源节点距离最近的节点,返回其下标
            visited[u] = true;

            //寻找和u存在有向边的节点,并根据节点更新distances数组
            for (int v = 0; v < numberOfVertices; v++) {
                if(!visited[v] && nodeGraph.getEdges()[u][v] != 0) {
                    int newDis = distances[u] + nodeGraph.getEdges()[u][v]; //注意是u节点的距离最短距离啊
                    if(newDis < distances[v]) {
                        distances[v] = newDis; //更新最短距离
                        predecessors[v] = u; //添加或者修改前驱节点
                    }
                }
            }
        }
        if(disvertex == -1) {
            String result = "";
            for (int v = 0; v < numberOfVertices; v++) {
                if (v != sourceVertex) {
                    result += "Shortest distance from " + word1 + " to " + nodeGraph.getNodeContent(v) + " is: " + distances[v] + "\n" + "Shortest path from " + word1 + " to " + nodeGraph.getNodeContent(v) + " is: ";
                    System.out.println("Shortest distance from " + word1 + " to " + nodeGraph.getNodeContent(v) + " is: " + distances[v]);
                    System.out.print("Shortest path from " + sourceVertex + " to " + v + " is: ");
                    result = printPath(predecessors, v, result) + "\n";
                    //System.out.println();
                }
            }
            return result;
        }
        else {
            String result = "Shortest distance from " + word1 + " to " + word2 + " is: " + distances[disvertex] + "\n" + "Shortest path from " + word1 + " to " + word2 + " is: ";
            System.out.println("Shortest distance from " + sourceVertex + " to " + disvertex + " is: " + distances[disvertex]);
            System.out.print("Shortest path from " + sourceVertex + " to " + disvertex + " is: ");
            String answer = "";
            answer = printPath(predecessors, disvertex, answer);
            String[] contents = answer.split(" ");
            int[][] pathedges = new int[nodeGraph.getNodesSize()][nodeGraph.getNodesSize()];
            for(int i = 0;i<contents.length-1;i++){
                pathedges[nodeGraph.getLabel(contents[i])][nodeGraph.getLabel(contents[i+1])] = 1;
            }
            //myNode[] nodes = nodeGraph.getNodes().toArray(new myNode[0]);
            String secondmatrix = "pathmatrix.txt";
            writeMatrixToFile(pathedges,"pathmatrix.txt");
            int[][] matrix = nodeGraph.getEdges();
            myNode[] nodes = nodeGraph.getNodes().toArray(new myNode[0]);
            String matrixFile = "/home/hrq2021112608/IdeaProjects/softwareproject1/matrix.txt";
            String nodesFile = "/home/hrq2021112608/IdeaProjects/softwareproject1/node.txt";
            writeMatrixToFile(matrix, matrixFile);
            writeNodesToFile(nodes, nodesFile);
            GraphGenerator graphGenerator = new GraphGenerator();
            graphGenerator.generate(matrixFile,secondmatrix,nodesFile);
            return result + answer;
        }
    }

    //打印到目标节点的最短路径
    public String printPath(int[] predecessors, int currentVertex,String result) {
        //String result = "";
        if(currentVertex == -1) {
            return result;
        }
         result = printPath(predecessors, predecessors[currentVertex],result);
         //System.out.println(currentVertex + " ");
         return  result + nodeGraph.getNodeContent(currentVertex) + " ";
    }

    //从还未访问过的节点当中选出距离源节点最近的节点
    public int minDistance(int[] distances, boolean[] visited) {
        int minDis = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < distances.length; i++) {
            if(!visited[i] && distances[i] < minDis) {
                minDis = distances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public String randomWalk(myNode startNode) {
        Set<String> visitedEdges = new HashSet<>(); //当前遍历过的边的信息,要求是不能够重复
        List<myNode> visitedNodes = new ArrayList<>(); //当前遍历过的节点,是可以重复的
        StringBuilder sbTemp = new StringBuilder();

        //初始化遍历
        traverse(startNode, visitedEdges, visitedNodes);
        // 输出遍历结果
        sbTemp.append("Traversal path with edges: ");
        for (int i = 0; i < visitedNodes.size(); i++) {
            if(i == visitedNodes.size() - 1) {
                sbTemp.append(visitedNodes.get(i).getContent());
            } else {
                sbTemp.append(visitedNodes.get(i).getContent()).append(" -> ");
            }

        }
        return sbTemp.toString();

    }

    //执行随机DFS

    //从图中随机选择一个节点
    public myNode getRandomNode(List<myNode> nodeList) {
        Random random = new Random();
        return nodeList.get(random.nextInt(nodeList.size()));
    }

    //遍历图的节点
    public void traverse(myNode node, Set<String> visitedEdge, List<myNode> visitedNodes) {
        visitedNodes.add(node); //标记当前节点已经被访问
        //获取到当前节点具有的邻居节点
        List<myNode> neighborNode = new ArrayList<>();
        if(node.getOutDegree() == 0) {
            //如果没有出边的话,就停止遍历
            return;


        }
        //如果存在邻居节点的话
        for (int i = 0; i < nodeGraph.getNodesSize(); i++) {
            if(nodeGraph.getEdges()[nodeGraph.getNodes().indexOf(node)][i] != 0) {
                neighborNode.add(nodeGraph.getNodes().get(i));
            }
        }
        //判断邻居节点的数目是否与节点的出度相等
        if(neighborNode.size() != node.getOutDegree()) {
            System.out.println("there is something wrong");
        }
        //先随机选择一个节点
        myNode nextNode = getRandomNode(neighborNode);
        //判断是否存在重复边

        if(visitedEdge.contains(node.getContent() + "," + nextNode.getContent())) {
            return;
        }
        //否则将边添加到当前节点
        visitedEdge.add(node.getContent() + "," + nextNode.getContent());

        //递归遍历邻居节点
        traverse(nextNode, visitedEdge, visitedNodes);
    }
}
