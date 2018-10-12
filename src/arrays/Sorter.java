package arrays;


import java.util.function.BiFunction;

public interface Sorter<T> {
  void sort(Sortable<T> sortable, BiFunction<T, T, Boolean> comparator);
}
