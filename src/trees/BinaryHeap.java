package trees;

import java.util.Comparator;

/**
 * Binary Heap that defaults to min-heap if not given {@link Comparator} as constructor argument
 *
 * @param <T>
 */
public class BinaryHeap<T extends Comparable<T>> {
  private Object[] heap;
  private int size;
  private int capacity;
  private Comparator<T> comparator;

  BinaryHeap() {
    this(10);
  }

  BinaryHeap(int initialCapacity) {
    this(initialCapacity, Comparator.naturalOrder());
  }

  BinaryHeap(int initialCapacity, Comparator<T> comparator) {
    if (initialCapacity < 0) {
      initialCapacity = 10;
    }

    this.heap = new Object[initialCapacity];
    this.size = 0;
    this.capacity = initialCapacity;
    this.comparator = comparator;
  }

  /**
   * Returns root (min/max) without deleting it from heap
   *
   * @return
   */
  @SuppressWarnings("unchecked")
  public T peek() {
    return (size > 0) ? (T) heap[0] : null;
  }

  /**
   * Pushes value into appropriate index in heap
   *
   * Worst Case T: O(log n)
   *
   * @param item
   */
  public void push(T item) {
    if (item == null) {
      throw new NullPointerException();
    }

    if (size >= capacity) {
      resize();
    }

    heap[size++] = item;
    siftUp();
  }

  /**
   * Returns root (min/max) of heap and deletes it from heap
   *
   * Worst Case T: O(log n)
   *
   * @return
   */
  @SuppressWarnings("unchecked")
  public T poll() {
    if (size < 1) {
      return null;
    }

    T polledItem = (T) heap[0];
    heap[0] = heap[--size];
    siftDown();
    return polledItem;
  }

  /**
   * Returns size of heap
   *
   * @return
   */
  public int size() {
    return size;
  }

  private void resize() {
    Object[] newHeap = new Object[capacity *= 2];

    for (int i = 0; i < heap.length; i++) {
      newHeap[i] = heap[i];
    }

    heap = newHeap;
  }

  @SuppressWarnings("unchecked")
  private void siftUp() {
    int currentIdx = size - 1;
    int parentIdx = (size - 1) / 2;

    while (parentIdx > -1 &&
        0 < comparator.compare((T) heap[parentIdx], (T) heap[currentIdx]))  {
      swapValues(parentIdx, currentIdx);
      currentIdx = parentIdx;
      parentIdx = (parentIdx - 1) / 2;
    }
  }

  @SuppressWarnings("unchecked")
  private void siftDown() {
    int currentIdx = 0;
    int leftChildIdx = 1;
    int rightChildIdx = 2;

    boolean swapped = true;
    while (leftChildIdx < size && swapped) {
      int maxChildIdx;
      if (rightChildIdx < size) {
        T leftValue = (T) heap[leftChildIdx];
        T rightValue = (T) heap[rightChildIdx];
        maxChildIdx = (leftValue.compareTo(rightValue) < 0) ? rightChildIdx : leftChildIdx;
      } else {
        maxChildIdx = leftChildIdx;
      }

      T currentValue = (T) heap[currentIdx];
      T maxChildValue = (T) heap[maxChildIdx];
      if (currentValue.compareTo(maxChildValue) > 0) {
        swapValues(currentIdx, maxChildIdx);
      } else {
        swapped = false;
      }

      // increment pointers
      currentIdx = maxChildIdx;
      leftChildIdx = 2 * currentIdx + 1;
      rightChildIdx = 2 * currentIdx + 2;
    }
  }

  @SuppressWarnings("unchecked")
  private void swapValues(int idx1, int idx2) {
    T temp = (T) heap[idx1];
    heap[idx1] = heap[idx2];
    heap[idx2] = temp;
  }
}
