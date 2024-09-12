package runner.printscript;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import providers.inputprovider.TestInputProvider;
import providers.printprovider.TestPrintProvider;
import runner.Runner;

public class ReadInputTest {
  private final ExpectedTransformer expectedTransformer = new ExpectedTransformer();

  @Test
  public void simpleNumberInput() throws IOException {
    TestInputProvider testInputProvider = new TestInputProvider(List.of("20"));
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/input/simpleInput.txt"), "1.1", testInputProvider);
    assertFalse(testInputProvider.hasInputsToRead());
  }

  @Test
  public void simpleBooleanInput() throws IOException {
    TestInputProvider testInputProvider = new TestInputProvider(List.of("true"));
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/input/simpleInput.txt"), "1.1", testInputProvider);
    assertFalse(testInputProvider.hasInputsToRead());
  }

  @Test
  public void simpleStringInput() throws IOException {
    TestInputProvider testInputProvider = new TestInputProvider(List.of("Hello"));
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/input/simpleInput.txt"), "1.1", testInputProvider);
    assertFalse(testInputProvider.hasInputsToRead());
  }

  @Test
  public void multipleReadInput() throws IOException {
    TestInputProvider testInputProvider = new TestInputProvider(List.of("20", "Hello", "false"));
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/input/multipleReadInput.txt"),
        "1.1",
        testInputProvider);
    assertFalse(testInputProvider.hasInputsToRead());
  }

  @Test
  public void assignationNumberReadInput() throws IOException {
    TestInputProvider testInputProvider = new TestInputProvider(List.of("20"));
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/input/assignationNumberReadInput.txt"),
        "1.1",
        testInputProvider);
    assertFalse(testInputProvider.hasInputsToRead());
  }

  @Test
  public void assignationBooleanReadInput() throws IOException {
    TestInputProvider testInputProvider = new TestInputProvider(List.of("false"));
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/input/assignationBooleanReadInput.txt"),
        "1.1",
        testInputProvider);
    assertFalse(testInputProvider.hasInputsToRead());
  }

  @Test
  public void printNumberReadInput() throws IOException {
    TestInputProvider testInputProvider = new TestInputProvider(List.of("47.63"));
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    String expected = expectedTransformer.transform(List.of("47.63"));
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/input/printNumberReadInput.txt"),
        "1.1",
        testPrintProvider,
        testInputProvider);
    assertFalse(testInputProvider.hasInputsToRead());
    assertEquals(expected, testPrintProvider.getMessages().next());
  }
}
