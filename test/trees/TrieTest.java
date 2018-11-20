package trees;


import arrays.CustomArray;
import arrays.DynamicArray;
import org.junit.jupiter.api.*;

public class TrieTest {
  Trie trie;

  @BeforeEach
  void init() {
    trie = new Trie();
  }

  @Nested
  @DisplayName("::insert")
  class TrieInsert {
    @BeforeEach
    void init() {
      TrieTest.this.init();
    }

    @Test
    @DisplayName("no-op on empty string")
    void testInsertEmptyString() {
      Assertions.assertEquals(false, trie.insert(""));
    }

    @Test
    @DisplayName("inserts key with no prior prefixes")
    void testKeyWithNoPriorPrefix() {
      String exampleKey1 = "someString";
      String exampleKey2 = "anotherString";
      Assertions.assertAll(
          () -> Assertions.assertEquals(true, trie.insert(exampleKey1)),
          () -> Assertions.assertEquals(true, trie.insert(exampleKey2))
      );
    }

    @Test
    @DisplayName("does not insert duplicate keys")
    void testInsertNoDuplicateKeys() {
      String exampleKey = "someString";
      trie.insert(exampleKey);
      trie.insert(exampleKey);

      Assertions.assertAll(
          () -> Assertions.assertEquals(true, trie.insert(exampleKey)),
          () -> Assertions.assertEquals(false, trie.insert(exampleKey))
      );
    }

    @Test
    @DisplayName("successfully branches off keys with same prefix")
    void testInsertNewSuffixOnExistingPrefix() {
      String prefix = "circum";
      String suffix1 = "spect";
      String suffix2 = "vent";
      CustomArray<String> expectedResults = new DynamicArray<>((prefix + suffix1), (prefix + suffix2));

      trie.insert(prefix + suffix1);
      trie.insert(prefix + suffix2);

      Assertions.assertEquals(expectedResults, trie.getAllKeysWithPrefix(prefix));
    }
  }

  @Nested
  @DisplayName("::delete")
  class TrieDelete {
    @BeforeEach
    void init() {
      TrieTest.this.init();
    }

    @Test
    @DisplayName("does not delete multiple keys on same branch (sharing same prefix)")
    void testDeleteSingleBranchWithManyKeys() {
      String prefix = "under";
      String mid = "stand";
      String suffix = "able";
      trie.insert(prefix);
      trie.insert(prefix + mid);
      trie.insert(prefix + mid + suffix);

      trie.delete(prefix + mid);

      Assertions.assertAll(
          () -> Assertions.assertEquals(true, trie.contains(prefix)),
          () -> Assertions.assertEquals(false, trie.contains(prefix + mid)),
          () -> Assertions.assertEquals(true, trie.contains(prefix + mid + suffix))
      );
    }

    @Test
    @DisplayName("removes key with no shared prefix")
    void testDeleteKeyNotSharingrefix() {
      String exampleKey = "someString";
      trie.insert(exampleKey);
      Assertions.assertAll(
          () -> Assertions.assertEquals(true, trie.delete(exampleKey)),
          () -> Assertions.assertEquals(false, trie.contains(exampleKey))
      );
    }

    @Test
    @DisplayName("")
    void testDeleteKeyWithSharedPrefix() {
      String prefix = "circum";
      String suffix1 = "spect";
      String suffix2 = "vent";
      String suffix3 = "navigate";

      Assertions.assertAll(
          () -> Assertions.assertEquals(true, trie.delete(prefix + suffix3)),
          () -> Assertions.assertEquals(true, trie.contains(prefix + suffix1)),
          () -> Assertions.assertEquals(true, trie.contains(prefix + suffix2)),
          () -> Assertions.assertEquals(false, trie.contains(prefix + suffix3))
      );
    }
  }

  @Test
  @DisplayName("::contains")
  void testContains() {
    String prefix = "circum";
    String suffix1 = "spect";
    String suffix2 = "vent";
    String suffix3 = "navigate";

    trie.insert(prefix + suffix1);
    trie.insert(prefix + suffix2);
    trie.insert(prefix + suffix3);

    Assertions.assertAll(
        () -> Assertions.assertEquals(true, trie.contains("")),
        () -> Assertions.assertEquals(false, trie.contains(prefix)),
        () -> Assertions.assertEquals(true, trie.contains(prefix + suffix1)),
        () -> Assertions.assertEquals(true, trie.contains(prefix + suffix2)),
        () -> Assertions.assertEquals(true, trie.contains(prefix + suffix3))
    );
  }

  @Test
  @DisplayName("::getAllKeysWithPrefix")
  void testGetAllKeysWithPrefix() {
    String prefix = "circum";
    String suffix1 = "spect";
    String suffix2 = "vent";
    String suffix3 = "navigate";

    trie.insert(prefix + suffix1);
    trie.insert(prefix + suffix2);
    trie.insert(prefix + suffix3);

    CustomArray<String> expectedResult = new DynamicArray<>((prefix + suffix1), (prefix + suffix2), (prefix + suffix3));

    Assertions.assertEquals(expectedResult, trie.getAllKeysWithPrefix(prefix))
  }
}