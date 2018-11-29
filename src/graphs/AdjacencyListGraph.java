package graphs;

import arrays.CustomArray;
import arrays.DynamicArray;
import hashtables.CustomHashTable;
import linkedlists.SinglyLinkedList;
import nodes.SinglyLinkedNode;

/**
 * Graph that allows for both directional/undirectional edges
 *
 * Prohibits duplicate nodes (or nodes with equal values)
 *
 * @param <T>
 */
public class AdjacencyListGraph<T> implements Graph<T> {
  private CustomArray<T> nodes;
  private CustomHashTable<T, Integer> nodeToIndex;
  private CustomArray<SinglyLinkedList<Integer>> adjacencyList;

  public AdjacencyListGraph(CustomArray<T> nodes, CustomArray<Edge> edges) {
    if (!verifyNodes(nodes)) {
      throw new IllegalArgumentException("Nodes cannot be null or empty");
    } else if (hasDuplicates(nodes)) {
      throw new IllegalArgumentException("Graph cannot contain duplicate nodes");
    }
    if (!verifyEdges(edges)) {
      throw new IllegalArgumentException("Edges cannot connect nodes not in graph");
    }

    this.nodes = nodes;
    initNodeToKey();
    initAdjacencyList(edges);
  }

  @Override
  public CustomArray<T> getShortestPath(T start, T destination) {
    // TODO: write method (BFS)
    return null;
  }

  @Override
  public boolean doesPathExist(T start, T destination) {
    // TODO: write method (DFS)
    return true;
  }

  @Override
  public void addNode(T newNode, CustomArray<T> adjacentNodes) {
    if (contains(newNode)) {
      return;
    }

    nodes.add(newNode);
    adjacencyList.add(new SinglyLinkedList<>());

    int newNodeIndex = nodes.size() - 1;
    for (int i = adjacentNodes.size() - 1; i > -1; i--) {
      Integer currentNodeIndex = nodeToIndex.get(adjacentNodes.get(i));

      // TODO: notify user if adjacent node not in graph
      if (currentNodeIndex != null) {
        SinglyLinkedList<Integer> newAdjacentNodes = adjacencyList.get(newNodeIndex);
        newAdjacentNodes.insert(currentNodeIndex);
      }
    }
  }

  @Override
  public void addEdges(CustomArray<Edge> edges) {
    for (int i = edges.size() - 1; i > -1; i--) {
      Edge currentEdge = edges.get(i);
      int start = currentEdge.getStart();
      int end = currentEdge.getEnd();
      // TODO: notify user if either start or end node not in graph
      if (nodes.get(start) != null && nodes.get(end) != null) {
        SinglyLinkedList<Integer> adjacentNodes = adjacencyList.get(start);
        if (!adjacentNodes.contains(end)) {
          adjacentNodes.insert(end);
        }
      }
    }
  }

  @Override
  public CustomArray<T> getAdjacentNodes(T node) {
    Integer nodeIndex = nodeToIndex.get(node);
    if (nodeIndex == null) {
      throw new NullPointerException("The graph does not contain this node: " + node);
    }

    CustomArray<T> adjacentNodes = new DynamicArray<>();

    SinglyLinkedNode<Integer> adjacentNode = adjacencyList.get(nodeIndex).getIterator();
    while (adjacentNode.hasNext()) {
      adjacentNode = adjacentNode.getNext();

      Integer adjacentNodeIndex = adjacentNode.getData();
      adjacentNodes.add(nodes.get(adjacentNodeIndex));
    }

    return adjacentNodes;
  }

  private boolean contains(T node) {
    for (int i = nodes.size() - 1; i > -1; i--) {
      if (node.equals(nodes.get(i))) {
        return true;
      }
    }

    return false;
  }

  private boolean verifyNodes(CustomArray<T> nodes) {
    if (nodes == null || nodes.size() < 1) {
      return false;
    }

    return true;
  }

  private boolean hasDuplicates(CustomArray<T> nodes) {
    CustomHashTable<T, Boolean> seenNodes = new CustomHashTable<>();

    for (int i = nodes.size() - 1; i >= 0; i--) {
      T currentNode = nodes.get(i);
      if (seenNodes.get(currentNode) != null) {
        return true;
      }
      seenNodes.put(currentNode, true);
    }

    return false;
  }

  private boolean verifyEdges(CustomArray<Edge> edges) {
    int lastIndex = nodes.size() - 1;
      for (int i = lastIndex; i > -1; i--) {
        Edge currentEdge = edges.get(i);
        int start = currentEdge.getStart();
        int end = currentEdge.getEnd();
        if (start > lastIndex || start < 0 || end > lastIndex || end < 0) {
          return false;
        }
      }

    return true;
  }

  private void initNodeToKey() {
    for (int i = 0; i < nodes.size(); i++) {
      nodeToIndex.put(nodes.get(i), i);
    }
  }

  private void initAdjacencyList(CustomArray<Edge> edges) {
    adjacencyList = new DynamicArray<>();

    for (int i = 0; i < nodes.size(); i++) {
      adjacencyList.add(new SinglyLinkedList<>());
    }

    for (int i = 0; i < edges.size(); i++) {
      Edge currentEdge = edges.get(i);
      SinglyLinkedList<Integer> adjacentNodes = adjacencyList.get(currentEdge.getStart());
      adjacentNodes.insert(currentEdge.getEnd());
    }
  }
}
