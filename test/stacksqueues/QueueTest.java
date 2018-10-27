package stacksqueues;

import org.junit.jupiter.api.*;

import java.util.EmptyStackException;

class QueueTest {
  Queue<Integer> testSubject;

  @BeforeEach
  void init() {
    testSubject = new Queue<>();
  }

  @Test
  @DisplayName("preserves FIFO")
  void testFifo() {
    testSubject.enqueue(1);
    testSubject.enqueue(2);
    testSubject.enqueue(3);
    testSubject.enqueue(4);

    Assertions.assertAll(
        () -> Assertions.assertEquals(1, (int) testSubject.dequeue()),
        () -> Assertions.assertEquals(2, (int) testSubject.dequeue()),
        () -> Assertions.assertEquals(3, (int) testSubject.dequeue()),
        () -> Assertions.assertEquals(4, (int) testSubject.dequeue())
    );
  }

  @Test
  @DisplayName("prohibits null values")
  void testNoNullEnqueue() {
    Assertions.assertThrows(NullPointerException.class, () -> testSubject.enqueue(null));
  }

  @Nested
  @DisplayName("When empty")
  class EmptyQueue {

    @BeforeEach
    void init() {
      testSubject = new Queue<>();
    }

    @Test
    @DisplayName("dequeue throws exception")
    void testDequeue() {
      Assertions.assertThrows(EmptyStackException.class, () -> testSubject.dequeue());
    }
  }
}