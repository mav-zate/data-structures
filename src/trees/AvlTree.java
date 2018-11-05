package trees;

public class AvlTree<T extends Comparable<T>> {
  private AvlTreeNode<T> root;

  public AvlTree(T rootKey) throws NullPointerException {
    if (rootKey == null) {
      throw new NullPointerException();
    }

    root = new AvlTreeNode<>(rootKey);
  }

  /**
   *
   * @param key
   * @return
   * @throws NullPointerException
   */
  public boolean insert(T key) throws NullPointerException {
    if (key == null) {
      throw new NullPointerException();
    }

    AvlTreeNode<T> newNode = new AvlTreeNode<>(key);
    AvlTreeNode<T> currentNode = root;
    while (true) {
      if (key.compareTo(currentNode.getKey()) > 0) {
        AvlTreeNode<T> rightChild = currentNode.getRightChild();
        if (rightChild != null) {
          if (key.compareTo(rightChild.getKey()) == 0) {
            return false;
          }
          currentNode = currentNode.getRightChild();
        } else {
          currentNode.setRightChild(newNode);
          newNode.setParent(currentNode);
          rebalance(currentNode);
          return true;
        }
      } else {
        AvlTreeNode<T> leftChild = currentNode.getLeftChild();
        if (leftChild != null) {
          if (key.compareTo(leftChild.getKey()) == 0) {
            return false;
          }
          currentNode = currentNode.getLeftChild();
        } else {
          currentNode.setLeftChild(newNode);
          newNode.setParent(currentNode);
          rebalance(currentNode);
          return true;
        }
      }
    }
  }

  // TODO: write delete
//  public boolean delete(T key) throws NullPointerException {
//
//  }

  /**
   *
   *
   * @param key
   * @return
   * @throws NullPointerException
   */
  public boolean contains(T key) throws NullPointerException {
    if (key == null) {
      throw new NullPointerException();
    }

    AvlTreeNode<T> currentNode = root;
    boolean found = false;
    while (!found) {
      if (currentNode == null) {
        break;
      }

      int orderComparison = key.compareTo(currentNode.getKey());
      if (orderComparison == 0) {
        found = true;
      } else if (orderComparison < 0) {
        currentNode = currentNode.getLeftChild();
      } else {
        currentNode = currentNode.getRightChild();
      }
    }

    return found;
  }

  /**
   *
   *
   * @param node
   * @return
   */
  public int height(AvlTreeNode<T> node) {
    if (node == null) {
      return -1;
    }

    return node.getHeight();
  }

  private void resetHeight(AvlTreeNode<T> node) {
    int height = 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    node.setHeight(height);
  }

  private void setBalanceFactor(AvlTreeNode<T>... nodes) {
    for (AvlTreeNode<T> node : nodes) {
      resetHeight(node);
      node.setBalanceFactor(height(node.getRightChild()) - height(node.getLeftChild()));
    }
  }

  // TODO: write rebalance
  private void rebalance(AvlTreeNode<T> node) {

    // left-left

    // right-right

    // left-right

    // right-left
  }

  private void rotateLeft() {

  }

  private void rotateRight() {

  }

  private void rotateRightThenLeft() {

  }

  private void rotateLeftThenRight() {

  }
}
