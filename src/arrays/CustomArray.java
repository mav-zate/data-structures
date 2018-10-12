package arrays;

public interface CustomArray<T> {
  T get(int idx);

  void insert(int idx, T item);

  void set(int idx, T item);

  T delete(int idx);

  void deleteAll();

  int size();
}
