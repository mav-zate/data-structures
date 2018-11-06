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
    setBalanceFactor(node);

    int balanceFactor = node.getBalanceFactor();
    if (balanceFactor < -1) {
      AvlTreeNode<T> leftChild = node.getLeftChild();
      if (height(leftChild.getLeftChild()) >= height(leftChild.getRightChild())) {
        node = rotateRight(node);
      } else {
          // TODO: left then right
      }

    } else if (balanceFactor > 1) {
      AvlTreeNode<T> rightChild = node.getRightChild();
      if (height(rightChild.getLeftChild()) <= height(rightChild.getRightChild())) {
        node = rotateLeft(node);
      } else {
        // TODO: right then left
      }
    }

    if (node.getParent() != null) {
      rebalance(node.getParent());
    } else {
      root = node;
    }
  }

  private AvlTreeNode<T> rotateLeft(AvlTreeNode<T> node) {
    AvlTreeNode<T> nodeReplacement = node.getRightChild();

    node.setRightChild(nodeReplacement.getLeftChild());
    if (nodeReplacement.getLeftChild() != null ) {
      nodeReplacement.getLeftChild().setParent(node);
    }

    AvlTreeNode<T> nodeOldParent = node.getParent();

    nodeReplacement.setLeftChild(node);
    node.setParent(nodeReplacement);

    nodeReplacement.setParent(nodeOldParent);
    if (nodeOldParent != null) {
      if (nodeOldParent.getLeftChild().equals(node)) {
        nodeOldParent.setLeftChild(nodeReplacement);
      } else {
        nodeOldParent.setRightChild(nodeReplacement);
      }
    }

    return nodeReplacement;
  }

  private AvlTreeNode<T> rotateRight(AvlTreeNode<T> node) {
    AvlTreeNode<T> nodeReplacement = node.getLeftChild();

    node.setLeftChild(nodeReplacement.getRightChild());
    if (nodeReplacement.getRightChild() != null) {
      nodeReplacement.getRightChild().setParent(node);
    }

    AvlTreeNode<T> nodeOldParent = node.getParent();

    nodeReplacement.setRightChild(node);
    node.setParent(nodeReplacement);

    nodeReplacement.setParent(nodeOldParent);
    if (nodeOldParent != null) {
      if (nodeOldParent.getLeftChild().equals(node)) {
        nodeOldParent.setLeftChild(nodeReplacement);
      } else {
        nodeOldParent.setRightChild(nodeReplacement);
      }
    }

    return nodeReplacement;
  }

  private void rotateRightThenLeft(AvlTreeNode<T> node) {

  }

  private void rotateLeftThenRight(AvlTreeNode<T> node) {

  }
}
