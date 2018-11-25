package arrays;

import comparison.SortComparison;

public class QuickSort<T extends Comparable<T>> implements Sorter<T> {
  @Override
  public void sort(CustomArray<T> sortable, SortComparison<T, Integer> comparator) {
    if (sortable.size() <= 1) {
      return;
    } else {
      quickSort(sortable, 0, 9, comparator);
    }
  }

  private void quickSort(CustomArray<T> array, int leftBound, int rightBound, SortComparison<T, Integer> comparator) {
    int pivotIdx = rightBound;
    int leftPointer = leftBound;
    int rightPointer = rightBound - 1;
    T pivotItem = array.get(pivotIdx);

    while (true) {
      while (comparator.compare(array.get(leftPointer), pivotItem) < 0 && leftPointer < rightPointer) {
        leftPointer++;
      }

      while (comparator.compare(array.get(rightPointer), pivotItem) > 0 && leftPointer < rightPointer) {
        rightPointer--;
      }

      if (leftPointer != rightPointer) {
        swap(leftPointer, rightPointer, array);
      } else {
        if (array.get(leftPointer).compareTo(pivotItem) > 0) {
          swap(leftPointer, pivotIdx, array);
        }
        break;
      }
    }



    if (leftPointer - leftBound > 1) {
      quickSort(array, leftBound, leftPointer, comparator);
    }
    if (rightBound - leftPointer > 1) {
      quickSort(array, leftPointer + 1, rightBound, comparator);
    }
  }

  private void swap(int idx1, int idx2, CustomArray<T> array) {
    T tempItem = array.get(idx1);
    array.set(idx1, array.get(idx2));
    array.set(idx2, tempItem);
  }
}
