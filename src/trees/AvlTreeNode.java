package trees;

public class AvlTreeNode<T extends Comparable<T>> {
    private T key;
    private int balanceFactor;
    private int height;
    private AvlTreeNode<T> parent;
    private AvlTreeNode<T> leftChild;
    private AvlTreeNode<T> rightChild;

    public AvlTreeNode(T key) throws NullPointerException {
      if (key == null) {
        throw new NullPointerException();
      }
      this.key = key;
      this.balanceFactor = 0;
      this.height = 0;
      this.parent = null;
      this.leftChild = null;
      this.rightChild = null;
    }

    public T getKey() {
      return key;
    }

  public int getBalanceFactor() {
      return balanceFactor;
    }

  public void setBalanceFactor(int balanceFactor) {
    this.balanceFactor = balanceFactor;
  }

  public int getHeight() {
      return height;
    }

  public void setHeight(int height) {
    this.height = height;
  }

  public AvlTreeNode<T> getParent() {
      return parent;
    }

    public void setParent(AvlTreeNode<T> parent) {
      this.parent = parent;
    }

    public AvlTreeNode<T> getLeftChild() {
      return leftChild;
    }

    public void setLeftChild(AvlTreeNode<T> leftChild) {
      this.leftChild = leftChild;
    }

    public AvlTreeNode<T> getRightChild() {
      return rightChild;
    }

    public void setRightChild(AvlTreeNode<T> rightChild) {
      this.rightChild = rightChild;
    }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof AvlTreeNode)) {
      return false;
    }

    AvlTreeNode<T> other = (AvlTreeNode<T>) obj;

    // TODO: include rest of the fields
    return getKey().equals(other.getKey());
  }
}
