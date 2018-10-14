package comparison;


public class AscendingOrder <T extends Comparable<T>> implements SortComparison<T, Boolean> {
  @Override
  public Boolean isSwap(T left, T right) {
    return left.compareTo(right) > 0 ? true : false;
  }
}
