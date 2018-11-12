package arrays;

import comparison.AscendingOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testutils.ArrayUtils;

class MergeSortTest {
  MergeSort<Integer> mergeSort;
  CustomArray<Integer> sortedArray;

  @BeforeEach
  void init() throws Exception {
    mergeSort = new MergeSort<>();
    sortedArray = ArrayUtils.initAscendingOrderArray(10);
  }

  @Test
  void testSort() throws Exception {
    CustomArray<Integer> inputArray = ArrayUtils.initUnsortedArray(10);
    CustomArray<Integer> resultArray = mergeSort.sort(new AscendingOrder<>(), inputArray);

    Assertions.assertEquals(sortedArray, resultArray);
  }
}