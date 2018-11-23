package arrays;

import comparison.AscendingOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testutils.ArrayUtils;

public class QuickSortTest {
  QuickSort<Integer> quickSort;
  CustomArray<Integer> inputArray;

  @BeforeEach
  void init() {
    quickSort = new QuickSort<>();
    inputArray = ArrayUtils.initUnsortedArray(10);
  }

  @Test
  public void testSort() {
    CustomArray<Integer> expectedResult = ArrayUtils.initAscendingOrderArray(10);

    quickSort.sort(inputArray, new AscendingOrder<>());

    Assertions.assertEquals(expectedResult, inputArray);
  }
}