package arrays;


import org.junit.jupiter.api.*;
import testutils.ArrayUtils;

/**
 * Created by malzate on 10/2/2018.
 */
public class DynamicArrayTest {

  DynamicArray<Integer> array;

  void init() {
    array = new DynamicArray<>(1);
    array.add(1);
  }

  @Nested
  @DisplayName("When empty")
  class EmptyDynamicArray {
    @BeforeEach
    void init() {
      DynamicArrayTest.this.init();
      array.delete(0);
    }

    @Test
    @DisplayName("::get throws error")
    void getTest() {
      Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(0));
    }

    @Test
    @DisplayName("::insert in bounds works")
    void insertTest() {
      array.insert(0, 10);
      Assertions.assertEquals(10, (Object) array.get(0));
    }

    @Test
    @DisplayName("::add works")
    void addTest() {
      array.add(10);
      Assertions.assertEquals(10, (Object) array.get(0));
    }

    @Test
    @DisplayName("::addAll works")
    void testAddAll() {
      CustomArray<Integer> expectedResult = new DynamicArray<>(1, 2, 3, 4);
      array.addAll(new DynamicArray<>(1, 2, 3, 4));

      Assertions.assertEquals(expectedResult, array);
    }

    @Test
    @DisplayName("::delete throws error")
    void deleteTest() {
      Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.delete(0));
    }

    @Test
    @DisplayName("::deleteAll should make size zero")
    void deleteAll() {
      array.deleteAll();
      Assertions.assertEquals(0, array.size());
    }
  }

  @Nested
  @DisplayName("When at capacity")
  class DynamicArrayAtCapacity {
    @BeforeEach
    void init() {
      DynamicArrayTest.this.init();
    }

    @Test
    @DisplayName("::insert grows internal array")
    void insertTest() {
      Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1));

      array.insert(0, 10);

      Assertions.assertAll(() -> {
        Assertions.assertEquals(10, (Object) array.get(0));
        Assertions.assertEquals(1, (Object) array.get(1));
      });
    }

    @Test
    @DisplayName("::add grows internal array")
    void addTest() {
      Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1));

      array.add(10);

      Assertions.assertAll(() -> {
        Assertions.assertEquals(1, (Object) array.get(0));
        Assertions.assertEquals(10, (Object) array.get(1));
      });
    }

    @Test
    @DisplayName("::addAll works")
    void testAddAll() {
      CustomArray<Integer> expectedResult = new DynamicArray<>(1, 2, 3, 4, 5);
      array.addAll(new DynamicArray<>(2, 3, 4, 5));

      Assertions.assertEquals(expectedResult, array);
    }

    @Test
    @DisplayName("::slice")
    void testSlice() throws Exception {
      CustomArray<Integer> expectedResult = ArrayUtils.initAscendingOrderArray(2);

      array.add(2);
      array.add(3);
      array.add(4);
      array.add(5);

      Assertions.assertAll(
          () -> Assertions.assertThrows(IndexOutOfBoundsException.class, () -> array.slice(2, 2)),
          () -> Assertions.assertEquals(expectedResult, array.slice(0, 2))
      );
    }
  }
}
