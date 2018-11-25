package comparison;


public class DescendingOrder<T extends Comparable<T>> implements SortComparison<T, Integer> {
  @Override
  public Integer compare(T left, T right) {
    return right.compareTo(left);
  }
}
