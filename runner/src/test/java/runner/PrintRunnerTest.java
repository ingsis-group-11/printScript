package runner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import providers.printProvider.TestPrintProvider;

public class PrintRunnerTest {
  private final ExpectedTransformer expectedTransformer = new ExpectedTransformer();

  // * ONLY PRINTS

  @Test
  public void simpleNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/simpleNumberPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void simpleStringPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/simpleStringPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello World"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void complexStringPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/complexStringPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello & World! 20"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void additionNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/additionNumberPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10.0"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void subtractionNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/subtractionNumberPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10.0"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void multiplicationNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/multiplicationNumberPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10.0"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void divisionNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/divisionNumberPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10.0"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void additionStringPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/additionStringPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello World"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void subtractionStringPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/subtractionStringPrint.txt", printProvider);
        });
  }

  @Test
  public void multiplicationStringPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/multiplicationStringPrint.txt", printProvider);
        });
  }

  @Test
  public void divisionStringPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/divisionStringPrint.txt", printProvider);
        });
  }

  @Test
  public void additionStringNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/additionStringNumberPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello 10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void subtractionStringNumberPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/subtractionStringNumberPrint.txt", printProvider);
        });
  }

  @Test
  public void multiplicationStringNumberPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/multiplicationStringNumberPrint.txt", printProvider);
        });
  }

  @Test
  public void divisionStringNumberPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/divisionStringNumberPrint.txt", printProvider);
        });
  }

  @Test
  public void combinedNumberOperations() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/combinedNumberOperations.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("48.0"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void combinedNumberOperationsWithParenthesis() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/combinedNumberOperationsWithParenthesis.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("30.0"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void multiplePrints() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/multiplePrints.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10.0", "Hello", "20.0", "World"));
    Iterator<String> actual = printProvider.getMessages();
    assertEquals(expected, actual.next() + actual.next() + actual.next() + actual.next());
  }
}
