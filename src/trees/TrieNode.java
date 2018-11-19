package trees;

/**
 * TrieNode for ASCII characters
 */
public class TrieNode {
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

  public TrieNode[] getChildren() {
    return children;
  }

  public void addChild(int idx) {
    if (this.children[idx] == null) {
      this.children[idx] = new TrieNode((char) idx);
      this.childCount++;
    }
  }

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
}
