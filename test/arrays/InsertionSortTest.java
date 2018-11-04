package arrays;

import comparison.AscendingOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

class InsertionSortTest {
  InsertionSort<Integer> insertionSort;
  DynamicArray<Integer> sortedArray;

  @BeforeEach
  void init() throws Exception {
    insertionSort = new InsertionSort<>();
    sortedArray = initSortedArray();
  }

  @Test
  void testSort() throws Exception {
    DynamicArray<Integer> inputArray = initInput();
    insertionSort.sort(inputArray, new AscendingOrder<>());

    Assertions.assertEquals(sortedArray, inputArray);
  }

  private DynamicArray<Integer> initInput() throws Exception {
    DynamicArray<Integer> sortedArray = initSortedArray();
    final int initialCapacity = sortedArray.size();
    int capacity = sortedArray.size();

    DynamicArray<Integer> unsortedArray = new DynamicArray<>(sortedArray.size());
    for (int i = 0; i < initialCapacity; i++) {
      int randomIdx = ThreadLocalRandom.current().nextInt(0, capacity--);
      Integer item = sortedArray.delete(randomIdx);
      unsortedArray.insert(i, item);
    }

    return unsortedArray;
  }

  private DynamicArray<Integer> initSortedArray() throws Exception {
    DynamicArray<Integer> expectedResult = new DynamicArray<>(10);
    for (int i = 0; i < 11; i++) {
      expectedResult.add(i);
    }
    return expectedResult;
  }
}