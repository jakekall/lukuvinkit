package lukuvinkit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagParser {

  public static String listToString(List<String> tags) {
    return String.join(",", tags);
  }

  public static List<String> stringToList(String tags) {
    if (tags.trim().isEmpty()) {
      return new ArrayList<>();
    }
    return new ArrayList<>(Arrays.asList(tags.split("\\s*,\\s*")));
  }

  private TagParser() {
  }
}
