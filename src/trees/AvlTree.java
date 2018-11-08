package trees;

import com.google.common.collect.Lists;
import java.util.List;

public class AvlTree<T extends Comparable<T>> {
  private AvlTreeNode<T> root;
  private int size;

  public AvlTree(T rootKey) throws NullPointerException {
    if (rootKey == null) {
      throw new NullPointerException();
    }

    size = 1;
    root = new AvlTreeNode<>(rootKey);
  }

  /**
   * Inserts node according to order imposed by {@link BinarySearchTree}.
   *
   * Self balances tree so that the {@link AvlTreeNode#balanceFactor} for every node is within
   * the [-1, 1] range.
   *
   * Throws {@link NullPointerException} if key parameter is null
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

    if (root == null) {
      root = newNode;
    }

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
          size++;
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
          size++;
          rebalance(currentNode);
          return true;
        }
      }
    }
  }

  /**
   * If given key is in tree, deletes node. Returns true if deletion occurs, false otherwise.
   *
   * @param key
   * @return
   * @throws IllegalArgumentException
   */
  public boolean delete(T key) throws IllegalArgumentException {
    if (!(key instanceof Comparable)) {
      throw new IllegalArgumentException();
    } else if (size < 1) {
      return false;
    }

    AvlTreeNode<T> currentNode = root;
    boolean deleted = false;
    while (!deleted) {
      if (currentNode == null) {
        break;
      }

      int orderComparison = key.compareTo(currentNode.getKey());
      if (orderComparison == 0) {
        delete(currentNode);
        size--;
      } else if (orderComparison < 0) {
        currentNode = currentNode.getLeftChild();
      } else {
        currentNode = currentNode.getRightChild();
      }
    }

    return deleted;
  }

  /**
   * Returns true if given key in tree
   *
   * Throws {@link IllegalArgumentException} if key is not instance of Comparable
   *
   * @param key
   * @return
   * @throws IllegalArgumentException
   */
  public boolean contains(T key) throws IllegalArgumentException {
    if (!(key instanceof Comparable)) {
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
   * Get number of keys in tree
   *
   * @return
   */
  public int size() {
    return size;
  }

  /**
   * Returns height of whole tree
   *
   * @return
   */
  public int height() {
    return height(root);
  }

  /**
   * Returns nodes of tree in logical order
   *
   * @return
   */
  public List<T> depthFirstInOrder() {
    return depthFirst(root);
  }

  private int height(AvlTreeNode<T> node) {
    if (node == null) {
      return -1;
    }

    return node.getHeight();
  }

  private List<T> depthFirst(AvlTreeNode<T> node) {
    List<T> allNodes = Lists.newArrayList();

    if (node == null) {
      return allNodes;
    } else {
      allNodes.addAll(depthFirst(node.getLeftChild()));
      allNodes.add(node.getKey());
      allNodes.addAll(depthFirst(node.getRightChild()));
    }

    return allNodes;
  }

  private void delete(AvlTreeNode<T> node){
    if (node.getLeftChild() == null && node.getRightChild() == null) {
      AvlTreeNode<T> parent = node.getParent();
      node.setParent(null);
      if (parent == null) {
        root = null;
      } else {
        if (parent.getKey().compareTo(node.getKey()) < 1) {
          parent.setRightChild(null);
        } else {
          parent.setLeftChild(null);
        }
        rebalance(parent);
      }
    }

    AvlTreeNode<T> child;
    if (node.getLeftChild() != null) {
      child = node.getLeftChild();
      while (child.getRightChild() != null) {
        child = child.getRightChild();
      }
      node.setKey(child.getKey());
      delete(child);
    } else if (node.getRightChild() != null) {
      child = node.getRightChild();
      while (child.getLeftChild() != null) {
        child = child.getLeftChild();
      }
      node.setKey(child.getKey());
      delete(child);
    }
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

  private void rebalance(AvlTreeNode<T> node) {
    setBalanceFactor(node);

    int balanceFactor = node.getBalanceFactor();
    if (balanceFactor < -1) {
      AvlTreeNode<T> leftChild = node.getLeftChild();
      if (height(leftChild.getLeftChild()) >= height(leftChild.getRightChild())) {
        node = rotateRight(node);
      } else {
          node = rotateLeftThenRight(node);
      }

    } else if (balanceFactor > 1) {
      AvlTreeNode<T> rightChild = node.getRightChild();
      if (height(rightChild.getLeftChild()) <= height(rightChild.getRightChild())) {
        node = rotateLeft(node);
      } else {
        node = rotateRightThenLeft(node);
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

    setBalanceFactor(node, nodeReplacement);

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

    setBalanceFactor(node, nodeReplacement);

    return nodeReplacement;
  }

  private AvlTreeNode<T> rotateRightThenLeft(AvlTreeNode<T> node) {
    node.setRightChild(rotateRight(node.getRightChild()));
    return rotateLeft(node);
  }

  private AvlTreeNode<T> rotateLeftThenRight(AvlTreeNode<T> node) {
    node.setLeftChild(rotateLeft(node.getLeftChild()));
    return rotateRight(node);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("BinarySearchTree: {");

    List<T> keys = depthFirstInOrder();
    for (T key : keys) {
      sb.append(key);
      sb.append(", ");
    }
    sb.delete(sb.length() - 2, sb.length());
    sb.append("}");

    return sb.toString();
  }
}
