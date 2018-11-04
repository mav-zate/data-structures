package trees;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class BinarySearchTreeTest {
  BinarySearchTree<Integer> binarySearchTree;

  @BeforeEach
  void init() {
    binarySearchTree = new BinarySearchTree<>(5);
  }

  @Test
  @DisplayName("::insert preserves order")
  void testInsert() {
    List<Integer> keysInExpectedOrder = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    binarySearchTree.insert(9);
    binarySearchTree.insert(8);
    binarySearchTree.insert(1);
    binarySearchTree.insert(4);
    binarySearchTree.insert(3);
    binarySearchTree.insert(7);
    binarySearchTree.insert(6);
    binarySearchTree.insert(2);

    Assertions.assertAll(
        () -> Assertions.assertThrows(IllegalArgumentException.class,
            () -> binarySearchTree.insert(null)),
        () -> Assertions.assertEquals(keysInExpectedOrder, binarySearchTree.depthFirstInOrder())
    );
  }

  @Test
  void testDelete() {
    binarySearchTree.insert(1);
    boolean deletion = binarySearchTree.delete(1);
    boolean noDeletion = binarySearchTree.delete(2);

    Assertions.assertAll(
        () -> Assertions.assertThrows(IllegalArgumentException.class, () -> binarySearchTree.delete(null)),
        () -> Assertions.assertThrows(IllegalArgumentException.class, () -> binarySearchTree.delete(5)),
        () -> Assertions.assertEquals(true, deletion),
        () -> Assertions.assertEquals(false, noDeletion)
    );
  }

  @Test
  void testGetHeight() {
    // left side
    binarySearchTree.insert(2);
    binarySearchTree.insert(1);
    binarySearchTree.insert(3);
    binarySearchTree.insert(4);

    // right side
    binarySearchTree.insert(7);
    binarySearchTree.insert(6);
    binarySearchTree.insert(8);
    binarySearchTree.insert(9);

    Assertions.assertEquals(3, binarySearchTree.getHeight());
  }

  @Test
  void testContains() {
    binarySearchTree.insert(4);

    Assertions.assertAll(
        () -> Assertions.assertThrows(IllegalArgumentException.class, () -> binarySearchTree.contains(null)),
        () -> Assertions.assertEquals(true, binarySearchTree.contains(4)),
        () -> Assertions.assertEquals(false, binarySearchTree.contains(1))
    );
  }

  @Test
  void testDepthFirstInOrder() {
    List<Integer> expectedNodes = Lists.newArrayList(1, 2, 3, 4, 5, 6);

    binarySearchTree.insert(4);
    binarySearchTree.insert(6);
    binarySearchTree.insert(2);
    binarySearchTree.insert(3);
    binarySearchTree.insert(1);

    Assertions.assertEquals(expectedNodes, binarySearchTree.depthFirstInOrder());
  }
}