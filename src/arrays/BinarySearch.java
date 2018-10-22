package arrays;


import java.util.Arrays;

public class BinarySearch {
  public static void main(String[] args) {
    int[] ascendingIntegers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    System.out.println("Recursive Binary Search Test");
    System.out.println("Expected 0, actual: " + binarySearchRecursive(ascendingIntegers, 1));
    System.out.println("Expected 8, actual: " + binarySearchRecursive(ascendingIntegers, 9));
    System.out.println("Expected 6, actual: " + binarySearchRecursive(ascendingIntegers, 7));
    System.out.println("Expected x < 0, actual: " + binarySearchRecursive(ascendingIntegers, 10));
  }

  /**
   *
   * @param list
   * @param soughtItem
   * @return
   */
  public static int binarySearchRecursive(int[] list, int soughtItem) {
    if (list.length < 1) {
      return -1;
    }

    int mid = list.length / 2;
    int midItem = list[mid];

    if (list.length == 1) {
      return midItem == soughtItem ? 0 : Integer.MIN_VALUE;
    }

    if (midItem == soughtItem) {
      return mid;
    } else if (midItem > soughtItem) {
      int[] leftHalf = Arrays.copyOfRange(list, 0, mid);
      return binarySearchRecursive(leftHalf, soughtItem);
    } else {
      int[] rightHalf = Arrays.copyOfRange(list, mid, list.length);
      return mid + binarySearchRecursive(rightHalf, soughtItem);
    }
  }
}
