/** Graph.java
* A class for building graphs, with methods for editing the graph and
* searching it.*
* @author Dillon John
* CSIT357-02 Artificial Intelligence
* Dr. Aparna Varde
* Assignment 2: Implementation of Search Methods
*/
import java.util.ArrayList;

public class Graph {
  int[][] matrix;  // The adjacencyk matrix for the graph
  boolean[] visited; // An array, storing the 'visited' status of each node
  String title;
  boolean found = false;
  int pathCost = 0;
  ArrayList<Integer> searchPath = new ArrayList<Integer>();

  /** Graph: default constructor
  * Creates a graph object with a specified number of nodes.
  * Initializes the graph's adjacency matrix with no connecting edges.
  * Creates an array for tracking the visited status of the graph's nodes,
  * or vertices, and Initializes that array to all false, meaning no node has
  * yet been visited.
  */
  Graph(int nodes, String name) {
    // Create a 2D array for the adjacency matrix
    matrix = new int[nodes][nodes];
    // Set all edges in the matrix to 0: No edges connecting the nodes yet
    for (int i = 0 ; i < nodes; i++ ) {
      for(int j = 0; i < nodes; i++){
        matrix[i][j] = 0;
      }
    }
    // Create an array for tracking the 'visited' status of a node
    visited = new boolean[nodes];
    // Set all nodes to false: not yet visited
    for (int node = 0; node < visited.length; node++) {
      visited[node] = false;
    }
    // Give the grapha  title
    title = name;
  }

