package graphs;


import arrays.CustomArray;
import arrays.DynamicArray;
import org.junit.jupiter.api.*;

public class AdjacencyListGraphTest {
  AdjacencyListGraph<String> graph;

  @Nested
  @DisplayName("Singleton Graph")
  class EmptyGraph {
    @BeforeEach
    void init() {
      CustomArray<String> nodes = new DynamicArray<>("A");
      CustomArray<Edge> edges = new DynamicArray<>();
      graph = new AdjacencyListGraph<>(nodes, edges);
    }

    @Test
    @DisplayName("::getAdjacentNodes")
    void testGetAdjacentNodes() {
      CustomArray<String> adjacentNodes = graph.getAdjacentNodes("A");
      Assertions.assertEquals(0, adjacentNodes.size());
    }

    @Test
    @DisplayName("::addNode")
    void testAddNode() {
      CustomArray<String> adjacentNodes = new DynamicArray<>("A");
      graph.addNode("B", adjacentNodes);

      Assertions.assertAll(
          () -> Assertions.assertEquals("A", graph.getAdjacentNodes("B").get(0)),
          () -> Assertions.assertEquals(0, graph.getAdjacentNodes("A").size()),
          () -> Assertions.assertEquals(1, graph.getAdjacentNodes("B").size())
      );
    }

    @Test
    @DisplayName("::addEdges")
    void testAddEdges() {
      CustomArray<Edge> edges = new DynamicArray<>(new Edge(0, 0));
      graph.addEdges(edges);

      CustomArray<String> singletonAdjacentNodes = graph.getAdjacentNodes("A");
      Assertions.assertAll(
          () -> Assertions.assertEquals("A", singletonAdjacentNodes.get(0)),
          () -> Assertions.assertEquals(1, singletonAdjacentNodes.size())
      );
    }
  }

  /*
                 A
               /   \
              B      D
            /   \    |
           C     E   |
            \     \ /
              - -  F

   */
  @Nested
  @DisplayName("Undirected graph")
  class UndirectedGraph {
    @BeforeEach
    void init() {
      CustomArray<String> nodes = new DynamicArray<>("A", "B", "C", "D", "E", "F");

      CustomArray<Edge> edges = new DynamicArray<>(
          new Edge(0, 1), new Edge(1, 0), // A - B
          new Edge(1, 2), new Edge(2, 1), // B - C
          new Edge(1, 4), new Edge(4, 1), // B - E
          new Edge(0, 3), new Edge(3, 0), // A - D
          new Edge(3, 5), new Edge(5, 3), // D - F
          new Edge(4, 5), new Edge(5, 4), // E - F
          new Edge(2, 5), new Edge(5, 2)  // C - F
      );

      graph = new AdjacencyListGraph<>(nodes, edges);
    }
  }
}