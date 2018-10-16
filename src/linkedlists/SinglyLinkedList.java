package linkedlists;

public class SinglyLinkedList<T> {
  private Node<T> head;

  SinglyLinkedList() {
    head = new Node(null, null);
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
  public Node<T> get(T data) {
    Node<T> currentNode = head;

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
   * T: O(1)
   *
   * @param insertNode
   */
  public void insert(Node<T> insertNode) {
    if (insertNode != null) {
      insertNode.setNext(head.getNext());
      head.setNext(insertNode);
    }
  }

  /**
   * Deletes given node from list
   *
   * Returns true upon successful deletion, and false otherwise
   *
   * @param deleteData
   * @return
   */
  public boolean delete(T deleteData) {
    if (deleteData == null) {
      return false;
    }

    Node<T> prevNode;
    Node<T> currentNode = head;

    while (currentNode.hasNext()) {
      prevNode = currentNode;
      currentNode = currentNode.getNext();

      if (currentNode.getData().equals(deleteData)) {
        prevNode.setNext(currentNode.getNext());
        currentNode.setNext(null);
        return true;
      }
    }

    return false;
  }

  class Node<T> {
    private T data;
    private Node<T> next;

    Node(T data) {
      this(data, null);
    }

    Node(T data, Node<T> next) {
      if (data == null) {
        throw new NullPointerException();
      }
      this.data = data;
      this.next = next;
    }


    public T getData() {
      return data;
    }

    public void setData(T data) {
      this.data = data;
    }

    public boolean hasNext() {
      return this.next != null;
    }

    public Node<T> getNext() {
      return next;
    }

    public void setNext(Node<T> next) {
      this.next = next;
    }
  }
}
