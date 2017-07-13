/**
 * This algorithm finds the center(s) of a tree.
 *
 * Time complexity: O(V+E)
 *
 * @author Jeffrey Xiao, https://github.com/jeffrey-xiao
 **/

import java.util.*;

public class TreeCenter {

  public static List <Integer> findTreeCenters(List<List<Integer>> graph) {
    
    int n = graph.size();
    int[] degrees = new int[n];

    // Find all leaf nodes
    List <Integer> leaves = new ArrayList<>();
    for(int i = 0; i < n; i++) {
      List <Integer> edges = graph.get(i);
      degrees[i] = edges.size();
      if (degrees[i] <= 1) leaves.add(i);
    }

    int processedLeafs = leaves.size();

    // Remove leaf nodes and decrease the degree of
    // each node adding new leaf nodes progressively 
    // until only the centers remain.
    while(processedLeafs < n) {
      List <Integer> newLeaves = new ArrayList<>();
      for(int node : leaves)
        for (int neighbor : graph.get(node))
          if (--degrees[neighbor] == 1)
            newLeaves.add(neighbor);
      processedLeafs += newLeaves.size();
      leaves = newLeaves;
    }

    return leaves;

  }


    /************ TESTING **********/


  // Create a graph as a adjacency list
  public static List<List<Integer>> createGraph(int n) {
    List<List<Integer>> graph = new ArrayList<>(n);
    for (int i = 0; i < n; i++) graph.add(new LinkedList<>());
    return graph;
  }

  public static void addUndirectedEdge(List<List<Integer>> graph, int from, int to) {
    graph.get(from).add(to);
    graph.get(to).add(from);
  }

  public static void main(String[] args) {

    List<List<Integer>> graph = createGraph(9);
    addUndirectedEdge(graph, 0, 1);
    addUndirectedEdge(graph, 2, 1);
    addUndirectedEdge(graph, 2, 3);
    addUndirectedEdge(graph, 3, 4);
    addUndirectedEdge(graph, 5, 3);
    addUndirectedEdge(graph, 2, 6);
    addUndirectedEdge(graph, 6, 7);
    addUndirectedEdge(graph, 6, 8);

    // Centers are 2
    System.out.println(findTreeCenters(graph));

    // Centers are 0
    List<List<Integer>> graph2 = createGraph(1);
    System.out.println(findTreeCenters(graph2));

    // Centers are 0,1
    List<List<Integer>> graph3 = createGraph(2);
    addUndirectedEdge(graph3, 0, 1);
    System.out.println(findTreeCenters(graph3));

    // Centers are 1
    List<List<Integer>> graph4 = createGraph(3);
    addUndirectedEdge(graph4, 0, 1);
    addUndirectedEdge(graph4, 1, 2);
    System.out.println(findTreeCenters(graph4));

    // Centers are 1,2
    List<List<Integer>> graph5 = createGraph(4);
    addUndirectedEdge(graph5, 0, 1);
    addUndirectedEdge(graph5, 1, 2);
    addUndirectedEdge(graph5, 2, 3);
    System.out.println(findTreeCenters(graph5));

    // Centers are 2,3 
    List<List<Integer>> graph6 = createGraph(7);
    addUndirectedEdge(graph6, 0, 1);
    addUndirectedEdge(graph6, 1, 2);
    addUndirectedEdge(graph6, 2, 3);
    addUndirectedEdge(graph6, 3, 4);
    addUndirectedEdge(graph6, 4, 5);
    addUndirectedEdge(graph6, 4, 6);
    System.out.println(findTreeCenters(graph6));

  }

}













