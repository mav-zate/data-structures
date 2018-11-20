package trees;

import arrays.CustomArray;
import arrays.DynamicArray;

public class Trie {
  private TrieNode root;

  public Trie() {
    this.root = new TrieNode(Character.MIN_VALUE);
  }

  /**
   *
   * @param key
   */
  public void insert(String key) {
    if (key == null) {
      throw new NullPointerException();
    }

    char[] keyLetters = key.toLowerCase().toCharArray();
    TrieNode currentNode = root;
    for (int currentIdx = 0; currentIdx < keyLetters.length; currentIdx++) {
      int childIdx = keyLetters[currentIdx] - TrieNode.ASCII_LOWERCASE_A_OFFSET;

      if (!currentNode.hasChild(childIdx)) {
        currentNode.addChild(childIdx);
        if (currentIdx == keyLetters.length - 1) {
          currentNode.getChild(childIdx).setTerminal(true);
        }
      }

      currentNode = currentNode.getChild(childIdx);
    }
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

  public CustomArray<String> getAllKeysStartingWith(String substring) {
    return getAllSuffixes(substring, root);
  }

  private CustomArray<String> getAllSuffixes(String substring, TrieNode currentNode) {
    if (currentNode.getChildCount() < 1 && currentNode.isTerminal()) {
      String valueAsString = Character.toString(currentNode.getValue());
      return new DynamicArray<>(valueAsString);
    }

    CustomArray<String> strings = new DynamicArray<>();
    CustomArray<TrieNode> children = currentNode.getNonNullChildren();
    for (int i = 0; i < children.size(); i++) {
      CustomArray<String> childStrings = getAllSuffixes(substring.substring(1, substring.length()), children.get(i));
      for (int j = 0; j < childStrings.size(); j++) {
        strings.add(childStrings.get(j));
      }
    }

    return strings;
  }
}
