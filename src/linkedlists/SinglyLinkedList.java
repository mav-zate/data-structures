package linkedlists;

import nodes.SinglyLinkedNode;
import java.util.EmptyStackException;

public class SinglyLinkedList<T> {
  private SinglyLinkedNode<T> head;
  private int size;

  public SinglyLinkedList() {
    head = new SinglyLinkedNode<>(null, null);
    size = 0;
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
    return get(data) != null;
  }

  /**
   * Returns node for given data or null if not found
   *
   * T: O(n)
   *
   * @param data
   * @return
   */
  public SinglyLinkedNode<T> get(T data) {
    if (data == null) {
      return null;
    }

    SinglyLinkedNode<T> currentNode = head;

    while (currentNode.hasNext()) {
      currentNode = currentNode.getNext();
      T currentData = currentNode.getData();
      if (currentData.equals(data)) {
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
  public void insert(SinglyLinkedNode<T> insertNode) {
    if (insertNode != null && insertNode.getData() != null) {
      insertNode.setNext(head.getNext());
      head.setNext(insertNode);
      size++;
    }
  }

  /**
   * Overloaded method to allow data insert
   *
   * cannot insert null
   *
   * @param data
   */
  public void insert(T data) {
    if (data != null) {
      SinglyLinkedNode<T> insertNode = new SinglyLinkedNode<>(data);
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
   * @param deleteData
   * @return
   */
  public boolean delete(T deleteData) {
    if (deleteData == null) {
      return false;
    }

    SinglyLinkedNode<T> prevNode;
    SinglyLinkedNode<T> currentNode = head;

    while (currentNode.hasNext()) {
      prevNode = currentNode;
      currentNode = currentNode.getNext();

      if (currentNode.getData().equals(deleteData)) {
        prevNode.setNext(currentNode.getNext());
        currentNode.setNext(null);
        size--;
        return true;
      }
    }

    return false;
  }

  /**
   * Return value at head of linked list while removing it.
   *
   * Throws {@link EmptyStackException} in case the stack is empty
   *
   * @return
   */
  // TODO: test pop method
  public T pop() {
    if (size < 1) {
      throw new EmptyStackException();
    }

    SinglyLinkedNode<T> nodeToPop = head.getNext();
    head.setNext(nodeToPop.getNext());
    size--;
    return nodeToPop.getData();
  }

  /**
   * Returns size of linked list
   *
   * @return
   */
  public int size() {
    return size;
  }

  /**
   * Returns the list head, {@link SinglyLinkedNode}, to allow iteration
   *
   * @return
   */
  public SinglyLinkedNode<T> getIterator() {
    return head;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("SinglyLinkedList: [");
    SinglyLinkedNode currentNode = head;
    while (currentNode.hasNext()) {
      currentNode = currentNode.getNext();
      sb.append(currentNode + ", ");
    }
    sb.delete(sb.length() - 2, sb.length());
    sb.append("]");

    return sb.toString();
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }

    SinglyLinkedList<T> other = (SinglyLinkedList<T>) obj;

    if (size() != other.size()) {
      return false;
    }

    SinglyLinkedNode thisNode = getIterator();
    SinglyLinkedNode thatNode = other.getIterator();
    while (thisNode.hasNext() && thatNode.hasNext()) {
      thisNode = thisNode.getNext();
      thatNode = thatNode.getNext();

      if (!(thisNode.equals(thatNode))) {
        return false;
      }
    }

    return true;
  }
}
