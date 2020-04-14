package lukuvinkit.util;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TagParserTest {

  @Test
  public void stringIsParsed() {
    String s = "abc,cba";
    List<String> expected = new ArrayList<>();
    expected.add("abc");
    expected.add("cba");
    List<String> tags = TagParser.stringToList(s);
    assertEquals(expected, tags);
  }

  @Test
  public void whitespaceRemoved() {
    String s = "  abc  , cba  ";
    List<String> expected = new ArrayList<>();
    expected.add("abc");
    expected.add("cba");
    List<String> tags = TagParser.stringToList(s);
    assertEquals(expected, tags);
  }

  @Test
  public void emptyStringReturnsEmptyList() {
    String s = "";
    List<String> tags = TagParser.stringToList(s);
    assertTrue(tags.isEmpty());
  }

  @Test
  public void listIsParsed() {
    List<String> tags = new ArrayList<>();
    tags.add("abc");
    tags.add("cba");
    String s = TagParser.listToString(tags);
    assertEquals("abc,cba", s);
  }

  @Test
  public void emptyListReturnsEmptyString() {
    String s = TagParser.listToString(new ArrayList<>());
    assertTrue(s.isEmpty());
  }
}
