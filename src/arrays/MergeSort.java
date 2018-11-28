package arrays;

import comparison.SortComparison;

// TODO: make merge both ascending and descending
public class MergeSort<T extends Comparable<T>> implements Sorter<T> {

  @Override
  public CustomArray<T> sort(SortComparison<T, Integer> comparator, CustomArray<T> sortable) {
    if (sortable.size() < 2) {
      return sortable;
    }

    int mid = sortable.size() / 2;
    CustomArray<T> leftArray = sort(comparator, sortable.slice(0, mid));
    CustomArray<T> rightArray = sort(comparator, sortable.slice(mid, sortable.size()));

    return merge(leftArray, rightArray, comparator);
  }

  private CustomArray<T> merge(CustomArray<T> leftArray, CustomArray<T> rightArray,
                               SortComparison<T, Integer> comparator) {
    CustomArray<T> mergedArray = null;

    try {
      mergedArray = new DynamicArray<>(leftArray.size() + rightArray.size());

      // Compare elements of both arrays
      int leftIdx = 0;
      int rightIdx = 0;
      while (leftIdx < leftArray.size() && rightIdx < rightArray.size()) {
        T leftElement = leftArray.get(leftIdx);
        T rightElement = rightArray.get(rightIdx);

        if (comparator.compare(leftElement, rightElement)< 1) {
          mergedArray.add(leftElement);
          leftIdx++;
        } else {
          mergedArray.add(rightElement);
          rightIdx++;
        }
      }

      // Add all elements of array with elements left
      if (leftIdx >= leftArray.size()) {
        for (; rightIdx < rightArray.size(); rightIdx++) {
          mergedArray.add(rightArray.get(rightIdx));
        }
      } else {
        for (; leftIdx < leftArray.size(); leftIdx++) {
          mergedArray.add(leftArray.get(leftIdx));
        }
      }
    } catch (Exception e) {

    }

    return mergedArray;
  }
}
