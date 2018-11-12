package arrays;

public interface CustomArray<T> {
  T get(int idx);

  void insert(int idx, T item);

  void set(int idx, T item);

  void add(T item);

  T delete(int idx);

  void deleteAll();

  int size();

  CustomArray<T> slice(int startIdx, int endIdx);
}
