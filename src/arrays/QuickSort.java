package arrays;

import comparison.SortComparison;

public class QuickSort<T extends Comparable<T>> implements Sorter<T> {
  @Override
  public void sort(CustomArray<T> sortable, SortComparison<T, Boolean> comparator) {
    int pivotIdx = sortable.size() - 1;
    quickSort(sortable, 0, pivotIdx - 1, pivotIdx);
  }

  private void quickSort(CustomArray<T> array, int leftPointer, int rightPointer, int pivotIdx) {
    int leftPointerStart = leftPointer;
    int rightPointerStart = rightPointer;
    T pivotItem = array.get(pivotIdx);

    while (leftPointer < rightPointer) {
      while (array.get(leftPointer).compareTo(pivotItem) < 1) {
        leftPointer++;
      }

      while (array.get(rightPointer).compareTo(pivotItem) > 0 && leftPointer < rightPointer) {
        rightPointer--;
      }

      if (leftPointer != rightPointer) {
        swap(leftPointer, rightPointer, array);
      }
    }

    swap(leftPointer, pivotIdx, array);

    if (leftPointer - leftPointerStart < 2) {
      quickSort(array, leftPointerStart, leftPointer - 2, leftPointer - 1);
    }
    if (rightPointerStart - rightPointer < 2) {
      quickSort(array, rightPointer + 1, rightPointerStart - 1, rightPointerStart);
    }
  }

  private void swap(int idx1, int idx2, CustomArray<T> array) {
    T tempItem = array.get(idx1);
    array.set(idx1, array.get(idx2));
    array.set(idx2, tempItem);
  }
}
