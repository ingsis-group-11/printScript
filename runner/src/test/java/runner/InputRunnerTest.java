package runner;

import interpreter.providers.inputProvider.TestInputProvider;
import interpreter.providers.printProvider.TestPrintProvider;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InputRunnerTest {
  private final ExpectedTransformer expectedTransformer = new ExpectedTransformer();

  // * ONLY INPUTS

  @Test
  public void simpleInputRead() throws IOException {
    TestInputProvider inputProvider = new TestInputProvider(List.of("Hello"));
    Runner runner = new Runner();
    runner.run("src/test/resources/inputRead/simpleInputRead.txt", inputProvider);
    assertFalse(inputProvider.hasInputsToRead());
  }

  @Test
  public void multipleInputRead() throws IOException {
    TestInputProvider inputProvider = new TestInputProvider(List.of("Hello", "World", "20"));
    Runner runner = new Runner();
    runner.run("src/test/resources/inputRead/multipleInputRead.txt", inputProvider);
    assertFalse(inputProvider.hasInputsToRead());
  }

  @Test
  public void simpleReadInputWithExpression() throws IOException {
    TestInputProvider inputProvider = new TestInputProvider(List.of("Hello"));
    Runner runner = new Runner();
    runner.run("src/test/resources/inputRead/simpleReadInputWithExpression.txt", inputProvider);
    assertFalse(inputProvider.hasInputsToRead());
  }

  // * INPUTS AND ASSIGNMENTS

  @Test
  public void simpleInputReadAssignment() throws IOException {
    TestInputProvider inputProvider = new TestInputProvider(List.of("Hello"));
    Runner runner = new Runner();
    runner.run("src/test/resources/inputRead/simpleInputReadAssignment.txt", inputProvider);
    assertFalse(inputProvider.hasInputsToRead());
  }

  @Test
  public void multipleInputReadAssignment() throws IOException {
    TestInputProvider inputProvider = new TestInputProvider(List.of("Hello", "World", "20"));
    Runner runner = new Runner();
    runner.run("src/test/resources/inputRead/multipleInputReadAssignment.txt", inputProvider);
    assertFalse(inputProvider.hasInputsToRead());
  }

  @Test
  public void simpleReadInputWithExpressionAssignment() throws IOException {
    TestInputProvider inputProvider = new TestInputProvider(List.of("Hello"));
    Runner runner = new Runner();
    runner.run("src/test/resources/inputRead/simpleReadInputWithExpressionAssignment.txt", inputProvider);
    assertFalse(inputProvider.hasInputsToRead());
  }

  @Test
  public void simpleReadInputPrint() throws IOException {
    TestInputProvider inputProvider = new TestInputProvider(List.of("Hello"));
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/inputRead/simpleReadInputPrint.txt", inputProvider, printProvider);
    assertFalse(inputProvider.hasInputsToRead());
    assertEquals("Hello\n", printProvider.getMessages().next());
  }

  @Test
  public void simpleReadInputPrintAssignment() throws IOException {
    TestInputProvider inputProvider = new TestInputProvider(List.of("Hello"));
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/inputRead/simpleReadInputPrintAssignment.txt", inputProvider, printProvider);
    assertFalse(inputProvider.hasInputsToRead());
    assertEquals("Hello\n", printProvider.getMessages().next());
  }
}
