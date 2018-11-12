package arrays;

import comparison.AscendingOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testutils.ArrayUtils;

class SelectionSortTest {
  SelectionSort<Integer> selectionSort;
  CustomArray<Integer> sortedArray;
  CustomArray<Integer> inputArray;

  @BeforeEach
  void init() throws Exception {
    selectionSort = new SelectionSort<>();
    sortedArray = ArrayUtils.initAscendingOrderArray(10);
    inputArray = ArrayUtils.initUnsortedArray(10);
  }

  @Test
  void testSort() {
    selectionSort.sort(inputArray, new AscendingOrder<>());
    Assertions.assertEquals(sortedArray, inputArray);
  }
}