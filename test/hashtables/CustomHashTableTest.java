package hashtables;

import org.junit.jupiter.api.*;

class CustomHashTableTest {
  private static final String FIRST_KEY = "key1";
  private static final String SECOND_KEY = "key2";
  private static final String THIRD_KEY = "key3";

  private static final String FIRST_VALUE = "value1";
  private static final String SECOND_VALUE = "value2";
  private static final String THIRD_VALUE = "value3";

  private CustomHashTable<String, String> testSubject;

  @Nested
  @DisplayName("With some entries")
  class NormalHashTable {

    @BeforeEach
    void init() {
      testSubject = new CustomHashTable<>();
    }

    @Test
    @DisplayName("::get")
    void getTest() {
      testSubject.put(FIRST_KEY, FIRST_VALUE);

      Assertions.assertAll(() -> {
        Assertions.assertEquals(FIRST_VALUE, testSubject.get(FIRST_KEY));
        Assertions.assertEquals(null, testSubject.get(SECOND_KEY));
      });
    }

    @Test
    @DisplayName("::put returns overwritten value")
    void putTest() {
      testSubject.put(FIRST_KEY, FIRST_VALUE);
      String oldValue = testSubject.put(FIRST_KEY, SECOND_VALUE);

      Assertions.assertAll(() -> {
        Assertions.assertEquals(FIRST_VALUE, oldValue);
        Assertions.assertEquals(SECOND_VALUE, testSubject.get(FIRST_KEY));
        Assertions.assertThrows(NullPointerException.class, () -> testSubject.put(FIRST_KEY, null));
      });
    }

    @Test
    @DisplayName("::remove")
    void removeTest() {
      testSubject.put(FIRST_KEY, FIRST_VALUE);
      String removedValue = testSubject.remove(FIRST_KEY);

      Assertions.assertAll(() -> {
        Assertions.assertEquals(FIRST_VALUE, removedValue);
        Assertions.assertEquals(null, testSubject.remove(FIRST_KEY));
      });
    }
  }

  @Nested
  @DisplayName("At full capacity")
  class FullHashTable {

    @BeforeEach
    void init() {
      testSubject = new CustomHashTable<>(2);
    }

    @Test
    @DisplayName("::put resizes hashtable")
    void putTest() {
      testSubject.put(FIRST_KEY, FIRST_VALUE);
      testSubject.put(SECOND_KEY, SECOND_VALUE);
      testSubject.put(THIRD_KEY, THIRD_VALUE);

      Assertions.assertAll(() -> {
        Assertions.assertEquals(3, testSubject.size());
        Assertions.assertEquals(FIRST_VALUE, testSubject.get(FIRST_KEY));
        Assertions.assertEquals(SECOND_VALUE, testSubject.get(SECOND_KEY));
        Assertions.assertEquals(THIRD_VALUE, testSubject.get(THIRD_KEY));
      });
    }
  }
}