package linkedlists;

public class DoublyLinkedList<T> {
  private Node<T> head;

  DoublyLinkedList() {
    head = new Node(null);
    Node<T> sentinel = new Node(null);
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
  public Node<T> get(T data) {
    Node<T> currentNode = head;
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
  public void insert(Node<T> insertNode) {
    if (insertNode != null && insertNode.getData() != null) {
      Node<T> currentFirstNode = head.getNext();

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
      Node<T> insertNode = new Node(data);
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

    Node<T> currentNode = head;
    while (currentNode.hasNext()) {
      currentNode = currentNode.getNext();
      if (currentNode.getData() == data) {
        Node<T> prevNode = currentNode.getPrev();
        Node<T> nextNode = currentNode.getNext();

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
    Node<T> currentNode = head;
    while (currentNode.hasNext()) {
      currentNode = currentNode.getNext();

      Node<T> prevNode = currentNode.getPrev();
      Node<T> nextNode = currentNode.getNext();

      nextNode.setPrev(prevNode);
      prevNode.setNext(nextNode);
      return true;
    }

    return false;
  }

  class Node<T> {
    private T data;
    private Node<T> prev;
    private Node<T> next;

    Node(T data) {
      this(data, null, null);
    }

    Node(T data, Node<T> prev, Node<T> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }

    public T getData() {
      return data;
    }

    public void setData(T data) {
      this.data = data;
    }

    public Node<T> getPrev() {
      return prev;
    }

    public void setPrev(Node<T> prev) {
      this.prev = prev;
    }

    public boolean hasPrev() {
      return (getPrev() != null && getPrev().getData() != null);
    }

    public Node<T> getNext() {
      return next;
    }

    public void setNext(Node<T> next) {
      this.next = next;
    }

    public boolean hasNext() {
      return (getNext() != null && getNext().getData() != null);
    }

    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Node)) {
        return false;
      }

      Node<T> otherNode = (Node<T>) other;

      // data
      if (getData() != null) {
        if (!getData().equals(otherNode.getData())) {
          return false;
        }
      } else {
        if (otherNode.getData() != null) {
          return false;
        }
      }

      // prev
      if (getPrev() != null) {
        if (!getPrev().equals(otherNode.getPrev())) {
          return false;
        }
      } else {
        if (otherNode.getPrev() != null) {
          return false;
        }
      }

      // next
      if (getNext() != null) {
        if (!getNext().equals(otherNode.getNext())) {
          return false;
        }
      } else {
        if (otherNode.getNext() != null) {
          return false;
        }
      }

      return true;
    }
  }
}
