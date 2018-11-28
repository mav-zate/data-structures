package arrays;

public class ComparableArray<T extends Comparable<T>> extends DynamicArray<T> {

  public ComparableArray() {
    super();
  }

  public ComparableArray(int size) {
    super(size);
  }

  public ComparableArray(T... initialElements) {
    super(initialElements);
  }
}
