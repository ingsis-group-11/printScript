package formatter;

import fileReader.FileReader;
import fileWriter.TestWriter;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FormatterTest {
  private final FileReader fileReader = new FileReader();

  @Test
  public void testAllActiveFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/allActiveInput.txt";
    String configPathRules = "src/test/resources/config/allActive.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/allActiveOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter, "1.0");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void testSpaceBeforeColonDisabledFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/spaceBeforeColonDisabledInput.txt";
    String configPathRules = "src/test/resources/config/spaceBeforeDisabled.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/spaceBeforeColonDisabledOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter,"1.0");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void testSpaceAfterColonDisabledFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/spaceAfterColonDisabledInput.txt";
    String configPathRules = "src/test/resources/config/spaceAfterDisabled.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/spaceAfterColonDisabledOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter, "1.0");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void testSpaceBetweenAssignDisabledFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/spaceBetweenAssignDisabledInput.txt";
    String configPathRules = "src/test/resources/config/spaceBetweenAssignDisabled.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/spaceBetweenAssignDisabledOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter, "1.0");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void testLineBreakAfterSemiColonFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/lineBreakAfterSemiColonInput.txt";
    String configPathRules = "src/test/resources/config/allDisabled.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/lineBreakAfterSemiColonOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter,"1.0");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void testAllDisabledFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/allDisabledInput.txt";
    String configPathRules = "src/test/resources/config/allDisabled.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/allDisabledOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter,"1.0");
    assertEquals(expected, testWriter.getOutput());
  }
}