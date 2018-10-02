package arrays;

/**
 * Implementation of dynamic array backed by a static array
 */
public class DynamicArray {
    private int[] internalArray;
    private int actualSize;
    private int maxSize;

    DynamicArray(int size) throws Exception {
        if (size < 1) {
            throw new Exception("Array cannot have negative length");
        }

        internalArray = new int[size];
        maxSize = size;
        actualSize = 0;
    }

    /**
     * T: O(1)
     * @param idx
     * @return
     * @throws ArrayIndexOutOfBoundsException
     */
    public int get(int idx) throws ArrayIndexOutOfBoundsException {
        if (outOfArrayBounds(idx)) {
            throw new ArrayIndexOutOfBoundsException("Array does not contain index: " + idx);
        }

        return internalArray[idx];
    }

    /**
     * Amortized: O(1), Best case: O(1), Worse Case: O(n)
     * @param idx
     * @param item
     * @throws ArrayIndexOutOfBoundsException
     */
    public void insert(int idx, int item) throws ArrayIndexOutOfBoundsException {
        if (outOfArrayBounds(idx)) {
            throw new ArrayIndexOutOfBoundsException("Cannot insert...index outside of array bounds");
        }

        if (actualSize >= maxSize) {
            growInternalArray();
        }


        int prevVal = item;
        int current;
        for (int i = idx; i < actualSize + 1; i++) {
            current = internalArray[i];
            internalArray[i] = prevVal;
            prevVal = current;
        }
        actualSize++;
    }

    /**
     * Amortized: O(1), Best Case: O(1), Worst Case: O(n)
     * @param item
     */
    public void add(int item) {
        insert(actualSize, item);
    }

    /**
     * T: O(n)
     * @param idx
     * @return
     * @throws ArrayIndexOutOfBoundsException
     */
    public int delete(int idx) throws ArrayIndexOutOfBoundsException {
        if (outOfArrayBounds(idx)) {
            throw new ArrayIndexOutOfBoundsException("Cannot delete...index outside of array bounds");
        }

        int deletedValue = internalArray[idx];
        internalArray[idx] = 0;
        for (int i = idx + 1; i < actualSize; i++) {
            internalArray[i - 1] = internalArray[i];
        }
        actualSize--;

        return deletedValue;
    }
    
    /**
     * T: O(n)
     */
    public void deleteAll() {
        for (int i = actualSize - 1; i > 0; i--) {
            internalArray[i] = 0;
        }

        actualSize = 0;
    }

    /**
     * O(1)
     * @return
     */
    public int size() {
        return actualSize;
    }

    private void growInternalArray() {
        int[] newArray = new int[2 * maxSize];
        maxSize *= 2;

        for (int i = actualSize - 1; i > 0; i--) {
            newArray[i] = internalArray[i];
        }

        internalArray = newArray;
    }

    private boolean outOfArrayBounds(int idx) {
        return idx < 0 || idx > actualSize;
    }
}
