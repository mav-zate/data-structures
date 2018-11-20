package trees;

import arrays.CustomArray;
import arrays.DynamicArray;

/**
 * TrieNode for ASCII characters
 */
public class TrieNode implements Comparable<TrieNode> {
  public static final int ASCII_LOWERCASE_A_OFFSET = 97;

  private TrieNode[] children;
  private int childCount;
  private char value;
  private boolean terminal;

  public TrieNode(char value) {
    this.children = new TrieNode[26];
    this.childCount = 0;
    this.value = value;
    this.terminal = false;
  }

  /**
   * Retrieves all branches {@link TrieNode} of current node.
   *
   * NB: all branches are references to TrieNode's {@link #children} so be weary of modifying them
   *
   * @return
   */
  public CustomArray<TrieNode> getNonNullChildren() {
    CustomArray<TrieNode> array = new DynamicArray<>();

    for (int i = 0; i < this.children.length; i++) {
      if (this.children[i] != null) {
        array.add(this.children[i]);
      }
    }

    return array;
  }

  /**
   * If the current branch is null, adds a node and increments {@link #childCount}
   *
   * Else, no-op.
   *
   * @param idx
   */
  public void addChild(int idx) {
    if (this.children[idx] == null) {
      this.children[idx] = new TrieNode((char) idx);
      this.childCount++;
    }
  }

  /**
   * If the current branch is non-null, removes the node and decrements {@link #childCount}
   *
   * Else, no-op.
   *
   * @param idx
   */
  public void removeChild(int idx) {
    if (this.children[idx] != null) {
      this.children[idx] = null;
      this.childCount--;
    }
  }

  public TrieNode getChild(int idx) {
    return this.children[idx];
  }

  public boolean hasChild(int idx) {
    return this.children[idx] != null;
  }

  public int getChildCount() {
    return childCount;
  }

  public char getValue() {
    return value;
  }

  public boolean isTerminal() {
    return terminal;
  }

  public void setTerminal(boolean terminal) {
    this.terminal = terminal;
  }

  @Override
  public int compareTo(TrieNode other) {

    if (this.value < other.getValue()) {
      return -1;
    } else if (this.value == other.getValue()) {
      return 0;
    } else {
      return 1;
    }
  }
}
