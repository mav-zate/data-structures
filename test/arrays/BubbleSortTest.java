package arrays;

import comparison.AscendingOrder;
import comparison.DescendingOrder;
import comparison.SortComparison;
import org.junit.jupiter.api.*;
import testutils.ArrayUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {
  BubbleSort<Integer> bubbleSort;

  @BeforeEach
  void init() throws Exception {
    bubbleSort = new BubbleSort<>();
  }

  @Nested
  @DisplayName("Ascending Order")
  class AscendingOrderTest {
    SortComparison<Integer, Boolean> sortComparison;
    Sortable<Integer> actualResult;
    Sortable<Integer> expectedResult;

    @BeforeEach
    void init() throws Exception {
      sortComparison = new AscendingOrder();
      actualResult = ArrayUtils.initUnsortedArray(10);
      expectedResult = ArrayUtils.initAscendingOrderArray(10);
    }

    @Test
    @DisplayName("::sort")
    void sortTest() {
      System.out.println(expectedResult);
      bubbleSort.sort(actualResult, sortComparison);
      Assertions.assertEquals(expectedResult, actualResult);
    }
  }

  @Nested
  @DisplayName("Descending Order")
  class DescendingOrdertest {
    SortComparison<Integer, Boolean> sortComparison;
    Sortable<Integer> actualResult;
    Sortable<Integer> expectedResult;

    @BeforeEach
    void init() throws Exception {
      sortComparison = new DescendingOrder();
      actualResult = ArrayUtils.initUnsortedArray(10);
      expectedResult = ArrayUtils.initDescendingOrderArray(10);
    }

    @Test
    @DisplayName("::sort")
    void sortTest() {
      bubbleSort.sort(actualResult, sortComparison);
      Assertions.assertEquals(expectedResult, actualResult);
    }
  }
}