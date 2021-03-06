package trees;

public class BinaryTreeNodeImpl<T extends Comparable<T>> implements BinaryTreeNode<T> {
  private T key;
  private BinaryTreeNode<T> leftChild;
  private BinaryTreeNode<T> rightChild;

  public BinaryTreeNodeImpl(T item) {
    key = item;
    leftChild = null;
    rightChild = null;
  }

  @Override
  public T getKey() {
    return key;
  }

  public void setKey(T key) {
    this.key = key;
  }

  @Override
  public BinaryTreeNode<T> getLeftChild() {
    return leftChild;
  }

  @Override
  public void setLeftChild(T key) {
    this.leftChild = (key == null) ? null : new BinaryTreeNodeImpl<>(key);
  }

  @Override
  public BinaryTreeNode<T> getRightChild() {
    return rightChild;
  }

  @Override
  public void setRightChild(T key) {
    this.rightChild = (key == null) ? null : new BinaryTreeNodeImpl<>(key);
  }
}
