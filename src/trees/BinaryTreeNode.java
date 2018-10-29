package trees;

public interface BinaryTreeNode<T extends Comparable<T>> {
  T getKey();

  BinaryTreeNode<T> getLeftChild();

  void setLeftChild(T key);

  BinaryTreeNode<T> getRightChild();

  void setRightChild(T key);
}
