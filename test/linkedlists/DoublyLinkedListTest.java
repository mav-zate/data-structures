package linkedlists;

import org.junit.jupiter.api.*;

class DoublyLinkedListTest {
  public DoublyLinkedList<Integer> linkedList;

  @Nested
  @DisplayName("When empty")
  class EmptyList {

    @BeforeEach
    void init() {
      linkedList = new DoublyLinkedList<>();
    }

    @Test
    public void containsTest() {
      boolean result = linkedList.contains(1);
      Assertions.assertEquals(false, result);
    }

    @Test
    public void getTest() {
      DoublyLinkedList.Node result = linkedList.get(1);
      Assertions.assertEquals(null, result);
    }

    @Test
    public void insertTest() {
      DoublyLinkedList.Node insertNode = linkedList.new Node<>(1);
      DoublyLinkedList.Node head = linkedList.new Node<>(null, null, insertNode);
      DoublyLinkedList.Node sentinel = linkedList.new Node<>(null, insertNode, null);
      linkedList.insert(insertNode);

      System.out.println(head);
      Assertions.assertAll(() -> {
        Assertions.assertEquals(true, linkedList.contains(1));
        Assertions.assertEquals(head, insertNode.getPrev());
        Assertions.assertEquals(sentinel, insertNode.getNext());
      });
    }

    @Test
    public void deleteTest() {
      boolean gotDeletedWithArg = linkedList.delete(1);
      boolean gotDeletedNoArg = linkedList.delete(1);

      Assertions.assertAll(() -> {
        Assertions.assertEquals(false, gotDeletedWithArg);
        Assertions.assertEquals(false, gotDeletedNoArg);
      });
    }
  }

  @Nested
  @DisplayName("When has values")
  class NotEmptyList {

    @BeforeEach
    void init() {
      linkedList = new DoublyLinkedList<>();
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
      DoublyLinkedList.Node result = linkedList.get(2);

      DoublyLinkedList.Node head = linkedList.new Node<>(null, null, null);
      DoublyLinkedList.Node sentinel = linkedList.new Node<>(null, null, null);

      DoublyLinkedList.Node firstInsert = linkedList.new Node<>(3, result, sentinel);
      DoublyLinkedList.Node lastInsert = linkedList.new Node<>(1, head, result);

      head.setNext(lastInsert);
      sentinel.setPrev(firstInsert);

      Assertions.assertAll(() -> {
        Assertions.assertEquals(2, result.getData());
        Assertions.assertEquals(lastInsert, result.getPrev());
        Assertions.assertEquals(firstInsert, result.getNext());
      });
    }

    @Test
    @DisplayName("insert preserves order of insert")
    public void insertTest() {
      DoublyLinkedList.Node head = linkedList.new Node<>(null, null, null);
      DoublyLinkedList.Node sentinel = linkedList.new Node<>(null, null, null);

      DoublyLinkedList.Node firstInsertNode = linkedList.new Node<>(3, null, null);
      DoublyLinkedList.Node secondInsertNode = linkedList.new Node<>(2, null, null);
      DoublyLinkedList.Node thirdInsertNode = linkedList.new Node<>(1, null, null);

      head.setNext(thirdInsertNode);

      thirdInsertNode.setPrev(head);
      thirdInsertNode.setNext(secondInsertNode);

      secondInsertNode.setPrev(thirdInsertNode);
      secondInsertNode.setNext(firstInsertNode);

      firstInsertNode.setPrev(secondInsertNode);
      firstInsertNode.setNext(sentinel);

      sentinel.setPrev(firstInsertNode);


      DoublyLinkedList.Node node1 = linkedList.get(1);
      DoublyLinkedList.Node node2 = linkedList.get(2);
      DoublyLinkedList.Node node3 = linkedList.get(3);

      Assertions.assertAll(() -> {
        Assertions.assertEquals(thirdInsertNode, node1);
        Assertions.assertEquals(head, node1.getPrev());
        Assertions.assertEquals(secondInsertNode, node1.getNext());

        Assertions.assertEquals(secondInsertNode, node2);
        Assertions.assertEquals(thirdInsertNode, node2.getPrev());
        Assertions.assertEquals(firstInsertNode, node2.getNext());

        Assertions.assertEquals(firstInsertNode, node3);
        Assertions.assertEquals(secondInsertNode, node3.getPrev());
        Assertions.assertEquals(sentinel, node3.getNext());
      });
    }

    @Test
    public void deleteTest() {
      DoublyLinkedList.Node head = linkedList.new Node<>(null, null, null);
      DoublyLinkedList.Node sentinel = linkedList.new Node<>(null, null, null);

      DoublyLinkedList.Node firstNodePostDeletion = linkedList.new Node<>(3, null, null);
      DoublyLinkedList.Node secondNodePostDeletion = linkedList.new Node<>(1, null, null);

      head.setNext(secondNodePostDeletion);
      secondNodePostDeletion.setPrev(head);
      secondNodePostDeletion.setNext(firstNodePostDeletion);

      firstNodePostDeletion.setPrev(secondNodePostDeletion);
      firstNodePostDeletion.setNext(sentinel);

      sentinel.setPrev(firstNodePostDeletion);

      boolean gotDeleted = linkedList.delete(2);
      Assertions.assertAll(() -> {
        Assertions.assertEquals(true, gotDeleted);

        Assertions.assertEquals(secondNodePostDeletion, firstNodePostDeletion.getPrev());
        Assertions.assertEquals(firstNodePostDeletion, secondNodePostDeletion.getNext());
      });
    }
  }
}