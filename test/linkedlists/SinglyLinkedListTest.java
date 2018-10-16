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

  @Nested
  @DisplayName("When has values")
  class NotEmptyList {

    @BeforeEach
    void init() {
      linkedList = new SinglyLinkedList<>();
      linkedList.insert(3);
      linkedList.insert(2);
      linkedList.insert(1);
    }

    @Test
    public void containsTest() {
      Assertions.assertAll(() -> {
        Assertions.assertEquals(false, linkedList.contains(5));
        Assertions.assertEquals(true, linkedList.contains(2));
      });
    }

    @Test
    public void getTest() {
      SinglyLinkedList.Node result = linkedList.get(2);
      Assertions.assertEquals(2, result.getData());
    }

    @Test
    @DisplayName("insert preserves order of insert")
    public void insertTest() {
      SinglyLinkedList.Node firstNode = linkedList.new Node<>(3, null);
      SinglyLinkedList.Node secondNode = linkedList.new Node<>(2, firstNode);
      SinglyLinkedList.Node thirdNode = linkedList.new Node<>(1, secondNode);


      Assertions.assertAll(() -> {
        Assertions.assertEquals(firstNode, linkedList.get(3));
        Assertions.assertEquals(secondNode, linkedList.get(2));
        Assertions.assertEquals(thirdNode, linkedList.get(1));
      });
    }

    @Test
    public void deleteTest() {
      SinglyLinkedList.Node firstNodePostDeletion = linkedList.new Node<>(3, null);
      SinglyLinkedList.Node secondNodePostDeletion =
          linkedList.new Node<>(1, firstNodePostDeletion);

      boolean gotDeleted = linkedList.delete(2);
      Assertions.assertAll(() -> {
        Assertions.assertEquals(true, gotDeleted);
        Assertions.assertEquals(firstNodePostDeletion, secondNodePostDeletion.getNext());
      });
    }
  }
}