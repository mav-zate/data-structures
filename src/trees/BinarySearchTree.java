package trees;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * binary tree such that:
 *     all keys to left of node are less than node
 *     all keys to right of node are greater than node
 *
 * This one does not allow duplicates
 *
 */
public class BinarySearchTree<T extends Comparable<T>> {
  private final BinaryTreeNode<T> root;
  private int height = -1;

  /**
   * BST must be initialized with root node
   *
   * @param key
   */
  public BinarySearchTree(T key) {
    root = new BinaryTreeNodeImpl<>(key);
  }

  /**
   * Inserts new key into tree. No-op if key is already in tree
   *
   * Throws exception if key is not instance of {@link Comparable}
   *
   * @param key
   * @throws IllegalArgumentException
   */
  public void insert(T key) throws IllegalArgumentException {
    if (!(key instanceof Comparable)) {
      throw new IllegalArgumentException();
    }

    // no duplicates
    if (key.equals(root.getKey())) {
      return;
    }

    BinaryTreeNode<T> currentNode = root;
    boolean insert = false;
    while (!insert) {
      if (key.compareTo(currentNode.getKey()) < 0) {
        if (currentNode.getLeftChild() != null) {
          currentNode = currentNode.getLeftChild();
        } else {
          currentNode.setLeftChild(key);
          insert = true;
        }
      } else {
        if (currentNode.getRightChild() != null) {
          currentNode = currentNode.getRightChild();
        } else {
          currentNode.setRightChild(key);
          insert = true;
        }
      }
    }

    height = -1;
  }

  /**
   * Deletes key if found in tree (except root)
   *
   * Throws exception if key is not instance of {@link Comparable} or if key is root node
   *
   * @param key
   * @return
   * @throws IllegalArgumentException
   */
  public boolean delete(T key) throws IllegalArgumentException {
    if (!(key instanceof Comparable) || key.compareTo(root.getKey()) == 0) {
      throw new IllegalArgumentException();
    }

    boolean deleted = false;
    BinaryTreeNode<T> currentNode = root;
    while (!deleted) {
      BinaryTreeNode<T> leftChild = root.getLeftChild();
      BinaryTreeNode<T> rightChild = root.getRightChild();

      int orderComparison = key.compareTo(currentNode.getKey());
      if (orderComparison < 1) {
        if (leftChild == null) {
          break;
        }

        if (key.compareTo(leftChild.getKey()) == 0) {
          currentNode.setLeftChild(null);
          deleted = true;
        } else {
          currentNode = currentNode.getLeftChild();
        }
      } else {
        if (rightChild == null) {
          break;
        }

        if (key.compareTo(rightChild.getKey()) == 0) {
          currentNode.setRightChild(null);
          deleted = true;
        } else {
          currentNode = currentNode.getRightChild();
        }
      }
    }

    if (deleted) {
      height = -1;
    }
    return deleted;
  }

  /**
   * Checks if tree contains given key
   *
   * Throws exception if key is not instance of {@link Comparable}
   *
   * @param key
   * @return
   * @throws IllegalArgumentException
   */
  public boolean contains(T key) throws IllegalArgumentException {
    if (!(key instanceof Comparable)) {
      throw new IllegalArgumentException();
    }

    boolean found = false;
    BinaryTreeNode<T> currentNode = root;
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
   * Returns height of tree
   *
   * Caches result for consecutive calls (i.e., without {@link #insert} or {@link #delete}
   * being called between {@link #getHeight} calls
   *
   * @return
   */
  public int getHeight() {
    if (!(height < 0)) {
      return height;
    }

    height = subtreeHeight(root);
    return height;
  }

  /**
   * Returns nodes of tree in logical order
   *
   * @return
   */
  public List<T> depthFirstInOrder() {
    return depthFirst(root);
  }

  private List<T> depthFirst(BinaryTreeNode<T> node) {
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

//  public List<T> breadthFirst() {
//
//  }

  private int subtreeHeight(BinaryTreeNode<T> node) {
    if (node == null) {
      return 0;
    }

    BinaryTreeNode<T> leftChild = node.getLeftChild();
    BinaryTreeNode<T> rightChild = node.getRightChild();
    if (leftChild == null && rightChild == null) {
      return 0;
    }

    return 1 + Math.max(subtreeHeight(leftChild), subtreeHeight(rightChild));
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
