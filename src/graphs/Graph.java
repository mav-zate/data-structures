package graphs;

import arrays.CustomArray;

public interface Graph<T> {
  CustomArray<T> getShortestPath(T start, T destination);

  boolean doesPathExist(T start, T destination);

  void addNode(T node, CustomArray<T> adjacentNodes);

  void addEdges(CustomArray<Edge> edges);

  CustomArray<T> getAdjacentNodes(T node);
}
