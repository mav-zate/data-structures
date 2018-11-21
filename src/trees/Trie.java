package trees;

import arrays.CustomArray;
import arrays.DynamicArray;

import java.util.Optional;

public class Trie {
  private TrieNode root;

  public Trie() {
    this.root = new TrieNode(Character.MIN_VALUE);
  }

  /**
   * Inserts key into trie.
   *
   * Returns true if insert successful, false otherwise
   *
   * @param key
   * @return
   */
  public boolean insert(String key) {
    if (key == null) {
      throw new NullPointerException();
    } else if (key.isEmpty()) {
      return false;
    }

    boolean inserted = false;
    char[] keyLetters = key.toLowerCase().toCharArray();
    TrieNode currentNode = root;
    for (int currentIdx = 0; currentIdx < keyLetters.length; currentIdx++) {
      int childIdx = keyLetters[currentIdx] - TrieNode.ASCII_LOWERCASE_A_OFFSET;

      if (!currentNode.hasChild(childIdx)) {
        currentNode.addChild(childIdx);
        if (currentIdx == keyLetters.length - 1) {
          currentNode.getChild(childIdx).setTerminal(true);
          inserted = true;
        }
      }

      currentNode = currentNode.getChild(childIdx);
    }

    return inserted;
  }

  /**
   *
   *
   * @param key
   */
  public boolean delete(String key) {
    if (key == null) {
      throw new NullPointerException();
    } else if (key.isEmpty()) {
      return false;
    }

    char[] keyLetters = key.toLowerCase().toCharArray();

    TrieNode lastFork = root;
    int forkChild = keyLetters[0] - TrieNode.ASCII_LOWERCASE_A_OFFSET;

    TrieNode currentNode = root;
    for (int currentIdx = 0; currentIdx < keyLetters.length; currentIdx++) {
      int childIdx = keyLetters[currentIdx] - TrieNode.ASCII_LOWERCASE_A_OFFSET;

      if (!currentNode.hasChild(childIdx)) {
        return false;
      } else {
        if (currentNode.getChildCount() > 1) {
          lastFork = currentNode.getChild(childIdx);
          forkChild = childIdx;
        }

        currentNode = currentNode.getChild(childIdx);
      }
    }

    if (!currentNode.isTerminal()) { // Forbids partial key removal
      return false;
    } else {
      if (currentNode.getChildCount() > 0) {
        currentNode.setTerminal(false);
      } else {
        lastFork.removeChild(forkChild);
      }
      return true;
    }
  }

  /**
   *
   *
   * @param key
   * @return
   */
  public boolean contains(String key) {
    if (key == null) {
      throw new NullPointerException();
    } else if (key.isEmpty()) {
      return true;
    }

    char[] keyLetters = key.toLowerCase().toCharArray();
    TrieNode currentNode = root;
    for (int currentIdx = 0; currentIdx < keyLetters.length; currentIdx++) {
      int childIdx = keyLetters[currentIdx] - TrieNode.ASCII_LOWERCASE_A_OFFSET;

      if (!currentNode.hasChild(childIdx)) {
        return false;
      } else {
        currentNode = currentNode.getChild(childIdx);
      }
    }

    return currentNode.isTerminal();
  }

  /**
   * Given a substring, returns all keys in trie that begin with substring as prefix.
   *
   * @param prefix
   * @return
   */
  public CustomArray<String> getAllKeysWithPrefix(String prefix) {
    if (prefix.isEmpty()) {
      return new DynamicArray<>("");
    }

    Optional<TrieNode> endOfPrefix = getEndOfPrefix(prefix);

    if (endOfPrefix.isPresent()) {
      CustomArray<TrieNode> endOfPrefixChildren = endOfPrefix.get().getNonNullChildren();
      CustomArray<String> suffixes = new DynamicArray<>();
      for (int i = 0; i < endOfPrefixChildren.size(); i++) {
        suffixes.addAll(getAllSuffixes(endOfPrefixChildren.get(i)));
      }
      for (int j = 0; j < suffixes.size(); j++) {
        suffixes.set(j, prefix + suffixes.get(j));
      }
      return suffixes;
    } else {
      return new DynamicArray<>("");
    }
  }

  private Optional<TrieNode> getEndOfPrefix(String prefix) {
    char[] prefixLetters = prefix.toLowerCase().toCharArray();
    TrieNode currentNode = root;
    for (int currentIdx = 0; currentIdx < prefixLetters.length; currentIdx++) {
      int childIdx = prefixLetters[currentIdx] - TrieNode.ASCII_LOWERCASE_A_OFFSET;

      if (!currentNode.hasChild(childIdx)) {
        currentNode = null;
        break;
      } else {
        currentNode = currentNode.getChild(childIdx);
      }
    }

    return Optional.ofNullable(currentNode);
  }

  @SuppressWarnings("unchecked")
  private CustomArray<String> getAllSuffixes(TrieNode currentNode) {
    if (currentNode.isTerminal() && currentNode.getChildCount() < 1) {
      String valueAsString = Character.toString(currentNode.getValue());
      return new DynamicArray<>(valueAsString);
    }

    CustomArray<String> strings = new DynamicArray<>();
    CustomArray<TrieNode> children = currentNode.getNonNullChildren();
    for (int i = 0; i < children.size(); i++) {
      if (currentNode.isTerminal()) {
        strings.add(String.valueOf(currentNode.getValue()));
      }
      CustomArray<String> childStrings = getAllSuffixes(children.get(i));
      for (int j = 0; j < childStrings.size(); j++) {
        String childStr = childStrings.get(j);
        strings.add(currentNode.getValue() + childStr);
      }
    }

    return strings;
  }
}
