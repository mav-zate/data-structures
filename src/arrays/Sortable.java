package arrays;

/**
 * Wrapper for data structures that can be sorted by a comparison-ssort algorithm
 *
 * @param <T>
 */
public interface Sortable<T extends Comparable<T>> {
  int size();

  T get(int idx);

  void set(int idx, T item);
}
