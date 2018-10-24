package stacksqueues;

import org.junit.jupiter.api.*;

import java.util.EmptyStackException;

class StackTest {
  Stack<Integer> testSubject;

  @Nested
  @DisplayName("When empty")
  class EmptyStack {
    @BeforeEach
    void init() {
      testSubject = new Stack<>();
    }

    @Test
    @DisplayName("pop throws exception")
    void testPop() {
      Assertions.assertThrows(EmptyStackException.class, () -> testSubject.pop());
    }

    @Test
    @DisplayName("peek returns null")
    void testPeek() {
      Assertions.assertEquals(null, testSubject.peek());
    }
  }

  @Nested
  @DisplayName("When at capacity")
  class FullStack {
    @BeforeEach
    void init() {
      testSubject = new Stack<>(1);
    }

    @Test
    @DisplayName("Stack resizes")
    void testPush() {
      testSubject.push(1);
      testSubject.push(2);
      Assertions.assertEquals(2, testSubject.size());
    }

    @Test
    @DisplayName("pop respects LIFO")
    void testPop() throws EmptyStackException {
      testSubject.push(1);
      testSubject.push(2);
      testSubject.push(3);

      Assertions.assertEquals(3, (int) testSubject.pop());
      Assertions.assertEquals(2, (int) testSubject.pop());
      Assertions.assertEquals(1, (int) testSubject.pop());
    }

    @Test
    void testPeek() {
      testSubject.push(1);
      Assertions.assertEquals(1, (int) testSubject.peek());

      testSubject.push(2);
      Assertions.assertEquals(2, (int) testSubject.peek());

      testSubject.push(3);
      Assertions.assertEquals(3, (int) testSubject.peek());

    }
  }
}