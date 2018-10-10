package arrays;


import org.junit.jupiter.api.*;

/**
 * Created by malzate on 10/2/2018.
 */
public class DynamicArrayTest {

  DynamicArray<Integer> array;

  void init() {
    try {
      array = new DynamicArray<>(1);
    } catch (Exception e) {
      // Throws exception for negative size
    }
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
  }
}
