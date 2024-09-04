package runner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import providers.printProvider.TestPrintProvider;

public class AssignationRunnerTest {
  private final ExpectedTransformer expectedTransformer = new ExpectedTransformer();


  // * PRINTS AND ASSIGNMENTS

  @Test
  public void simpleNumberAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/simpleNumberPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void simpleStringAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/simpleStringPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello World"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void complexStringAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/complexStringPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello & World! 20"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void additionNumberAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/additionNumberPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void additionIntDoubleAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/additionNumberDoubleAssignation.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("17.5"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void divisionIntAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/divisionIntAssignation.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("2.5"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void subtractionNumberAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/subtractionNumberPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void multiplicationNumberAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/multiplicationNumberPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void divisionNumberAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/divisionNumberPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void additionStringAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/additionStringPrint.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello World"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void subtractionStringAssignation() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/subtractionStringPrint.txt", printProvider);
        });
  }

  @Test
  public void multiplicationStringPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/multiplicationStringPrint.txt", printProvider);
        });
  }

  @Test
  public void divisionStringPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/divisionStringPrint.txt", printProvider);
        });
  }

  @Test
  public void additionStringNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/additionStringNumberPrint.txt", printProvider);
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
          runner.run("src/test/resources/assignation/subtractionStringNumberPrint.txt", printProvider);
        });
  }

  @Test
  public void multiplicationStringNumberPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/multiplicationStringNumberPrint.txt", printProvider);
        });
  }

  @Test
  public void validReassignment() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/validReassignment.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("Doe"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void invalidReassignment() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/invalidReassignment.txt", printProvider);
        });
  }

  @Test
  public void printBeforeReassignment() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/printBeforeReassignment.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("John", "Doe"));
    Iterator<String> actual = printProvider.getMessages();
    assertEquals(expected, actual.next() + actual.next());
  }

  @Test
  public void divisionStringNumberPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/divisionStringNumberPrint.txt", printProvider);
        });
  }

  @Test
  public void combinedNumberOperations() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/combinedNumberOperations.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("48"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void combinedNumberOperationsWithParenthesis() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/combinedNumberOperationsWithParenthesis.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("30"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void variableNumberAddition() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/variableNumberAddition.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("20"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void variableStringAddition() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/variableStringAddition.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello World"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void variableStringNumberAddition() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/variableStringNumberAddition.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello 10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void variableNumberSubtraction() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/variableNumberSubtraction.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("0"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void variableStringSubtraction() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/variableStringSubtraction.txt", printProvider);
        });
  }

  @Test
  public void variableStringNumberSubtraction() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/variableStringNumberSubtraction.txt", printProvider);
        });
  }

  @Test
  public void variableNumberMultiplication() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/variableNumberMultiplication.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void variableStringMultiplication() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/variableStringMultiplication.txt", printProvider);
        });
  }

  @Test
  public void variableStringNumberMultiplication() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/variableStringNumberMultiplication.txt", printProvider);
        });
  }

  @Test
  public void variableNumberDivision() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/variableNumberDivision.txt", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void variableStringDivision() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/variableStringDivision.txt", printProvider);
        });
  }

  @Test
  public void variableStringNumberDivision() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/variableStringNumberDivision.txt", printProvider);
        });
  }

  @Test
  public void duplicateSameVariableAssignation() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/duplicateSameVariableAssignation.txt", printProvider);
        });
  }
}
