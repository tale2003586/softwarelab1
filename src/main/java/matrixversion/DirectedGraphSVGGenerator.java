package matrixversion;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.io.FileWriter;
import java.io.IOException;

public class DirectedGraphSVGGenerator {
    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
                {0, 1, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0}
        };

        try {
            // 创建DOM文档
            DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
            String svgNS = "http://www.w3.org/2000/svg";
            Document document = domImpl.createDocument(svgNS, "svg", null);

            // 创建SVG绘图对象
            SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

            // 添加白色背景
            svgGenerator.setColor(java.awt.Color.WHITE);
            svgGenerator.fillRect(0, 0, 500, 500); // 设置为图形的尺寸

            // 绘制有向图
            int nodeCount = adjacencyMatrix.length;
            for (int i = 0; i < nodeCount; i++) {
                for (int j = 0; j < nodeCount; j++) {
                    if (adjacencyMatrix[i][j] == 1) {
                        // 绘制从节点 i 到节点 j 的有向边
                        svgGenerator.drawLine(i * 50, j * 50, (i + 1) * 50, (j + 1) * 50);
                    }
                }
            }

            // 保存为SVG文件
            FileWriter writer = new FileWriter("directed_graph.svg");
            svgGenerator.stream(writer, true);
            writer.flush();
            writer.close();
            System.out.println("SVG saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
