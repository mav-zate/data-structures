package testutils;

import arrays.DynamicArray;

import java.util.concurrent.ThreadLocalRandom;

public class ArrayUtils {
  /**
   * Returns Integer {@link DynamicArray} with numbers 1 to size, inclusive in a random,
   * unsorted order
   *
   * @param size
   * @return
   * @throws Exception
   */
  public static DynamicArray<Integer> initUnsortedArray(int size) {
    DynamicArray<Integer> sortedArray = ArrayUtils.initAscendingOrderArray(size);
    final int initialCapacity = size;
    int capacity = sortedArray.size();

    DynamicArray<Integer> unsortedArray = new DynamicArray<>(sortedArray.size());
    for (int i = 0; i < initialCapacity; i++) {
      int randomIdx = ThreadLocalRandom.current().nextInt(0, capacity--);
      Integer item = sortedArray.delete(randomIdx);
      unsortedArray.insert(i, item);
    }

    return unsortedArray;
  }

  /**
   * Returns Integer {@link DynamicArray} that contains numbers 1 to size, inclusive
   *
   * @param size
   * @return
   * @throws Exception
   */
  public static DynamicArray<Integer> initAscendingOrderArray(int size) {
    if (size < 1) {
      throw new ArrayIndexOutOfBoundsException("Size cannot be non-positive");
    }
    DynamicArray<Integer> expectedResult = new DynamicArray<>(size);
    for (int i = 1; i < size + 1; i++) {
      expectedResult.add(i);
    }
    return expectedResult;
  }

  /**
   * Returns Integer {@link DynamicArray} that contains numbers size down to 1, inclusive
   *
   * @param size
   * @return
   * @throws Exception
   */
  public static DynamicArray<Integer> initDescendingOrderArray(int size) {
    if (size < 1) {
      throw new ArrayIndexOutOfBoundsException("Size cannot be non-positive");
    }

    DynamicArray<Integer> expectedResult = new DynamicArray<>(size);
    for (int i = size; i > 0; i--) {
      expectedResult.add(i);
    }
    return expectedResult;
  }
}
