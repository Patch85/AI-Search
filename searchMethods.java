/**
* searchMethods.java
* @author Dillon John, Maciej Sudol, Iffat Siddiqi, Granit Gashi
* CSIT357-02 Artificial Intelligence
* Dr. Aparna Varde
* Assignment 2: Implementation of Search Methods
*/

public class searchMethods {

	/**
	 * graph
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
	 * edge
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

	/**
	 * printMatrix
	 * A method for printing an adjacency matrix. Formatted to display the node
	 * numbers across the top and left side for easy cross reference.
	 * @param matrix: int[][] a 2D matrix of integers; the adjacency matrix
	 * @return void
	 */
	private static void printMatrix(int[][] matrix) {
		System.out.println();
		// Print node numbers across the top
		System.out.print("N   ");
		for(int n = 0; n < matrix.length; n++){
			System.out.print(n+1 + "  ");
		}
		System.out.println("\n");
		int node = 1;
		for (int i = 0; i < matrix.length; i++) {
			System.out.print(node + ": ");
			node++;
			for (int j = 0; j < matrix.length; j++) {

				System.out.print(" " + matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * visited
	 * Creates an array of boolean values, representing the nodes of a graph.
	 * True if the node has been visited, false otherwise.
	 * Initialized with all false values (not visited yet)
	 * @param nodes: the integer number of nodes in the Graph
	 * @return visited: an array of boolean values
	 */
	private static boolean[] visitedList(int nodes){
		boolean[] visited = new boolean[nodes];
		for (int i = 0; i < visited.length; i++ ) {
			visited[i] = false;
		}
		return visited;
	}

	/**
	 * resetVisited
	 * a method to reset the list of nodes to all 'false',
	 * meaning they have not yet been visited
	 */
	private static void resetVisited(boolean[] visitedList){
		for (int i = 0; i < visitedList.length; i++ ) {
			visitedList[i] = false;
		}
	}


/**
 * DFS
 * A Depth-first search method, takes an adjacency matrix, a start state and an
 * accept state. Returns true if the accept state is reached, false otherwise.
 * @param graph: int[][]: an adjacency matrix representing a graph
 * @param startAt: int: the start state, or the node from which the search begins
 * @param lookFor: int: the accept state, or the node being searched for
 * @param visited: boolean[] : A list representing the visited status of each node
 * @return: true if the accept state has been reached, false otherwise.
 */
private static void DFS(int[][] graph, int startAt, int lookFor, boolean[] visited){
	int currentNode = startAt;
	int pathCost = 0;
	int nextNode = 0;
	if(startAt == lookFor) {
		// Accept state is reached (base case)
		visited[currentNode-1] = true;
		//System.out.print(currentNode);
		System.out.print(currentNode);
		System.out.println("\nSuccesfully found node " + lookFor);
	}
	else{
		// Still searching for the accept state
		// If the current node hasn't been visited yet...
		if(!visited[currentNode-1]){ // -1 to account for being off by one in node labels
			// Add the current node to the search path.
			System.out.print(currentNode + " ");
			// Set the current node to visited.
			visited[currentNode-1] = true;

			// For the current node, check the adjacency matrix for an edge
			// that connects to a node that has not yet been visited.
			for (int i = 0; i < graph[currentNode-1].length; i++ ) {
				// If an edge exists between the current node and another node
				// that hasn't been visited yet...
				if ((graph[currentNode-1][i] >=1) && !visited[i]){
					// Assign that node to next node.
					nextNode = i+1;
					// Recursively call the DFS
					DFS(graph, nextNode, lookFor, visited);
				}
			}
		}
	}
}

	public static void main(String[] args) {
		int [][] g1 = graph(7); // create a graph
		boolean[] g1Visited = visitedList(7); // an array for tracking visited nodes

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

		System.out.println("Graph 1: Unweighted");
		printMatrix(g1); // Prints the adjacency matrix for graph g1

		// g2 is the weighted version of graph g1
		int[][] g2 = g1;
		// an array for tacking whether a node in g2 has been visited
		boolean[] g2Visited = visitedList(7);

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

		System.out.println("Graph 2: Graph 1 with weighted edges.");
		printMatrix(g2);

		for (int i = 1;i < 8; i++ ) {
			resetVisited(g1Visited);
			System.out.println("\n\nSearching on graph 1 for node  " + i);
			System.out.print("Search path: ");
			DFS(g1, 1, i, g1Visited);
		}

		for (int i = 1;i < 8; i++ ) {
			resetVisited(g2Visited);
			System.out.println("\n\nSearching on graph 2 for node  " + i);
			System.out.print("Search path: ");
			DFS(g1, 1, i, g2Visited);
		}
	}
}
