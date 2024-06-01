package matrixversion;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GraphGenerator {
    public GraphGenerator() {
    }

    public void generate(String matrix1name, String nodesname) throws IOException {
        generate(matrix1name, null, nodesname);
    }

    public void generate(String matrix1name, String matrix2name, String nodesname) throws IOException {
        // 设置图形引擎为 Swing
        System.setProperty("org.graphstream.ui", "swing");

        // 读取第一个邻接矩阵
        int[][] adjacencyMatrix1 = readAdjacencyMatrixFromFile(matrix1name);
        int[][] adjacencyMatrix2 = null;
        if (matrix2name != null) {
            adjacencyMatrix2 = readAdjacencyMatrixFromFile(matrix2name);
        }
        List<String> nodeNames = readNodeNames(nodesname);

        // 创建图
        Graph graph = createGraphFromAdjacencyMatrices(adjacencyMatrix1, adjacencyMatrix2);

        int count = 0;
        for (Node node : graph) {
            node.setAttribute("ui.label", nodeNames.get(count));
            node.setAttribute("ui.style", "text-size: 20;"); // 设置标签的字体大小为 20
            count++;
        }

        // 可视化图
        Viewer viewer = graph.display();
        viewer.enableAutoLayout(); // 启用自动布局

        ViewPanel viewPanel = (ViewPanel) viewer.getDefaultView();

        try {
            Dimension dimension = viewPanel.getSize();
            BufferedImage image = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            viewPanel.paint(graphics);
            ImageIO.write(image, "png", new File("graph.png"));
            System.out.println("Graph saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readNodeNames(String filename) throws IOException {
        List<String> nodeNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                nodeNames.add(line.trim()); // 将每一行添加到节点名称列表中
            }
        }
        return nodeNames;
    }

    public int[][] readAdjacencyMatrixFromFile(String filename) {
        List<int[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elements = line.trim().split(",");
                int[] row = new int[elements.length];
                for (int i = 0; i < elements.length; i++) {
                    row[i] = Integer.parseInt(elements[i]);
                }
                rows.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 检查行数和列数是否一致
        int numRows = rows.size();
        if (numRows == 0) {
            throw new IllegalArgumentException("邻接矩阵文件为空");
        }
        int numCols = rows.get(0).length;
        for (int[] row : rows) {
            if (row.length != numCols) {
                throw new IllegalArgumentException("邻接矩阵的行数和列数不一致");
            }
        }

        int[][] adjacencyMatrix = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            adjacencyMatrix[i] = rows.get(i);
        }
        return adjacencyMatrix;
    }

    public Graph createGraphFromAdjacencyMatrices(int[][] adjacencyMatrix1, int[][] adjacencyMatrix2) {
        Graph graph = new SingleGraph("Graph");
        for (int i = 0; i < adjacencyMatrix1.length; i++) {
            graph.addNode(String.valueOf(i)); // 使用节点索引作为节点 ID
        }
        for (int i = 0; i < adjacencyMatrix1.length; i++) {
            for (int j = 0; j < adjacencyMatrix1[i].length; j++) {
                if (adjacencyMatrix1[i][j] != 0 || (adjacencyMatrix2 != null && adjacencyMatrix2[i][j] != 0)) {
                    // 添加有向边并设置颜色和权值标签
                    Edge edge = graph.addEdge(String.valueOf(i) + "-" + String.valueOf(j), String.valueOf(i), String.valueOf(j), true);
                    if (adjacencyMatrix2 == null) {
                        edge.setAttribute("ui.style", "arrow-shape: arrow; fill-color: black;");
                    } else if (adjacencyMatrix1[i][j] != 0 && adjacencyMatrix2[i][j] != 0) {
                        edge.setAttribute("ui.style", "arrow-shape: arrow; fill-color: red;");
                    } else if (adjacencyMatrix1[i][j] != 0) {
                        edge.setAttribute("ui.style", "arrow-shape: arrow; fill-color: black;");
                    } else {
                        edge.setAttribute("ui.style", "arrow-shape: arrow; fill-color: green;");
                    }
                    edge.setAttribute("ui.label", String.valueOf(adjacencyMatrix1[i][j] != 0 ? adjacencyMatrix1[i][j] : adjacencyMatrix2[i][j])); // 设置边的权值为标签
                }
            }
        }
        return graph;
    }

    public static void main(String[] args) throws IOException {
        GraphGenerator generator = new GraphGenerator();
        // 仅输入一个邻接矩阵
        generator.generate("/path/to/matrix1.txt", "/path/to/nodes.txt");

        // 输入两个邻接矩阵
        // generator.generate("/path/to/matrix1.txt", "/path/to/matrix2.txt", "/path/to/nodes.txt");
    }
}
