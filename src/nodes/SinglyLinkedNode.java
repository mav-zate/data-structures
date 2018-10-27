package nodes;

import com.google.common.base.MoreObjects;

public class SinglyLinkedNode<T> {
  private T data;
  private SinglyLinkedNode<T> next;

  public SinglyLinkedNode(T data) {
    this(data, null);
  }

  public SinglyLinkedNode(T data, SinglyLinkedNode<T> next) {
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
    return (this.next != null && this.next.data != null);
  }

  public SinglyLinkedNode<T> getNext() {
    return next;
  }

  public void setNext(SinglyLinkedNode<T> next) {
    this.next = next;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof SinglyLinkedNode)) {
      return false;
    }

    SinglyLinkedNode<T> otherNode = (SinglyLinkedNode) other;

    if (getData() != null) {
      if (!(getData().equals(otherNode.getData()))) {
        return false;
      }
    } else {
      if (otherNode.getData() != null) {
        return false;
      }
    }

    if (getNext() != null) {
      if (!(getNext().equals(otherNode.getNext()))) {
        return false;
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
     T nextData = (next == null) ? null : next.getData();

    return MoreObjects.toStringHelper(this)
        .add("data", data)
        .add("next", nextData)
        .toString();
  }
}
