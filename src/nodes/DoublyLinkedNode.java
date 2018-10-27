package nodes;

import com.google.common.base.MoreObjects;

public class DoublyLinkedNode<T> {
  private T data;
  private DoublyLinkedNode<T> prev;
  private DoublyLinkedNode<T> next;

  public DoublyLinkedNode(T data) {
    this(data, null, null);
  }

  public DoublyLinkedNode(T data, DoublyLinkedNode<T> prev, DoublyLinkedNode<T> next) {
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

  public DoublyLinkedNode<T> getPrev() {
    return prev;
  }

  public void setPrev(DoublyLinkedNode<T> prev) {
    this.prev = prev;
  }

  public boolean hasPrev() {
    return (getPrev() != null && getPrev().getData() != null);
  }

  public DoublyLinkedNode<T> getNext() {
    return next;
  }

  public void setNext(DoublyLinkedNode<T> next) {
    this.next = next;
  }

  public boolean hasNext() {
    return (getNext() != null && getNext().getData() != null);
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof DoublyLinkedNode)) {
      return false;
    }

    DoublyLinkedNode<T> otherNode = (DoublyLinkedNode<T>) other;

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
      T prevData = getPrev().getData();
      if (prevData != null) {
        if (!(prevData.equals(otherNode.getPrev().getData()))) {
          return false;
        }
      } else {
        if (otherNode.getPrev() == null || otherNode.getPrev().getData() != null) {
          return false;
        }
      }
    } else {
      if (otherNode.getPrev() != null) {
        return false;
      }
    }

    // next
    if (getNext() != null) {
      T nextData = getNext().getData();
      if (nextData != null) {
        if (!(nextData.equals(otherNode.getNext().getData()))) {
          return false;
        }
      } else {
        if (otherNode.getNext() == null || otherNode.getNext().getData() != null) {
          return false;
        }
      }
    } else {
      if (otherNode.getNext() != null) {
        return false;
      }
    }

    return true;
  }

  @Override
  public String toString() {
    T prevData = prev == null ? null : prev.getData();
    T nextData = next == null ? null : next.getData();

    return MoreObjects.toStringHelper(this)
        .add("data", data)
        .add("prev", prevData)
        .add("next", nextData)
        .toString();
  }
}
