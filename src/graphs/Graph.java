package graphs;

import arrays.CustomArray;
import linkedlists.SinglyLinkedList;

public interface Graph<T> {
  SinglyLinkedList<T> getShortestPath(T start, T destination);

  boolean doesPathExist(T start, T destination);

  void addNode(T node, CustomArray<T> adjacentNodes);

  void addEdges(CustomArray<Edge> edges);

  CustomArray<T> getAdjacentNodes(T node);
}
