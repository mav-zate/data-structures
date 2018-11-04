package arrays;

/**
 * Implementation of dynamic array backed by a static array
 */
public class DynamicArray<T extends Comparable<T>> implements CustomArray<T>, Sortable<T> {
  private Object[] internalArray;
  private int actualSize;
  private int maxSize;

  public DynamicArray(int size) throws Exception {
    if (size < 1) {
      throw new Exception("Array cannot have negative length");
    }

    internalArray = new Object[size];
    maxSize = size;
    actualSize = 0;
  }

  /**
   * T: O(1)
   *
   * @param idx
   * @return
   * @throws ArrayIndexOutOfBoundsException
   */
  @Override
  @SuppressWarnings("unchecked")
  public T get(int idx) throws ArrayIndexOutOfBoundsException {
    if (outOfArrayBounds(idx)) {
      throw new ArrayIndexOutOfBoundsException("Array does not contain index: " + idx);
    }

    return (T) internalArray[idx];
  }

  /**
   * Amortized: O(1), Best case: O(1), Worse Case: O(n)
   *
   * @param idx
   * @param item
   * @throws ArrayIndexOutOfBoundsException
   */
  @Override
  @SuppressWarnings("unchecked")
  public void insert(int idx, T item) throws ArrayIndexOutOfBoundsException {
    if (idx < 0 || idx > actualSize) {
      throw new ArrayIndexOutOfBoundsException("Cannot insert...index outside of array bounds");
    }

    if (actualSize >= maxSize) {
      growInternalArray();
    }

    T prevVal = item;
    T current;
    for (int i = idx; i < actualSize + 1; i++) {
      current = (T) internalArray[i];
      internalArray[i] = prevVal;
      prevVal = current;
    }
    actualSize++;
  }

  @Override
  public void set(int idx, T item) throws ArrayIndexOutOfBoundsException {
    if (outOfArrayBounds(idx)) {
      throw new ArrayIndexOutOfBoundsException();
    }

    internalArray[idx] = item;
  }

  /**
   * Amortized: O(1), Best Case: O(1), Worst Case: O(n)
   *
   * @param item
   */
  public void add(T item) {
    insert(actualSize, item);
  }

  /**
   * T: O(n)
   *
   * @param idx
   * @return
   * @throws ArrayIndexOutOfBoundsException
   */
  @SuppressWarnings("unchecked")
  @Override
  public T delete(int idx) throws ArrayIndexOutOfBoundsException {
    if (outOfArrayBounds(idx)) {
      throw new ArrayIndexOutOfBoundsException("Cannot delete...index outside of array bounds");
    }

    T deletedValue = (T) internalArray[idx];
    internalArray[idx] = 0;
    for (int i = idx + 1; i < actualSize; i++) {
      internalArray[i - 1] = internalArray[i];
    }
    actualSize--;

    // TODO: shift values right of deletion left one

    return deletedValue;
  }

  /**
   * T: O(n)
   */
  @Override
  public void deleteAll() {
    for (int i = actualSize - 1; i > 0; i--) {
      internalArray[i] = 0;
    }

    actualSize = 0;
  }

  /**
   * O(1)
   *
   * @return
   */
  public int size() {
    return actualSize;
  }

  private void growInternalArray() {
    Object[] newArray = new Object[2 * maxSize];
    maxSize *= 2;

    for (int i = actualSize - 1; i > -1; i--) {
      newArray[i] = internalArray[i];
    }

    internalArray = newArray;
  }

  private boolean outOfArrayBounds(int idx) {
    return idx < 0 || idx >= actualSize;
  }

  // TODO: polish string method
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("DynamicArray: [");
    for (int i = 0; i < actualSize; i++) {
      sb.append(internalArray[i] + ", ");
    }
    sb.append("]");
    return sb.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof DynamicArray)) {
      return false;
    }

    DynamicArray<T> that = (DynamicArray) other;
    for (int i = 0; i < actualSize; i++) {
      T thisItem = (T) internalArray[i];
      T thatItem = that.get(i);
      if (!thisItem.equals(thatItem)) {
        return false;
      }
    }

    return true;
  }
}
