package hashtables;

/**
 * Basic implementation of a hash table backed by a static array
 *
 * Collisions are resolved by chaining
 *
 * @param <K>
 * @param <V>
 */
public class CustomHashTable<K, V> {
  private Entry<K, V>[] table;
  private int size;
  private int capacity;

  public CustomHashTable() {
    this(11);
  }

  public CustomHashTable(int capacity) {
    size = 0;
    this.capacity = capacity;
    table = new Entry[capacity];
    initTable(table, capacity);
  }

  private void initTable(Entry<K, V>[] table, int capacity) {
    for (int idx = 0; idx < capacity; idx++) {
      table[idx] = new Entry<>(null, null);
    }
  }

  /**
   *
   * @param key
   * @return
   * @throws NullPointerException
   */
  public V get(K key) throws NullPointerException {
    if (key == null) {
      throw new NullPointerException();
    }

    int index = key.hashCode() % capacity;

    Entry<K, V> bucket = table[index];
    while (bucket.hasNext()) {
      bucket = bucket.getNext();
      if (key.equals(bucket.getKey())) {
        return bucket.getValue();
      }
    }

    return null;
  }

  /**
   *
   * @param key
   * @param value
   * @return
   * @throws NullPointerException
   */
  public V put(K key, V value) throws NullPointerException {
    if (key == null || value == null) {
      throw new NullPointerException();
    }

    if (size >= capacity) {
      resizeTable();
    }

    int index = key.hashCode() % capacity;

    Entry<K, V> bucket = table[index];
    while (bucket.hasNext()) {
      bucket = bucket.getNext();
      if (key.equals(bucket.getKey())) {
        V oldValue = bucket.getValue();
        bucket.setValue(value);
        return oldValue;
      }
    }

    Entry<K, V> newEntry = new Entry<>(key, value);
    bucket.setNext(newEntry);
    size++;
    return null;
  }

  /**
   *
   *
   * @param key
   * @return
   */
  public V remove(K key) throws NullPointerException {
    if (key == null) {
      throw new NullPointerException();
    }

    int idx = key.hashCode() % capacity;

    Entry<K, V> prevBucket;
    Entry<K, V> currentBucket = table[idx];
    while(currentBucket.hasNext()) {
      prevBucket = currentBucket;
      currentBucket = currentBucket.getNext();

      if (key.equals(currentBucket.getKey())) {
        prevBucket.setNext(null);
        return currentBucket.getValue();
      }
    }

    return null;
  }

  public int size() {
    return size;
  }

  private void resizeTable() {
    Entry<K, V>[] newTable = new Entry[capacity * 2];
    capacity *= 2;


    initTable(newTable, capacity);

    for (int idx = 0; idx < table.length; idx++) {
      Entry<K, V> bucket = table[idx];
      while (bucket.hasNext()) {
        bucket = bucket.getNext();
        K key = bucket.getKey();
        V value = bucket.getValue();
        Entry<K, V> oldBucketClone = new Entry(key, value);

        int newIdx = key.hashCode() % capacity;

        Entry<K, V> newBucket = newTable[newIdx];
        newBucket.setNext(oldBucketClone);
      }
    }

    table = newTable;
  }

  private class Entry<K, V> {
    private K key;
    private V value;
    private Entry<K, V> next;

    Entry(K key, V value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }

    public K getKey() {
      return key;
    }

    public void setKey(K key) {
      this.key = key;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }

    public Entry<K, V> getNext() {
      return next;
    }

    public void setNext(Entry<K, V> next) {
      this.next = next;
    }

    public boolean hasNext() {
      return next != null;
    }
  }
}
