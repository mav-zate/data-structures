package linkedlists;

import nodes.DoublyLinkedNode;

public class DoublyLinkedList<T> {
  private DoublyLinkedNode<T> head;

  DoublyLinkedList() {
    head = new DoublyLinkedNode<>(null);
    DoublyLinkedNode<T> sentinel = new DoublyLinkedNode<>(null);
    head.setNext(sentinel);
    sentinel.setPrev(null);
  }

  /**
   * Checks if data is inside linkedList
   *
   * T: O(n)
   *
   * @param data
   * @return
   */
  public boolean contains(T data) {
    return (get(data) != null);
  }

  /**
   * Returns node for given data or null if not found
   *
   * T: O(n)
   *
   * @param data
   * @return
   */
  public DoublyLinkedNode<T> get(T data) {
    DoublyLinkedNode<T> currentNode = head;
    while (currentNode.hasNext()) {
      currentNode = currentNode.getNext();
      if (currentNode.getData() == data) {
        return currentNode;
      }
    }

    return null;
  }


  /**
   * inserts node after head of list
   *
   * cannot insert null or node with null data
   *
   * T: O(1)
   *
   * @param insertNode
   */
  public void insert(DoublyLinkedNode<T> insertNode) {
    if (insertNode != null && insertNode.getData() != null) {
      DoublyLinkedNode<T> currentFirstNode = head.getNext();

      currentFirstNode.setPrev(insertNode);
      insertNode.setNext(currentFirstNode);
      insertNode.setPrev(head);
      head.setNext(insertNode);
    }
  }

  /**
   * Overloaded method to allow data insert
   *
   * cannot insert null
   *
   * T: O(1)
   *
   * @param data
   */
  public void insert(T data) {
    if (data != null) {
      DoublyLinkedNode<T> insertNode = new DoublyLinkedNode<>(data);
      insert(insertNode);
    }
  }

  /**
   * Deletes given node from list
   *
   * Returns true upon successful deletion, and false otherwise
   *
   * T: O(n)
   *
   * @param data
   * @return
   */
  public boolean delete(T data) {
    if (data == null) {
      return false;
    }

    DoublyLinkedNode<T> currentNode = head;
    while (currentNode.hasNext()) {
      currentNode = currentNode.getNext();
      if (currentNode.getData() == data) {
        DoublyLinkedNode<T> prevNode = currentNode.getPrev();
        DoublyLinkedNode<T> nextNode = currentNode.getNext();

        nextNode.setPrev(prevNode);
        prevNode.setNext(nextNode);
        return true;
      }
    }

    return false;
  }

  /**
   * Deletes first node from list
   *
   * returns true if list non-empty, false otherwise
   *
   * @return
   */
  public boolean delete() {
    DoublyLinkedNode<T> currentNode = head;
    while (currentNode.hasNext()) {
      currentNode = currentNode.getNext();

      DoublyLinkedNode<T> prevNode = currentNode.getPrev();
      DoublyLinkedNode<T> nextNode = currentNode.getNext();

      nextNode.setPrev(prevNode);
      prevNode.setNext(nextNode);
      return true;
    }

    return false;
  }
}
