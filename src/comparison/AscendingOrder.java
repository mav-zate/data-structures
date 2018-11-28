package comparison;


public class AscendingOrder<T extends Comparable<T>> implements SortComparison<T, Integer> {
  @Override
  public Integer compare(T left, T right) {
    return left.compareTo(right);
  }
}