  /**
   * printMatrix
   * A method for printing an adjacency matrix.
   * @param Graph: a Graph object
   * @return void
   */
  private void printMatrix() {
    System.out.println("\n" + title + "\n");
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
   * edge
   * A method for editing the adjacency matrix to account for connected nodes
   * The default graph is set to all 0s, meaning no nodes are connected by edges
   * Use 1 for representing the existence of an edge
   * Other integer values can be used to represent a weighted edge length.
   * @param node1: an integer, the 'start location' : "from here..."
   * @param node2: an integer, a node that is connected to node 1 by an edge "... to here"
   * @param weight: 1 if edge exists, >=1 if weighted edge. 0 means no edge exists
   * @return void
   */
  private void edge(int node1, int node2, int weight) {
    matrix[node1 -1][node2 -1] = weight;
  }

  /**
   * reset
   * Sets all nodes to 'false' visited status
   * Sets found to 'false'
   * Sets pathCost to 0.
   * Clears the search path.
   * Allows for quickly searching the same graph with different parameters.
   */
  private void reset(){
    found = false;
    pathCost = 0;
    searchPath.clear();
    for (int i = 0; i < visited.length; i++ ) {
      visited[i] = false;
    }
  }

  /**
   * DFS (Depth-First Search)
   * Given and accept states, searches the graph from the start state until
   * it finds the accept state. Tracks the path cost as it traverses the graph.
   * @param startAt: int: the start state, or the node from which the search begins
   * @param lookFor: int: the accept state, or the node being searched for
   * @return void
   */
   private boolean DFS(int startAt, int lookFor){
     // Since our nodes are labeled 1-n, subtract 1 for indexing
     int currentNode = startAt -1;
     int nextNode = 0;

     visited[currentNode] = true;
     if(!found){
       if(startAt == lookFor) {
         // The current node is the accept state.
         found = true;
         // Add the current node to the search path.
         // Use startAt, since that has the appropriate node number, rather
         // than the index of that node in the array or matrix
         searchPath.add(startAt);
         // Accept state has been reached
         System.out.println("Successfully found node " + lookFor + "!");
         System.out.println("Total path cost: " + pathCost);
         System.out.print("Search path: ");
         System.out.print(searchPath);
         System.out.println("\n");
         return found;
       }
       else{
         // Still searching for the accept state
         // Check for an edge connecting the current node to the accept state
         if(matrix[currentNode][lookFor-1] > 0){
           // There is an edge connecting the current node to the accept state
           nextNode = lookFor;
           // Add the current node to the search path.
           // Use startAt, since that has the appropriate node number rather than
           // the index of that node in the array or matrix.
           searchPath.add(startAt);
           // Update the path cost with the weight of the edge between the
           // current node and the accept state.
           pathCost += matrix[currentNode][lookFor-1];
           // Recursively call DFS
           this.DFS(nextNode, lookFor);
          }
          // If there is no edge connecting the current node to the accept
          // state, look for an edge connecting to a node that hasn't been
          // visited yet.
          else{
            for (int i = 0; i < matrix[currentNode].length; i++) {
              if(matrix[currentNode][i] > 0 && !visited[i]){
                // There is an edge connecting the current node to another
                // node that hasn't been visited yet
                // Add the current node to the search path
                searchPath.add(startAt);
                // Set the next node to that unvisited node
                nextNode = i + 1;
                // Update the path cost
                pathCost += matrix[currentNode][i];
                // Recursively call DFS
                this.DFS(nextNode, lookFor);
              }
            }
          }
        }
      }
      return found;
    }

    /**
     * tryEmAll
     * A method for testing every valid combination of start and accept states
     * for a given graph.
     * @return: void;
     */
     private void tryEmAll(){
       for (int node = 0; node < matrix[1].length; node++) {
         for(int vertex = 0; vertex < matrix[1].length; vertex++){
           System.out.println("Starting from " + (node+1) + ", searching for " + (vertex+1) + ".");
           this.reset();
           if(!this.DFS(node + 1, vertex + 1)){
             System.out.println("Could not find " + (vertex+1) + " from " + (node+1) + ".");
             System.out.println("Total path cost: " + pathCost);
             System.out.println("Search path: " + searchPath + "\n");

           }
         }
       }
     }

     /**
     * depthFirst
     * A method for performing a single depth-first search, with appropriate
     * success and failure messages.
     * @param :startFrom: the starting node
     * @param :lookFor: the accept state
     * @return: void
     */
     private void depthFirst(int startFrom, int lookFor){
       this.reset();
       System.out.println("Starting from " + startFrom + ", searching for " + lookFor + ".");
       if(!this.DFS(startFrom, lookFor)){
         System.out.println("Could not find " + startFrom + " from " + lookFor + ".");
         System.out.println("Total path cost: " + pathCost);
         System.out.println("Search path: " + searchPath + "\n");
       }
     }

  public static void main(String[] args){

    // Describe and build graph 1
    System.out.println("\n\nThis graph has unweighted, non-directional edges.");
    Graph g1 = new Graph(7, "Graph 1");
    g1.edge(1, 2, 1); // Node 1 connects to nodes 2, 3 and 5
    g1.edge(1, 3, 1);
    g1.edge(1, 5, 1);

    g1.edge(2, 1, 1); // Node 2 connects to 1, 3, 4, 7
    g1.edge(2, 3, 1);
    g1.edge(2, 4, 1);
    g1.edge(2, 7, 1);

    g1.edge(3, 1, 1); // Node 3 connects to 1, 2, 4, 5, 6, 7
    g1.edge(3, 2, 1);
    g1.edge(3, 4, 1);
    g1.edge(3, 5, 1);
    g1.edge(3, 6, 1);
    g1.edge(3, 7, 1);

    g1.edge(4, 2, 1); // Node 4 connects to 2, 3, 7
    g1.edge(4, 3, 1);
    g1.edge(4, 7, 1);

    g1.edge(5, 1, 1); // Node 5 connects to 1, 3, 6
    g1.edge(5, 3, 1);
    g1.edge(5, 6, 1);

    g1.edge(6, 3, 1); // Node 6 connects to 3, 5, 7
    g1.edge(6, 5, 1);
    g1.edge(6, 7, 1);

    g1.edge(7, 2, 1); // Node 7 connects to 2, 3, 4, 6
    g1.edge(7, 3, 1);
    g1.edge(7, 4, 1);
    g1.edge(7, 6, 1);
    g1.printMatrix();

    g1.depthFirst(1, 7);
    g1.depthFirst(3, 6);

    // Describe and build graph 2
    System.out.println("\n\n\nThis graph has weighted, non-directional edges.");
    Graph g2 = new Graph(7, "Graph 2");
    g2.edge(1, 2, 3); // Node 1 connects to nodes 2, 3 and 5
    g2.edge(1, 3, 1);
    g2.edge(1, 5, 4);

    g2.edge(2, 1, 3); // Node 2 connects to 1, 3, 4, 7
    g2.edge(2, 3, 2);
    g2.edge(2, 4, 1);
    g2.edge(2, 7, 4);

    g2.edge(3, 1, 1); // Node 3 connects to 1, 2, 4, 5, 6, 7
    g2.edge(3, 2, 2);
    g2.edge(3, 4, 1);
    g2.edge(3, 5, 3);
    g2.edge(3, 6, 4);
    g2.edge(3, 7, 5);

    g2.edge(4, 2, 1); // Node 4 connects to 2, 3, 7
    g2.edge(4, 3, 1);
    g2.edge(4, 7, 4);

    g2.edge(5, 1, 4); // Node 5 connects to 1, 3, 6
    g2.edge(5, 3, 3);
    g2.edge(5, 6, 2);

    g2.edge(6, 3, 4); // Node 6 connects to 3, 5, 7
    g2.edge(6, 5, 2);
    g2.edge(6, 7, 1);

    g2.edge(7, 2, 6); // Node 7 connects to 2, 3, 4, 6
    g2.edge(7, 3, 5);
    g2.edge(7, 4, 4);
    g2.edge(7, 6, 1);
    g2.printMatrix();
    g2.depthFirst(1, 7);
    g2.depthFirst(3, 6);

    System.out.println("\n\nThis graph has unweighted, directional edges.");
    Graph g3 = new Graph(7, "Graph 3");
    g3.edge(1, 2, 1); // Node 1 Connects to 2, 3, 5
    g3.edge(1, 3, 1);
    g3.edge(1, 5, 1);

    g3.edge(2, 3, 1); // Node 2 connects to 3, 4, 7
    g3.edge(2, 4, 1);
    g3.edge(2, 7, 1);

    g3.edge(3, 5, 1); // Node 3 connects to 5, 6
    g3.edge(3, 6, 1);

    g3.edge(4, 3, 1); // Node 4 connects to 3, 7
    g3.edge(4, 7, 1);

    g3.edge(5, 1, 1); // Node 5 connects to 1, 6
    g3.edge(5, 6, 1);

    g3.edge(6, 3, 1); // Node 6 connects to 3, 5, 7
    g3.edge(6, 5, 1);
    g3.edge(6, 7, 1);

    g3.edge(7, 2, 1); // Node 7 connects to 2, 3
    g3.edge(7, 3, 1);
    g3.printMatrix();
    g3.depthFirst(1, 7);
    g3.depthFirst(3, 6);

    System.out.println("\n\nThis graph has weighted, directional edges.");
    Graph g4 = new Graph(7, "Graph 4");
    g4.edge(1, 2, 3); // Node 1 Connects to 2, 3, 5
    g4.edge(1, 3, 1);
    g4.edge(1, 5, 4);

    g4.edge(2, 3, 2); // Node 2 connects to 3, 4, 7
    g4.edge(2, 4, 1);
    g4.edge(2, 7, 6);

    g4.edge(3, 5, 3); // Node 3 connects to 5, 6
    g4.edge(3, 6, 4);

    g4.edge(4, 3, 1); // Node 4 connects to 3, 7
    g4.edge(4, 7, 4);

    g4.edge(5, 1, 4); // Node 5 connects to 1, 6
    g4.edge(5, 6, 2);

    g4.edge(6, 3, 1); // Node 6 connects to 3, 5, 7
    g4.edge(6, 5, 1);
    g4.edge(6, 7, 1);

    g4.edge(7, 2, 1); // Node 7 connects to 2, 3
    g4.edge(7, 3, 1);
    g4.printMatrix();
    g4.depthFirst(1, 7);
    g4.depthFirst(3, 6);

    System.out.println("\n\nThis graph has 3 nodes, with no edges.");
    Graph g5 = new Graph(3, "Graph 5");
    g5.printMatrix();
    // Search through graph five, using every valid combination of
    // start and accept states.
    g5.depthFirst(1, 3);
    g5.depthFirst(3,3);

    System.out.println("\n\nThis graph has 3 nodes, with unweighted, directional edges.");
    Graph g6 = g5;
    g6.title = "Graph 6";
    g6.edge(1, 2, 1); // Node 1 connects to 2,3
    g6.edge(1, 3, 1);

    g6.edge(2, 3, 1); // Node 2 connects to 3

    g6.edge(3, 1, 1); // Node 3 connects to 1
    g6.printMatrix();
    g6.depthFirst(1, 3);
    g6.depthFirst(3, 2);
  }
}
