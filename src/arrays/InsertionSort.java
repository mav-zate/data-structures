package arrays;

import comparison.SortComparison;

/**
 * In-place insertion sort
 *
 * @param <T>
 */
public class InsertionSort<T extends Comparable<T>> implements Sorter<T> {
  @Override
  public void sort(CustomArray<T> sortable, SortComparison<T, Integer> comparator) {
    for (int unsortedIdx = 0; unsortedIdx < sortable.size(); unsortedIdx++) {
      int sortedIdx = unsortedIdx;
      while (sortedIdx > 0 && comparator.compare(sortable.get(sortedIdx - 1), sortable.get(sortedIdx)) > 0) {
        T leftItem = sortable.get(sortedIdx - 1);
        T rightItem = sortable.get(sortedIdx);
        sortable.set(sortedIdx - 1, rightItem);
        sortable.set(sortedIdx, leftItem);
        sortedIdx--;
      }
    }
  }
}
