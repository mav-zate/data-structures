package comparison;


public class DescendingOrder<T extends Comparable<T>> implements SortComparison<T, Boolean> {

  public Boolean isSwap(T left, T right) {
    return left.compareTo(right) < 0 ? true : false;
  }
}
