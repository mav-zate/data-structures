package arrays;

import comparison.AscendingOrder;
import comparison.DescendingOrder;
import comparison.SortComparison;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {
  BubbleSort<Integer> bubbleSort;

  @BeforeEach
  void init() throws Exception {
    bubbleSort = new BubbleSort<>();
  }

  public Sortable<Integer> initInput() throws Exception {
    DynamicArray actualResult = new DynamicArray<>(10);

    actualResult.add(2);
    actualResult.add(7);
    actualResult.add(4);
    actualResult.add(3);
    actualResult.add(8);

    actualResult.add(1);
    actualResult.add(10);
    actualResult.add(9);
    actualResult.add(5);
    actualResult.add(6);

    return actualResult;
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
      actualResult = initInput();
      expectedResult = initExpectedResult();
    }

    @Test
    @DisplayName("::sort")
    void sortTest() {
      System.out.println(expectedResult);
      bubbleSort.sort(actualResult, sortComparison);
      Assertions.assertEquals(expectedResult, actualResult);
    }

    Sortable initExpectedResult() throws Exception {
      DynamicArray expectedResult = new DynamicArray<>(10);
      for (int i = 1; i < 11; i++) {
        expectedResult.add(i);
      }

      return expectedResult;
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
      actualResult = initInput();
      expectedResult = initExpectedResult();
    }

    @Test
    @DisplayName("::sort")
    void sortTest() {
      bubbleSort.sort(actualResult, sortComparison);
      Assertions.assertEquals(expectedResult, actualResult);
    }

    Sortable initExpectedResult() throws Exception {
      DynamicArray expectedResult = new DynamicArray<>(10);
      for (int i = 10; i > 0; i--) {
        expectedResult.add(i);
      }

      return expectedResult;
    }
  }
}