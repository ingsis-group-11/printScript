package runner;

import java.util.List;

public class ExpectedTransformer {
  public String transform(List<String> expected) {
    StringBuilder result = new StringBuilder();
    for (String line : expected) {
      result.append(line).append(System.lineSeparator());
    }
    return result.toString();
  }
}
