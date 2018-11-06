package arrays;

import comparison.SortComparison;

/**
 *
 * @param <T>
 */
public class SelectionSort<T extends Comparable<T>> implements Sorter<T> {
  @Override
  public void sort(Sortable<T> sortable, SortComparison<T, Boolean> comparator) {
    for (int i = 0; i < sortable.size() - 1; i++) {
      T min = sortable.get(i);
      int indexOfMin = -1;

      for (int j = i + 1; j < sortable.size(); j++) {
        T currentItem = sortable.get(j);
        if (min.compareTo(currentItem) > 0) {
          min = currentItem;
          indexOfMin = j;
        }
      }

      if (indexOfMin > -1) {
        T temp = sortable.get(i);
        sortable.set(i, min);
        sortable.set(indexOfMin, temp);
      }
    }
  }
}