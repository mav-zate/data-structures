package arrays;

import comparison.SortComparison;

/**
 * Custom interface that all sorting algorithms in this project will implement
 *
 * @param <T>
 */
public interface Sorter<T extends Comparable<T>> {
  /**
   * For in-place sorts
   *
   * @param sortable item to be sorted
   * @param comparator function that determines sort order
   */
  default void sort(CustomArray<T> sortable, SortComparison<T, Boolean> comparator) {
    return;
  }

  /**
   * For out-of-place sorts
   *
   * @param comparator
   * @param sortable
   * @return
   */
  default CustomArray<T> sort(SortComparison<T, Boolean> comparator, CustomArray<T> sortable) {
    return sortable;
  }
}
