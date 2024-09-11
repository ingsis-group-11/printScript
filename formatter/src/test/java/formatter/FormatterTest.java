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
    String inputFilePath = "src/test/resources/cases/1.0/allActiveInput.txt";
    String configPathRules = "src/test/resources/config/1.0/allActive.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/1.0/allActiveOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter,
        "1.0");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void testSpaceBeforeColonDisabledFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/1.0/spaceBeforeColonDisabledInput.txt";
    String configPathRules = "src/test/resources/config/1.0/spaceBeforeDisabled.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/1.0/spaceBeforeColonDisabledOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter,"1.0");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void testSpaceAfterColonDisabledFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/1.0/spaceAfterColonDisabledInput.txt";
    String configPathRules = "src/test/resources/config/1.0/spaceAfterDisabled.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/1.0/spaceAfterColonDisabledOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter, "1.0");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void testSpaceBetweenAssignDisabledFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/1.0/spaceBetweenAssignDisabledInput.txt";
    String configPathRules = "src/test/resources/config/1.0/spaceBetweenAssignDisabled.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/1.0/spaceBetweenAssignDisabledOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter, "1.0");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void testAllDisabledFile() throws IOException {
    String inputFilePath = "src/test/resources/cases/1.0/allDisabledInput.txt";
    String configPathRules = "src/test/resources/config/1.0/allDisabled.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/1.0/allDisabledOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter,"1.0");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void testSpacesInOperator() throws IOException {
    String inputFilePath = "src/test/resources/cases/1.0/spacesInOperatorInput.txt";
    String configPathRules = "src/test/resources/config/1.0/allActive.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/1.0/spacesInOperatorOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter,"1.0");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void testLineBreakAfterPrint() throws IOException {
    String inputFilePath = "src/test/resources/cases/1.0/lineBreakAfterPrintInput.txt";
    String configPathRules = "src/test/resources/config/1.0/allActive.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/1.0/lineBreakAfterPrintOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter,"1.0");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void testBracketOnSameLineAsif() throws IOException {
    String inputFilePath = "src/test/resources/cases/1.1/bracketOnSameLineAsifInput.txt";
    String configPathRules = "src/test/resources/config/1.1/allActive.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/1.1/bracketOnSameLineAsifOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter,"1.1");
    assertEquals(expected, testWriter.getOutput());
  }

  @Test
  public void test2IndentationInsideIf() throws IOException {
    String inputFilePath = "src/test/resources/cases/1.1/2indentationSpaceInput.txt";
    String configPathRules = "src/test/resources/config/1.1/allActive.json";

    FormatterRunner formatterRunner = new FormatterRunner();
    TestWriter testWriter = new TestWriter();
    String expected = fileReader.read("src/test/resources/cases/1.1/2indentationSpaceOutput.txt");
    formatterRunner.format(new FileInputStream(inputFilePath), new FileInputStream(configPathRules), testWriter,"1.1");
    assertEquals(expected, testWriter.getOutput());
  }
}