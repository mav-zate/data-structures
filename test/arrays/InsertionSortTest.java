package arrays;

import comparison.AscendingOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testutils.ArrayUtils;

class InsertionSortTest {
  InsertionSort<Integer> insertionSort;
  CustomArray<Integer> sortedArray;

  @BeforeEach
  void init() throws Exception {
    insertionSort = new InsertionSort<>();
    sortedArray = ArrayUtils.initAscendingOrderArray(10);
  }

  @Test
  void testSort() throws Exception {
    CustomArray<Integer> inputArray = ArrayUtils.initUnsortedArray(10);
    insertionSort.sort(inputArray, new AscendingOrder<>());

    Assertions.assertEquals(sortedArray, inputArray);
  }
}