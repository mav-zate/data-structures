package comparison;

/**
 * Determines sort order. To be passed as parameter to {@link arrays.Sorter}
 *
 * @param <T>
 * @param <Integer>
 */
public interface SortComparison<T extends Comparable<T>, Integer> {
  Integer compare(T left, T right);
}
