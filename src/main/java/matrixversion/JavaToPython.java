package matrixversion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class JavaToPython {
    static class Node {
        private String content;

        public Node(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    public static void main(String[] args) {
        // 生成节点数组
        Node[] nodes = {
            new Node("Node1"),
            new Node("Node2"),
            new Node("Node3"),
            new Node("Node4")
        };

        // 生成矩阵
        int[][] matrix = {
            {0, 1, 0, 1},
            {0, 0, 1, 0},
            {1, 0, 0, 0},
            {0, 0, 1, 0}
        };

        // 将节点数组和矩阵写入文件
        String nodesFile = "node1.txt";
        String matrixFile = "matrix1.txt";
        writeNodesToFile(nodes, nodesFile);
        writeMatrixToFile(matrix, matrixFile);

        // 调用Python脚本
        callPythonScript(nodesFile, matrixFile, "output1.svg");
    }

    private static void writeNodesToFile(Node[] nodes, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Node node : nodes) {
                writer.write(node.getContent());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeMatrixToFile(int[][] matrix, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int[] row : matrix) {
                writer.write(Arrays.toString(row).replaceAll("[\\[\\] ]", ""));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void callPythonScript(String nodesFile, String matrixFile, String outputFile) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "/home/hrq2021112608/IdeaProjects/softwareproject1/generate_graph_plus.py", nodesFile, matrixFile, outputFile);
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
