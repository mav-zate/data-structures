package arrays;

public interface Sortable<T> {
  int size();

  T get(int idx);

  void set(int idx, T item);
}
