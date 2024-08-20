package runner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrintRunnerTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final ExpectedTransformer expectedTransformer = new ExpectedTransformer();

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
    outContent.reset();
  }

  // * ONLY PRINTS

  @Test
  public void simpleNumberPrint() throws IOException {
    Runner runner = new Runner();
    runner.run("src/test/resources/print/simpleNumberPrint.txt");
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, outContent.toString());
  }

  @Test
  public void simpleStringPrint() throws IOException {
    Runner runner = new Runner();
    runner.run("src/test/resources/print/simpleStringPrint.txt");
    String expected = expectedTransformer.transform(List.of("Hello World"));
    assertEquals(expected, outContent.toString());
  }

  @Test
  public void complexStringPrint() throws IOException {
    Runner runner = new Runner();
    runner.run("src/test/resources/print/complexStringPrint.txt");
    String expected = expectedTransformer.transform(List.of("Hello & World! 20"));
    assertEquals(expected, outContent.toString());
  }

  @Test
  public void additionNumberPrint() throws IOException {
    Runner runner = new Runner();
    runner.run("src/test/resources/print/additionNumberPrint.txt");
    String expected = expectedTransformer.transform(List.of("10.0"));
    assertEquals(expected, outContent.toString());
  }

  @Test
  public void subtractionNumberPrint() throws IOException {
    Runner runner = new Runner();
    runner.run("src/test/resources/print/subtractionNumberPrint.txt");
    String expected = expectedTransformer.transform(List.of("10.0"));
    assertEquals(expected, outContent.toString());
  }

  @Test
  public void multiplicationNumberPrint() throws IOException {
    Runner runner = new Runner();
    runner.run("src/test/resources/print/multiplicationNumberPrint.txt");
    String expected = expectedTransformer.transform(List.of("10.0"));
    assertEquals(expected, outContent.toString());
  }

  @Test
  public void divisionNumberPrint() throws IOException {
    Runner runner = new Runner();
    runner.run("src/test/resources/print/divisionNumberPrint.txt");
    String expected = expectedTransformer.transform(List.of("10.0"));
    assertEquals(expected, outContent.toString());
  }

  @Test
  public void additionStringPrint() throws IOException {
    Runner runner = new Runner();
    runner.run("src/test/resources/print/additionStringPrint.txt");
    String expected = expectedTransformer.transform(List.of("Hello World"));
    assertEquals(expected, outContent.toString());
  }

  @Test
  public void subtractionStringPrint() {
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/subtractionStringPrint.txt");
        });
  }

  @Test
  public void multiplicationStringPrint() {
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/multiplicationStringPrint.txt");
        });
  }

  @Test
  public void divisionStringPrint() {
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/divisionStringPrint.txt");
        });
  }

  @Test
  public void additionStringNumberPrint() throws IOException {
    Runner runner = new Runner();
    runner.run("src/test/resources/print/additionStringNumberPrint.txt");
    String expected = expectedTransformer.transform(List.of("Hello 10"));
    assertEquals(expected, outContent.toString());
  }

  @Test
  public void subtractionStringNumberPrint() {
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/subtractionStringNumberPrint.txt");
        });
  }

  @Test
  public void multiplicationStringNumberPrint() {
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/multiplicationStringNumberPrint.txt");
        });
  }

  @Test
  public void divisionStringNumberPrint() {
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/divisionStringNumberPrint.txt");
        });
  }

  @Test
  public void combinedNumberOperations() throws IOException {
    Runner runner = new Runner();
    runner.run("src/test/resources/print/combinedNumberOperations.txt");
    String expected = expectedTransformer.transform(List.of("48.0"));
    assertEquals(expected, outContent.toString());
  }

  @Test
  public void combinedNumberOperationsWithParenthesis() throws IOException {
    Runner runner = new Runner();
    runner.run("src/test/resources/print/combinedNumberOperationsWithParenthesis.txt");
    String expected = expectedTransformer.transform(List.of("30.0"));
    assertEquals(expected, outContent.toString());
  }
}
