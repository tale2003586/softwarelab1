# @Version   : 1.0
# @Author    : liuyu
# @File      : generate_graph_plus.py
# @Time      : 2024/5/22 18:42

import sys
import networkx as nx
import matplotlib.pyplot as plt


class Node:
    def __init__(self, content):
        self.content = content

def read_nodes(file_name):
    nodes = []
    with open(file_name, 'r') as f:
        for line in f:
            content = line.strip()
            nodes.append(Node(content))
    print(nodes[0].content)
    return nodes
def read_matrix(file_name):
    with open(file_name, 'r') as f:
        matrix = []
        for line in f:
            row = list(map(int, line.strip().split(',')))
            matrix.append(row)
    return matrix


def generate_directed_graph(matrix, nodes):
    G = nx.DiGraph()
    n = len(matrix)
    for i in range(n):
        for j in range(n):
            if matrix[i][j] != 0:
                G.add_edge(nodes[i].content, nodes[j].content, weight=matrix[i][j])
    return G


def save_graph_as_svg(G, output_file):
    plt.figure(figsize=(8, 6))
    pos = nx.spring_layout(G)

    nx.draw(G, pos, with_labels=True, node_size=700, node_color='skyblue', arrowsize=20)
    nx.draw_networkx_edge_labels(G, pos, edge_labels=nx.get_edge_attributes(G, 'weight'))  # 绘制边的权重
    plt.savefig(output_file, format='svg')
    plt.show()


if __name__ == '__main__':
    if len(sys.argv) != 4:
        print("Usage: python generate_graph.py <nodes_file> <matrix_file> <output_file>")
        sys.exit(1)

    nodes_file = sys.argv[1]
    matrix_file = sys.argv[2]
    output_file = sys.argv[3]

    nodes = read_nodes(nodes_file)
    matrix = read_matrix(matrix_file)
    G = generate_directed_graph(matrix, nodes)
    save_graph_as_svg(G, output_file)

