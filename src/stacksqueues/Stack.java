package stacksqueues;

import java.util.EmptyStackException;

/**
 * Stack backed by array that grows and shrinks when ({@link #size} >= {@link #capacity}) and
 * ({@link #size} <= {@link #capacity}), respectively
 *
 * Push and pop operations could be constant time if backed by singly linked list
 *
 * @param <T>
 */
public class Stack<T> {
  private Object[] store;
  private int capacity;
  private int size;

  public Stack() {
    this(10);
  }

  public Stack(int capacity) {
    store = new Object[capacity];
    this.capacity = capacity;
    this.size = 0;
  }

  /**
   * Retrieves last inserted item
   * T: O(1) (amortized)
   *
   * @return
   * @throws EmptyStackException
   */
  public T pop() throws EmptyStackException {
    if (size < 1) {
      throw new EmptyStackException();
    }

    if (size <= (capacity / 4)) {
      shrinkStore();
    }

    return (T) store[--size];
  }

  /**
   * Inserts item
   * T: O(1) (amortized)
   *
   * @param item
   * @throws NullPointerException
   */
  public void push(T item) throws NullPointerException {
    if (item == null) {
      throw new NullPointerException();
    }

    if (size >= capacity) {
      growStore();
    }

    store[size++] = item;
  }

  /**
   * Returns top element of stack if full. If empty, returns null.
   *
   * @return
   */
  public T peek() {
    return (size > 0) ? (T) store[size - 1] : null;
  }

  /**
   * Returns size of stack
   *
   * @return
   */
  public int size() {
    return size;
  }

  private void growStore() {
    Object[] newStore = new Object[capacity * 2];
    capacity *= 2;

    for (int idx = 0; idx < size; idx++) {
      newStore[idx] = store[idx];
    }

    store = newStore;
  }

  private void shrinkStore() {
    Object[] newStore = new Object[capacity / 2];
    capacity /= 2;

    for (int idx = 0; idx < size; idx++) {
      newStore[idx] = store[idx];
    }

    store = newStore;
  }
}
