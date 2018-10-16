package linkedlists;

import org.junit.jupiter.api.*;


class SinglyLinkedListTest {
  public SinglyLinkedList<Integer> linkedList;

  @Nested
  @DisplayName("When empty")
  class EmptyList {

    @BeforeEach
    void init() {
      linkedList = new SinglyLinkedList<>();
    }

    @Test
    public void containsTest() {
      boolean result = linkedList.contains(1);
      Assertions.assertEquals(false, result);
    }

    @Test
    public void getTest() {
      SinglyLinkedList.Node result = linkedList.get(1);
      Assertions.assertEquals(null, result);
    }

    @Test
    public void insertTest() {
      SinglyLinkedList.Node insertNode = linkedList.new Node<>(1);
      linkedList.insert(insertNode);

      Assertions.assertEquals(true, linkedList.contains(1));
    }

    @Test
    public void deleteTest() {
      boolean gotDeleted = linkedList.delete(1);

      Assertions.assertEquals(false, gotDeleted);
    }
  }
}