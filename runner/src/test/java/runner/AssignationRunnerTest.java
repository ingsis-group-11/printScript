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
    runner.run("src/test/resources/assignation/simpleNumberPrint.txt", "1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void simpleStringAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/simpleStringPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello World"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void complexStringAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/complexStringPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello & World! 20"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void additionNumberAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/additionNumberPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void additionIntDoubleAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/additionNumberDoubleAssignation.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("17.5"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void divisionIntAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/divisionIntAssignation.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("2.5"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void subtractionNumberAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/subtractionNumberPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void multiplicationNumberAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/multiplicationNumberPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void divisionNumberAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/divisionNumberPrint.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void additionStringAssignation() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/additionStringPrint.txt","1.0", printProvider);
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
          runner.run("src/test/resources/assignation/subtractionStringPrint.txt","1.0", printProvider);
        });
  }

  @Test
  public void multiplicationStringPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/multiplicationStringPrint.txt","1.0", printProvider);
        });
  }

  @Test
  public void divisionStringPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/divisionStringPrint.txt","1.0", printProvider);
        });
  }

  @Test
  public void additionStringNumberPrint() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/additionStringNumberPrint.txt","1.0", printProvider);
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
          runner.run("src/test/resources/assignation/subtractionStringNumberPrint.txt","1.0",printProvider);
        });
  }

  @Test
  public void multiplicationStringNumberPrint() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/multiplicationStringNumberPrint.txt","1.0", printProvider);
        });
  }

  @Test
  public void validReassignment() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/validReassignment.txt","1.0", printProvider);
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
          runner.run("src/test/resources/assignation/invalidReassignment.txt","1.0", printProvider);
        });
  }

  @Test
  public void printBeforeReassignment() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/printBeforeReassignment.txt","1.0", printProvider);
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
          runner.run("src/test/resources/assignation/divisionStringNumberPrint.txt","1.0", printProvider);
        });
  }

  @Test
  public void combinedNumberOperations() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/combinedNumberOperations.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("48"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void combinedNumberOperationsWithParenthesis() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/combinedNumberOperationsWithParenthesis.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("30"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void variableNumberAddition() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/variableNumberAddition.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("20"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void variableStringAddition() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/variableStringAddition.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello World"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void variableStringNumberAddition() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/variableStringNumberAddition.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("Hello 10"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void variableNumberSubtraction() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/variableNumberSubtraction.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("0"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void assignationReassignmentString() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/assignationReassignmentString.txt","1.0", printProvider);
    String expected = expectedTransformer.transform(List.of("John"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void assignationReassignmentNumber() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/assignationReassignmentNumber.txt","1.0", printProvider);
        });
  }

  @Test
  public void variableStringSubtraction() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/variableStringSubtraction.txt","1.0", printProvider);
        });
  }

  @Test
  public void variableStringNumberSubtraction() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/variableStringNumberSubtraction.txt","1.0", printProvider);
        });
  }

  @Test
  public void variableNumberMultiplication() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/variableNumberMultiplication.txt","1.0", printProvider);
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
          runner.run("src/test/resources/assignation/variableStringMultiplication.txt","1.0", printProvider);
        });
  }

  @Test
  public void variableStringNumberMultiplication() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/variableStringNumberMultiplication.txt","1.0", printProvider);
        });
  }

  @Test
  public void variableNumberDivision() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run("src/test/resources/assignation/variableNumberDivision.txt","1.0", printProvider);
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
          runner.run("src/test/resources/assignation/variableStringDivision.txt","1.0", printProvider);
        });
  }

  @Test
  public void variableStringNumberDivision() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/variableStringNumberDivision.txt","1.0", printProvider);
        });
  }

  @Test
  public void duplicateSameVariableAssignation() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run("src/test/resources/assignation/duplicateSameVariableAssignation.txt","1.0", printProvider);
        });
  }
}
