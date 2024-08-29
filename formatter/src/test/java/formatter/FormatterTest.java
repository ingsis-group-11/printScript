package formatter;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FormatterTest {

  @Test
  public void testAllActiveFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/allActiveInput.txt";
    String outputFilePath = "src/test/resources/cases/allActiveOutput.txt";
    String configPathRules = "src/test/resources/config/allActive.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    formatterRunner.format(inputFilePath, outputFilePath, configPathRules);
  }

  @Test
  public void testSpaceBeforeColonDisabledFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/spaceBeforeColonDisabledInput.txt";
    String outputFilePath = "src/test/resources/cases/spaceBeforeColonDisabledOutput.txt";
    String configPathRules = "src/test/resources/config/spaceBeforeDisabled.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    formatterRunner.format(inputFilePath, outputFilePath, configPathRules);
  }

  @Test
  public void testSpaceAfterColonDisabledFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/spaceAfterColonDisabledInput.txt";
    String outputFilePath = "src/test/resources/cases/spaceAfterColonDisabledOutput.txt";
    String configPathRules = "src/test/resources/config/spaceAfterDisabled.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    formatterRunner.format(inputFilePath, outputFilePath, configPathRules);
  }

  @Test
  public void testSpaceBetweenAssignDisabledFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/spaceBetweenAssignDisabledInput.txt";
    String outputFilePath = "src/test/resources/cases/spaceBetweenAssignDisabledOutput.txt";
    String configPathRules = "src/test/resources/config/spaceBetweenAssignDisabled.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    formatterRunner.format(inputFilePath, outputFilePath, configPathRules);
  }

  @Test
  public void testLineBreakAfterSemiColonFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/lineBreakAfterSemiColonInput.txt";
    String outputFilePath = "src/test/resources/cases/lineBreakAfterSemiColonOutput.txt";
    String configPathRules = "src/test/resources/config/allActive.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    formatterRunner.format(inputFilePath, outputFilePath, configPathRules);
  }
}