package stacksqueues;

import nodes.DoublyLinkedNode;

import java.util.EmptyStackException;


/**
 * Queue backed by linked list
 *
 * @param <T>
 */
public class Queue<T> {
  private DoublyLinkedNode<T> backSentinel;
  private DoublyLinkedNode<T> frontSentinel;
  private int size;


  public Queue() {
    backSentinel = new DoublyLinkedNode<>(null);
    backSentinel.setPrev(null);

    frontSentinel = new DoublyLinkedNode<>(null);
    frontSentinel.setNext(null);

    backSentinel.setNext(frontSentinel);
    backSentinel.setPrev(backSentinel);

    size = 0;
  }

  /**
   * Adds non-null item to front of queue
   *
   * @param item
   * @throws NullPointerException
   */
  public void enqueue(T item) throws NullPointerException {
    if (item == null) {
      throw new NullPointerException();
    }

    DoublyLinkedNode<T> back = backSentinel.getNext();

    DoublyLinkedNode<T> newBack = new DoublyLinkedNode<>(item, backSentinel, back);
    backSentinel.setNext(newBack);
    back.setPrev(newBack);

    size++;
  }

  /**
   * Returns item at front of queue if available
   *
   * @return
   * @throws EmptyStackException
   */
  public T dequeue() throws EmptyStackException {
    if (size < 1) {
      throw new EmptyStackException();
    }

    DoublyLinkedNode<T> front = frontSentinel.getPrev();

    // reset links
    DoublyLinkedNode<T> frontMinusOne = front.getPrev();
    frontMinusOne.setNext(frontSentinel);
    frontSentinel.setPrev(frontMinusOne);

    size--;
    return front.getData();
  }

  /**
   * Returns size of queue
   *
   * @return
   */
  public int size() {
    return size;
  }

  // TODO: add equals method

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Queue: {");

    DoublyLinkedNode<T> currentNode = backSentinel;
    while (currentNode.hasNext()) {
      currentNode = currentNode.getNext();

      sb.append(currentNode.getData());
      sb.append(", ");
    }
    sb.delete(sb.length() - 2, sb.length());
    sb.append("}");

    return sb.toString();
  }
}
