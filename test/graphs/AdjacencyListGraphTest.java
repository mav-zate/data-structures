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
          () -> Assertions.assertEquals("B", graph.getAdjacentNodes("A").get(0)),
          () -> Assertions.assertEquals(1, graph.getAdjacentNodes("A").size()),
          () -> Assertions.assertEquals(0, graph.getAdjacentNodes("B").size())
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
}