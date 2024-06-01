package matrixversion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GUI {
    static myGraph myGraph;
    public static void main(String[] args) {
        GraMethod Gram = new GraMethod();
        String pro = "please select file first";
        //final Graph[] graph = new Graph[1];
        // 创建主界面
        JFrame frame = new JFrame("Software_eng_lab1");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建卡片布局容器
        JPanel cardPanel = new JPanel(new CardLayout());

        // 创建第一个面板
        JPanel firstPanel = new JPanel();
        firstPanel.add(new JLabel("This is the MainGUI"));
        firstPanel.add(new JLabel("choose your file"));
        JButton choosefileButton = new JButton("selcet");
        JButton switchtohomepage = new JButton("back");
        JButton switchToSecondButton = new JButton("show graph");
        JButton switchTosearchButton = new JButton("search bridge words");
        JButton switchtogenerateButton = new JButton("generatenewText");
        JButton switchtorandomwalkButton = new JButton("randomwalk");
        JButton switchtoshortestpathButton = new JButton("showshortestpath");
        JButton exitButton = new JButton("exit");

        firstPanel.add(choosefileButton);
        firstPanel.add(switchTosearchButton);
        firstPanel.add(switchToSecondButton);
        firstPanel.add(switchtogenerateButton);
        firstPanel.add(switchtorandomwalkButton);
        firstPanel.add(switchtoshortestpathButton);
        firstPanel.add(exitButton);

        // 创建第二个面板
        JPanel secondPanel = new JPanel();
        secondPanel.add(new JLabel("This is the show panel"));
        JButton switchToFirstButton = new JButton("Go to First Panel");
        //ImageIcon imageIcon = new ImageIcon("/home/hrq2021112608/IdeaProjects/softwareproject1/graph.png"); // 替换为你的图片路径
        ImageIcon originalIcon = new ImageIcon("/home/hrq2021112608/IdeaProjects/softwareproject1/graph.svg"); // 替换为你的图片路径
        Image image = originalIcon.getImage(); // 获取原始图像
        Image scaledImage = image.getScaledInstance(400, 300, Image.SCALE_SMOOTH); // 调整图像大小
        ImageIcon scaledIcon = new ImageIcon(scaledImage); // 创建新的ImageIcon
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setPreferredSize(new Dimension(400, 300));
        secondPanel.add(imageLabel);
        secondPanel.add(switchtohomepage);



        //查询桥借词的界面
        JPanel searchbridgePanel = new JPanel(new FlowLayout());
        JLabel label_search1 = new JLabel("Enter word A:\n");
        JLabel label_search2 = new JLabel("Enter word B:");
        JTextField textField_searchA = new JTextField(20);
        JTextField textField_searchB = new JTextField(20);
        JTextArea textArea_search = new JTextArea();
        JButton searchbridgeButton = new JButton("Search");
        JButton switchtohomepage2 = new JButton("back");
        searchbridgePanel.add(new JLabel("Search bridge words         \n                        "));
        searchbridgePanel.add(new JLabel("\n                            "));
        searchbridgePanel.add(label_search1);
        searchbridgePanel.add(textField_searchA);
        searchbridgePanel.add(label_search2);
        searchbridgePanel.add(textField_searchB);
        searchbridgePanel.add(new JLabel("\n                            "));
        searchbridgePanel.add(searchbridgeButton);
        searchbridgePanel.add(new JLabel("\n                            "));
        searchbridgePanel.add(new JLabel("\n                            "));
        searchbridgePanel.add(textArea_search);

        searchbridgePanel.add(switchtohomepage2);

        //利用桥借词生成新闻本的界面
        JPanel generatenewTextPanel = new JPanel(new FlowLayout());
        JButton switchtoHomepage3 = new JButton("back");
        JButton generateButton = new JButton("generate");
        JLabel promt = new JLabel("please input a sentence");
        JTextArea textArea_generate = new JTextArea();
        JTextField textField_generate = new JTextField(20);
        textArea_generate.setLineWrap(true);
        generatenewTextPanel.add(promt);
        generatenewTextPanel.add(textField_generate);
        generatenewTextPanel.add(generateButton);
        generatenewTextPanel.add(switchtoHomepage3);
        generatenewTextPanel.add(textArea_generate);

        //计算两个单词之间的最短路径的界面
        JPanel shortestpathPanel = new JPanel();
        JButton switchtohomepage6 = new JButton("back");
        JLabel label_shortest1 = new JLabel("Enter word A:\n");
        JLabel label_shortest2 = new JLabel("Enter word B:");
        JTextField textField_shortestA = new JTextField(20);
        JTextField textField_shortestB = new JTextField(20);
        JTextArea textArea_shortest = new JTextArea();
        JButton shortestButton = new JButton("Search");
        //JButton switchtohomepage2 = new JButton("back");
        shortestpathPanel.add(new JLabel("search the shortest path from a and b         \n                        "));
        shortestpathPanel.add(new JLabel("\n               "));
        shortestpathPanel.add(label_shortest1);
        shortestpathPanel.add(textField_shortestA);
        shortestpathPanel.add(label_shortest2);
        shortestpathPanel.add(textField_shortestB);
        shortestpathPanel.add(new JLabel("\n                            "));
        shortestpathPanel.add(shortestButton);
        shortestpathPanel.add(new JLabel("\n                            "));
        shortestpathPanel.add(new JLabel("\n                            "));
        shortestpathPanel.add(textArea_shortest);
        shortestpathPanel.add(switchtohomepage6);

        //随机游走的界面
        JPanel randomtravelPanel = new JPanel(new FlowLayout());
        JButton switchtohomepage5 = new JButton("back");
        JLabel prompt_randomtravel = new JLabel("random travel");
        JButton starttravelButton = new JButton("start travel!");
        JTextArea textArea_travel = new JTextArea(5,20);
        textArea_travel.setLineWrap(true);
        //JScrollPane scrollPane = new JScrollPane(textArea_travel);
        //scrollPane.setPreferredSize(new Dimension(200,20));
        randomtravelPanel.add(prompt_randomtravel);
        randomtravelPanel.add(starttravelButton);
        randomtravelPanel.add(switchtohomepage5);
        randomtravelPanel.add(new JLabel("                            "));
        randomtravelPanel.add(new JLabel("                            "));
        randomtravelPanel.add(textArea_travel);



        // 将面板添加到卡片布局容器中
        cardPanel.add(firstPanel, "Homepage");
        cardPanel.add(secondPanel, "SecondPanel");
        cardPanel.add(searchbridgePanel,"searchbridgePanel");
        cardPanel.add(generatenewTextPanel,"generatePanel");
        cardPanel.add(randomtravelPanel,"randomwalk");
        cardPanel.add(shortestpathPanel,"shortest");



        // 获取卡片布局管理器
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // 退出应用程序
            }
        });
        searchbridgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String word1 = textField_searchA.getText();
                String word2 = textField_searchB.getText();
                //List<String> words = new ArrayList<>();
                if(Gram.nodeGraph==null)
                    textArea_search.setText("please select file first");
                else {
                    String words = Gram.queryBridgeWords(word1, word2);
                    if(words.equals("")) {
                        String NOWORDS = "No bridge words from " + word1 + " to " + word2;
                        textArea_search.setText(NOWORDS);
                    }
                    else{
                        List<String> bridgeWords = Gram.parseStringToList(words);
                        StringBuilder temp = new StringBuilder();

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
                        textArea_search.setText(temp.toString());
                    }
                }
            }
        });
        starttravelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(Gram.nodeGraph==null)
                    textArea_travel.setText(pro);
                else {
                    myNode startNode = Gram.getRandomNode(Gram.nodeGraph.getNodes());
                    String result = Gram.randomWalk(startNode);
                    textArea_travel.setText(result);
                }
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(Gram.nodeGraph==null)
                    textArea_generate.setText(pro);
                else {
                    String result = Gram.generateNewText(textField_generate.getText());
                    result = result.substring(1, result.length() - 1);
                    result = result.replace(",", " ");
                    textArea_generate.setText(result);
                }
            }
        });
        switchTosearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(cardPanel, "searchbridgePanel");
            }
        });
        switchtorandomwalkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(cardPanel,"randomwalk");
            }
        });
        switchtoshortestpathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(cardPanel,"shortest");
            }
        });
        shortestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String word1 = textField_shortestA.getText();
                String word2 = textField_shortestB.getText();
                //List<String> words = new ArrayList<>();
                if (Gram.nodeGraph == null)
                    textArea_shortest.setText("please select file first");
                else {
                    String words = null;
                    try {
                        words = Gram.dijkstra(word1,word2);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    textArea_shortest.setText(words);
                    if (words.equals("")) {
                        //String NOWORDS = "No bridge words from " + word1 + " to " + word2;
                        //textArea_search.setText(NOWORDS);
                    }
                }
            }
        });
        ActionListener backtohome = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(cardPanel, "Homepage");
            }
        };
        switchtohomepage2.addActionListener(backtohome);
        switchtohomepage.addActionListener(backtohome);
        switchtoHomepage3.addActionListener(backtohome);
        switchtohomepage5.addActionListener(backtohome);
        switchtohomepage6.addActionListener(backtohome);
        switchtogenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(cardPanel,"generatePanel");
            }
        });
        choosefileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建一个文件选择器
                JFileChooser fileChooser = new JFileChooser();

                // 显示文件选择对话框
                int result = fileChooser.showOpenDialog(frame);

                // 如果用户选择了文件
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(frame, "Selected file: " + selectedFile.getAbsolutePath());
                    Gram.textToGraph(selectedFile.getAbsolutePath());
                    //graph = GraMethod.loadText(selectedFile.getAbsolutePath());
                    //String answer = Main.printGraph(graph);
                    //jTextArea.setText(answer);
                }
            }
        });
        // 为按钮添加动作监听器
        switchToSecondButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Gram.showDirectedGraph();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // 将卡片布局容器添加到框架中
        frame.getContentPane().add(cardPanel);

        // 显示框架
        frame.setVisible(true);
    }

    private static JButton getjButton(JFrame frame, JTextArea jTextArea) {
        JButton fileChooserButton = new JButton("Choose File");
        fileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建一个文件选择器
                JFileChooser fileChooser = new JFileChooser();

                // 显示文件选择对话框
                int result = fileChooser.showOpenDialog(frame);

                // 如果用户选择了文件
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(frame, "Selected file: " + selectedFile.getAbsolutePath());
                    //graph = GraMethod.loadText(selectedFile.getAbsolutePath());
                    //String answer = Main.printGraph(graph);
                    //jTextArea.setText(answer);
                }
            }
        });
        return fileChooserButton;
    }
}
