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
    runner.run("src/test/resources/print/simpleNumberPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void simpleStringPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/simpleStringPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello World"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void complexStringPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/complexStringPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello & World! 20"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void additionNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/additionNumberPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void subtractionNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/subtractionNumberPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void multiplicationNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/multiplicationNumberPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void divisionNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/divisionNumberPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void additionStringPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/additionStringPrint.txt","1.0", printProvider);
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
          runner.run("src/test/resources/print/subtractionStringPrint.txt","1.0", printProvider);
        });
  }

  @Test
  public void multiplicationStringPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/multiplicationStringPrint.txt","1.0", printProvider);
        });
  }

  @Test
  public void divisionStringPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/divisionStringPrint.txt","1.0", printProvider);
        });
  }

  @Test
  public void additionStringNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/additionStringNumberPrint.txt","1.0", printProvider);
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
          runner.run("src/test/resources/print/subtractionStringNumberPrint.txt","1.0", printProvider);
        });
  }

  @Test
  public void multiplicationStringNumberPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/multiplicationStringNumberPrint.txt","1.0", printProvider);
        });
  }

  @Test
  public void divisionStringNumberPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/print/divisionStringNumberPrint.txt","1.0", printProvider);
        });
  }

  @Test
  public void combinedNumberOperations() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/combinedNumberOperations.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("48"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void combinedNumberOperationsWithParenthesis() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/combinedNumberOperationsWithParenthesis.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("30"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void multiplePrints() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/print/multiplePrints.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("10.0", "Hello", "20.0", "World"));
    Iterator<String> actual = printProvider.getMessages();
    assertEquals(expected, actual.next() + actual.next() + actual.next() + actual.next());
  }
}
