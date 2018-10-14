package arrays;

import comparison.SortComparison;

/**
 * Custom interface that all sorting algorithms in this project will implement
 *
 * @param <T>
 */
public interface Sorter<T extends Comparable<T>> {
  /**
   * Takes item
   *
   * @param sortable item to be sorted
   * @param comparator function that determines sort order
   */
  void sort(Sortable<T> sortable, SortComparison<T, Boolean> comparator);
}
