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
  public void sort(CustomArray<T> array, SortComparison<T, Integer> comparator) {
    boolean notSorted = true;

    int length = array.size();
    while (notSorted) {
      boolean noSwap = true;

      for (int idx = 0; idx < length - 1; idx++) {
        T left = array.get(idx);
        T right = array.get(idx + 1);
        if (comparator.compare(left, right) > 0) {
          array.set(idx, right);
          array.set(idx + 1, left);
          noSwap = false;
        }
      }
      length--;

      if (noSwap) {
        notSorted = false;
      }
    }
  }
}
