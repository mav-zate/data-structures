package arrays;


import comparison.SortComparison;

/**
 *
 *
 * Worst Case:
 *   T: O(n^2)
 * Best Case (nearly sorted):
 *   T: O(n)
 *
 * S: O(1)
 */
public class BubbleSort<T extends Comparable<T>> implements Sorter<T> {
  public BubbleSort() {
  }

  @Override
  public void sort(Sortable<T> array, SortComparison<T, Boolean> comparator) {
    boolean notSorted = true;

    int length = array.size();
    while (notSorted) {
      for (int idx = 0; idx < length - 1; idx++) {
        T left = array.get(idx);
        T right = array.get(idx + 1);
        if (comparator.isSwap(left, right)) {
          array.set(idx, right);
          array.set(idx + 1, left);
        }
      }

      length--;
    }
  }
}
