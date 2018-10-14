package comparison;

/**
 * Determines sort order. To be passed as parameter to {@link arrays.Sorter}
 *
 * @param <T>
 * @param <Boolean>
 */
public interface SortComparison<T extends Comparable<T>, Boolean> {
  Boolean isSwap(T left, T right);
}
