package matrixversion;

import java.io.*;
import java.util.Arrays;

public class CallPython {

    public static void main(String[] args) {
        // 生成矩阵
        int[][] matrix = {
            {0, 1, 0, 1},
            {0, 0, 1, 0},
            {1, 0, 0, 0},
            {0, 0, 1, 0}
        };

        // 将矩阵写入文件
        String matrixFile = "matrix.txt";
        writeMatrixToFile(matrix, matrixFile);

        // 调用Python脚本
        callPythonScript(matrixFile, "output.svg");
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

    private static void callPythonScript(String matrixFile, String outputFile) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "E:\\@python-code\\sf_en_lab1\\generate_graph.py", matrixFile, outputFile);
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

