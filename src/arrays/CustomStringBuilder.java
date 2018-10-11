package arrays;

public class CustomStringBuilder {
  private char[] internalCharArray;
  private int maxLength;
  private int currentLength;

  public CustomStringBuilder() {
    this("");
  }

  public CustomStringBuilder(Object obj) {
    internalCharArray = toCharArray(obj);
    maxLength = internalCharArray.length;
    currentLength = internalCharArray.length;
  }

  public CustomStringBuilder append(Object obj) {
    return insert(currentLength, obj);
  }

  public int length() {
    return currentLength;
  }

  /**
   * insert obj.toString() at given startIdx
   *
   * @param startIdx
   * @param obj
   * @return
   * @throws StringIndexOutOfBoundsException
   */
  public CustomStringBuilder insert(int startIdx, Object obj) throws
      StringIndexOutOfBoundsException {
    if (startIdx < 0 || startIdx > currentLength) {
      throw new StringIndexOutOfBoundsException();
    }

    char[] otherCharArray = toCharArray(obj);
    int otherLength = otherCharArray.length;

    while (currentLength + otherLength > maxLength) {
      growInternalCharArray();
    }

    shiftCharactersRight(startIdx, otherLength);
    for (int i = 0; i < otherLength; i++) {
      internalCharArray[startIdx + i] = otherCharArray[i];
    }

    currentLength += otherLength;
    return this;
  }

  /**
   * Yields substring with given endpoints: [start, end)
   *
   * @param start
   * @param end
   * @return
   * @throws StringIndexOutOfBoundsException
   */
  public String substring(int start, int end) throws StringIndexOutOfBoundsException {
    if (start >= end || start < 0 || end > currentLength) {
      throw new StringIndexOutOfBoundsException();
    }

    char[] subCharArray = new char[end - start];

    for (int i = start; i < end; i++) {
      subCharArray[i - start] = internalCharArray[i];
    }

    return new String(subCharArray);
  }

  /**
   * Replaces chars with replacementString, lengthening internal string if necessary
   *
   * @param startIdx
   * @param replacementString
   * @return
   */
  public CustomStringBuilder replace(int startIdx, String replacementString) throws
      StringIndexOutOfBoundsException {
    if (startIdx < 0 || startIdx >= currentLength) {
      throw new StringIndexOutOfBoundsException();
    }

    char[] otherCharArray = toCharArray(replacementString);
    int otherLength = otherCharArray.length;

    while (startIdx + otherLength > maxLength) {
      growInternalCharArray();
    }

    int idx;
    for (idx = 0; idx < otherLength; idx++) {
      internalCharArray[startIdx + idx] = otherCharArray[idx];
    }

    if (startIdx + idx > currentLength) {
      currentLength = startIdx + idx;
    }

    return this;
  }

  @Override
  public String toString() {
    return new String(internalCharArray).trim();
  }

  private char[] toCharArray(Object obj) {
    String objString = obj.toString();
    Integer strLength = objString.length();

    char[] charArray = new char[strLength];
    for (int i = 0; i < strLength; i++) {
      charArray[i] = objString.charAt(i);
    }
    return charArray;
  }

  private void growInternalCharArray() {
    char[] newCharArray = new char[2 * maxLength];
    maxLength *= 2;

    for (int i = 0; i < currentLength; i++) {
      newCharArray[i] = internalCharArray[i];
    }

    internalCharArray = newCharArray;
  }

  private void shiftCharactersRight(Integer start, Integer offset) {
    char prev = 69;
    char current;
    for (int nShift = 0; nShift < offset; nShift++) {
      for (int idx = start + nShift; idx < currentLength + offset; idx++) {
        current = internalCharArray[idx];
        internalCharArray[idx] = prev;
        prev = current;
      }
    }
  }

}
