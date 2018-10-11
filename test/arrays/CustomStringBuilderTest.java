package arrays;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomStringBuilderTest {
  private static final String TEST_STRING = "testString";
  private static final String SHORT_STRING = "ex";

  CustomStringBuilder testSubject;

  @BeforeEach
  void init() {
    testSubject = new CustomStringBuilder(TEST_STRING);
  }

  @Test
  @DisplayName("::toString reflects internal string")
  void toStringTest() {
    assertEquals(TEST_STRING, testSubject.toString());
  }

  @Test
  @DisplayName("::append adds string of any length")
  void appendTest() {
    CustomStringBuilder stringBuilder1 = new CustomStringBuilder(SHORT_STRING);
    String noLengthStr = "";
    String result1 = stringBuilder1.append(noLengthStr).toString();

    CustomStringBuilder stringBuilder2 = new CustomStringBuilder(SHORT_STRING);
    String shortLengthStr = "short";
    String result2 = stringBuilder2.append(shortLengthStr).toString();

    CustomStringBuilder stringBuilder3 = new CustomStringBuilder(SHORT_STRING);
    String longLengthStr = "this is a very long string compared to the original one";
    String result3 = stringBuilder3.append(longLengthStr).toString();


    Assertions.assertAll(
        () -> Assertions.assertEquals(SHORT_STRING, result1),
        () -> Assertions.assertEquals(SHORT_STRING + shortLengthStr, result2),
        () -> Assertions.assertEquals(SHORT_STRING + longLengthStr, result3)
    );
  }

  @Test
  @DisplayName("::insert")
  void insertTest() throws StringIndexOutOfBoundsException{
    String otherString = "other";

    CustomStringBuilder stringBuilder1 = new CustomStringBuilder(TEST_STRING);
    String result1 = stringBuilder1.insert(0, otherString).toString();

    CustomStringBuilder stringBuilder2 = new CustomStringBuilder(TEST_STRING);
    String result2 = stringBuilder2.insert(4, otherString).toString();

    CustomStringBuilder stringBuilder3 = new CustomStringBuilder(TEST_STRING);
    String result3 = stringBuilder3.insert(10, otherString).toString();

    Assertions.assertAll(
        () -> Assertions.assertEquals(otherString + TEST_STRING, result1),
        () -> Assertions.assertEquals("test" + otherString + "String", result2),
        () -> Assertions.assertEquals(TEST_STRING + otherString, result3)
    );
  }

  @Test
  @DisplayName("::substring")
  void subStringTest() {
    Assertions.assertAll(
        () -> assertEquals("stSt", testSubject.substring(2, 6)),
        () -> assertThrows(StringIndexOutOfBoundsException.class, () -> testSubject.substring(5, 5)),
        () -> assertThrows(StringIndexOutOfBoundsException.class, () -> testSubject.substring(-1, 5)),
        () -> assertThrows(StringIndexOutOfBoundsException.class, () -> testSubject.substring(5, 20))
    );
  }

  @Test
  @DisplayName("::replace")
  void replace() {
    String actualValue = testSubject.replace(4, "ing is fun").toString();
    String expectedValue = "testing is fun";

    Assertions.assertAll(
        () -> assertEquals(expectedValue, actualValue),
        () -> assertEquals(expectedValue.length(), testSubject.length()),
        () -> assertThrows(StringIndexOutOfBoundsException.class,
            () -> testSubject.replace(-1, "whatever")),
        () -> assertThrows(StringIndexOutOfBoundsException.class,
            () -> testSubject.replace(expectedValue.length(), "whatever"))
    );
  }
}