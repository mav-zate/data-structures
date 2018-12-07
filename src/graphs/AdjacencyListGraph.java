package graphs;

import arrays.CustomArray;
import arrays.DynamicArray;
import hashtables.CustomHashTable;
import linkedlists.SinglyLinkedList;
import nodes.SinglyLinkedNode;
import stacksqueues.Queue;

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
  private CustomHashTable<T, CustomArray<CustomArray<Integer>>> distancesFromNode;

  public AdjacencyListGraph(CustomArray<T> nodes, CustomArray<Edge> edges) {
    if (!verifyNodes(nodes)) {
      throw new IllegalArgumentException("Nodes cannot be null or empty");
    } else if (hasDuplicates(nodes)) {
      throw new IllegalArgumentException("Graph cannot contain duplicate nodes");
    }
    if (!verifyEdges(edges, nodes.size())) {
      throw new IllegalArgumentException("Edges cannot connect nodes not in graph");
    }

    this.nodes = nodes;
    initNodeToKey();
    initAdjacencyList(edges);
    this.distancesFromNode = new CustomHashTable<>();
  }

  /**
   *
   *
   * @param start
   * @param destination
   * @return
   */
  @Override
  public SinglyLinkedList<T> getShortestPath(T start, T destination) {
    if (nodeToIndex.get(start) == null || nodeToIndex.get(destination) == null) {
      throw new NullPointerException("Graph must contain both start and destination nodes");
    }

    if (distancesFromNode.get(start) == null) {
      CustomArray<CustomArray<Integer>> distancesAndPathsToNode = initDistancesAndPathsToNode(nodeToIndex.get(start));
      boolean[] visitedNodes = new boolean[nodes.size()];

      Queue<Integer> queue = new Queue<>();
      queue.enqueue(nodeToIndex.get(start));
      while (queue.size() > 0) {
        Integer currentNodeIndex = queue.dequeue();

        if (!visitedNodes[currentNodeIndex]) {
          SinglyLinkedNode<Integer> adjacentNode = adjacencyList.get(currentNodeIndex).getIterator();
          while (adjacentNode.hasNext()) {
            adjacentNode = adjacentNode.getNext();

            Integer adjacentNodeIndex = adjacentNode.getData();
            changeNodeShortestDistanceFromStart(currentNodeIndex, adjacentNodeIndex, distancesAndPathsToNode);

            if (!visitedNodes[adjacentNodeIndex]) {
              queue.enqueue(adjacentNodeIndex);
            }
          }

          visitedNodes[currentNodeIndex] = true;
        }
      }

      distancesFromNode.put(start, distancesAndPathsToNode);
    }

    return constructPathFromDistanceTable(start, destination);
  }

  private void changeNodeShortestDistanceFromStart(Integer currentNodeIndex, Integer adjacentNodeIndex,
                                                   CustomArray<CustomArray<Integer>> distancesAndPathsToNode) {
    Integer currentNodeDistance = distancesAndPathsToNode.get(currentNodeIndex).get(0);
    CustomArray<Integer> adjacentNodeDistancePathPair = distancesAndPathsToNode.get(adjacentNodeIndex);
    if (adjacentNodeDistancePathPair.get(0) > currentNodeDistance + 1) {
      adjacentNodeDistancePathPair.set(0, currentNodeDistance + 1);
      adjacentNodeDistancePathPair.set(1, currentNodeIndex);
    }
  }

  private CustomArray<CustomArray<Integer>> initDistancesAndPathsToNode(Integer nodeIndex) {
    CustomArray<CustomArray<Integer>> distancesAndPathsToNode = new DynamicArray<>();

    for (int i = nodes.size() - 1; i >= 0; i--) {
      CustomArray<Integer> distanceAndPath = new DynamicArray<>();
      if (i == nodeIndex) {
        distanceAndPath.add(0);
      } else {
        distanceAndPath.add(Integer.MAX_VALUE);
      }
      distanceAndPath.add(-1);
      distancesAndPathsToNode.add(distanceAndPath);
    }

    return distancesAndPathsToNode;
  }

  private SinglyLinkedList<T> constructPathFromDistanceTable(T start, T destination) {
    SinglyLinkedList<T> path = new SinglyLinkedList<T>();
    path.insert(destination);

    CustomArray<CustomArray<Integer>> distancesAndPaths = distancesFromNode.get(start);
    CustomArray<Integer> distanceAndPathForNode = distancesAndPaths.get(nodeToIndex.get(destination));
    Integer nodeIndex;
    while ((nodeIndex = distanceAndPathForNode.get(1)) != null) {
      T node = nodes.get(nodeIndex);
      path.insert(node);

      distanceAndPathForNode = distancesAndPaths.get(nodeIndex);
    }

    return path;
  }

  /**
   *
   * @param start
   * @param destination
   * @return
   */
  @Override
  public boolean doesPathExist(T start, T destination) {
    // TODO: write method (DFS)
    return true;
  }

  /**
   *
   *
   * @param newNode
   * @param adjacentNodes
   */
  @Override
  public void addNode(T newNode, CustomArray<T> adjacentNodes) {
    if (contains(newNode)) {
      return;
    }

    nodes.add(newNode);
    adjacencyList.add(new SinglyLinkedList<>());

    int newNodeIndex = nodes.size() - 1;
    nodeToIndex.put(newNode, newNodeIndex);
    for (int i = adjacentNodes.size() - 1; i > -1; i--) {
      Integer currentNodeIndex = nodeToIndex.get(adjacentNodes.get(i));

      // TODO: notify user if adjacent node not in graph
      if (currentNodeIndex != null) {
        SinglyLinkedList<Integer> newAdjacentNodes = adjacencyList.get(newNodeIndex);
        newAdjacentNodes.insert(currentNodeIndex);
      }
    }
  }

  /**
   *
   *
   * @param edges
   */
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

  /**
   *
   *
   * @param node
   * @return
   */
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

  private boolean verifyEdges(CustomArray<Edge> edges, int nodeSize) {
    int lastNodeIndex = nodeSize - 1;

    for (int i = edges.size() - 1; i > -1; i--) {
      Edge currentEdge = edges.get(i);
      int start = currentEdge.getStart();
      int end = currentEdge.getEnd();
      if (start > lastNodeIndex || start < 0 || end > lastNodeIndex || end < 0) {
        return false;
      }
    }

    return true;
  }

  private void initNodeToKey() {
    nodeToIndex = new CustomHashTable<>();

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
