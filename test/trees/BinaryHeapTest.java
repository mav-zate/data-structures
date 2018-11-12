package trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryHeapTest {
  BinaryHeap<Integer> binaryHeap;

  @BeforeEach
  void init() {
    binaryHeap = new BinaryHeap<>();
  }

  @Test
  void testPeek() {
    binaryHeap.push(3);
    binaryHeap.push(2);
    binaryHeap.push(1);

    Assertions.assertEquals(1, (int) binaryHeap.peek());
  }

  @Test
  void testPush() {
    binaryHeap = new BinaryHeap<>(2);
    binaryHeap.push(1);
    binaryHeap.push(2);
    binaryHeap.push(3);

    Assertions.assertAll(
        () -> Assertions.assertEquals(3, binaryHeap.size())
    );
  }

  @Test
  void testPoll() {
    binaryHeap.push(3);
    binaryHeap.push(2);
    binaryHeap.push(1);

    Assertions.assertAll(
        () -> Assertions.assertEquals(1, (int) binaryHeap.poll()),
        () -> Assertions.assertEquals(2, binaryHeap.size()),
        () -> Assertions.assertEquals(2, (int) binaryHeap.poll()),
        () -> Assertions.assertEquals(1, binaryHeap.size()),
        () -> Assertions.assertEquals(3, (int) binaryHeap.poll()),
        () -> Assertions.assertEquals(0, binaryHeap.size())
    );
  }

  // TODO: test max-heap
}