/**
* searchMethods.java
* @author Dillon John, Maciej Sudol, Iffat Siddiqi, Granit Gashi
* CSIT357-02 Artificial Intelligence
* Dr. Aparna Varde
* Assignment 2: Implementation of Search Methods
*/


public class searchMethods {


	/**
	 * An  adjacency matrix, representing a graph.
	 * The graph is initialized with no connecting edges, filled with 0s
	 * @param nodes: the integer number of nodes in the graph
	 * @return: int[nodes][nodes]:  a 2D array with size 'nodes' x 'nodes'
	 */
	private static int[][] graph(int nodes) {
		int[][] graph  = new int[nodes][nodes];
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				graph[i][j] = 0;
			}
		}
		return graph;
	}

	/**
	 * A method for editing the adjacency matrix to account for connected nodes
	 * The default graph is set to all 0s, meaning no nodes are connected by edges
	 * Use 1 for representing the existence of an edge
	 * Other integer values can be used to represent a weighted edge length.
	 * @param graph: int[][] a 2D matrix of integers; the adjacency matrix
	 * @param node1: an integer, the primary node under consideration (current state)
	 * @param node2: an integer, a node that is connected to node 1 by an edge
	 * @param weight: 1 if edge exists, >=1 if weighted edge
	 * @return void
	 */
	private static void edge(int[][] graph, int node1, int node2, int weight) {
		graph[node1 -1][node2 -1] = weight;
	}

	private static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}


	public static void main(String[] args) {
		int[][] g1 = graph(7); // create a graph

		// Unweighted: update graph to correctly represent the unweighted edges
		edge(g1, 1, 2, 1); // Node 1 connects to nodes 2, 3 and 5
		edge(g1, 1, 3, 1);
		edge(g1, 1, 5, 1);

		edge(g1, 2, 1, 1); // Node 2 connects to 1, 3, 4, 7
		edge(g1, 2, 3, 1);
		edge(g1, 2, 4, 1);
		edge(g1, 2, 7, 1);

		edge(g1, 3, 1, 1); // Node 3 connects to 1, 2, 4, 5, 6, 7
		edge(g1, 3, 2, 1);
		edge(g1, 3, 4, 1);
		edge(g1, 3, 5, 1);
		edge(g1, 3, 6, 1);
		edge(g1, 3, 7, 1);

		edge(g1, 4, 2, 1); // Node 4 connects to 2, 3, 7
		edge(g1, 4, 3, 1);
		edge(g1, 4, 7, 1);

		edge(g1, 5, 1, 1); // Node 5 connects to 1, 3, 6
		edge(g1, 5, 3, 1);
		edge(g1, 5, 6, 1);

		edge(g1, 6, 3, 1); // Node 6 connects to 3, 5, 7
		edge(g1, 6, 5, 1);
		edge(g1, 6, 7, 1);

		edge(g1, 7, 2, 1); // Node 7 connects to 2, 3, 4, 6
		edge(g1, 7, 3, 1);
		edge(g1, 7, 4, 1);
		edge(g1, 7, 6, 1);

		printMatrix(g1); // Prints the adjacency matrix for graph g1

		// g2 is the weighted version of graph g1
		int[][] g2 = g1;

		edge(g2, 1, 2, 3); // Node 1 connects to nodes 2, 3 and 5
		edge(g2, 1, 3, 1);
		edge(g2, 1, 5, 4);

		edge(g2, 2, 1, 3); // Node 2 connects to 1, 3, 4, 7
		edge(g2, 2, 3, 2);
		edge(g2, 2, 4, 1);
		edge(g2, 2, 7, 6);

		edge(g2, 3, 1, 1); // Node 3 connects to 1, 2, 4, 5, 6, 7
		edge(g2, 3, 2, 2);
		edge(g2, 3, 4, 1);
		edge(g2, 3, 5, 3);
		edge(g2, 3, 6, 4);
		edge(g2, 3, 7, 5);

		edge(g2, 4, 2, 1); // Node 4 connects to 2, 3, 7
		edge(g2, 4, 3, 1);
		edge(g2, 4, 7, 4);

		edge(g2, 5, 1, 4); // Node 5 connects to 1, 3, 6
		edge(g2, 5, 3, 3);
		edge(g2, 5, 6, 2);

		edge(g2, 6, 3, 4); // Node 6 connects to 3, 5, 7
		edge(g2, 6, 5, 2);
		edge(g2, 6, 7, 1);

		edge(g2, 7, 2, 6); // Node 7 connects to 2, 3, 4, 6
		edge(g2, 7, 3, 5);
		edge(g2, 7, 4, 4);
		edge(g2, 7, 6, 1);

		printMatrix(g2);
	}

}
