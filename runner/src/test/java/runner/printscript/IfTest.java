package runner.printscript;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import providers.printprovider.TestPrintProvider;
import runner.Runner;

public class IfTest {
  private final ExpectedTransformer expectedTransformer = new ExpectedTransformer();

  @Test
  public void ifFalseCondition() throws IOException {
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/ifStatement/ifFalseCondition.txt"),
        "1.1",
        testPrintProvider);
    String expected = expectedTransformer.transform(List.of("outside of conditional"));
    assertEquals(expected, testPrintProvider.getMessages().next());
  }

  @Test
  public void ifTrueCondition() throws IOException {
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/ifStatement/ifTrueCondition.txt"),
        "1.1",
        testPrintProvider);
    String expected = expectedTransformer.transform(List.of("if statement working correctly"));
    assertEquals(expected, testPrintProvider.getMessages().next());
  }

  @Test
  public void ifPrint() throws IOException {
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/ifStatement/ifPrint.txt"),
        "1.1",
        testPrintProvider);
    String expected = expectedTransformer.transform(List.of("else statement working correctly"));
    Iterator<String> messages = testPrintProvider.getMessages();
    assertEquals(expected, messages.next());
    String expected2 = expectedTransformer.transform(List.of("second print inside if statement"));
    assertEquals(expected2, messages.next());
    String expected3 = expectedTransformer.transform(List.of("outside of conditional"));
    assertEquals(expected3, messages.next());
  }

  @Test
  public void elsePrint() throws IOException {
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/ifStatement/elsePrint.txt"),
        "1.1",
        testPrintProvider);
    String expected = expectedTransformer.transform(List.of("else statement working correctly"));
    Iterator<String> messages = testPrintProvider.getMessages();
    assertEquals(expected, messages.next());
    String expected2 = expectedTransformer.transform(List.of("outside of conditional"));
    assertEquals(expected2, messages.next());
  }

  @Test
  public void nestedIf() throws IOException {
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/ifStatement/nestedIf.txt"),
        "1.1",
        testPrintProvider);

    Iterator<String> messages = testPrintProvider.getMessages();
    String expected = expectedTransformer.transform(List.of("nested if working correctly"));

    assertEquals(expected, messages.next());
  }

  @Test
  public void printVariableOutsideScope() throws IOException {
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run(
              new FileInputStream("src/test/resources/ifStatement/printVariableOutsideScope.txt"),
              "1.1",
              testPrintProvider);
        });
  }

  @Test
  public void completeIfElse() throws IOException {
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/ifStatement/completeIfElse.txt"),
        "1.1",
        testPrintProvider);
  }
}
