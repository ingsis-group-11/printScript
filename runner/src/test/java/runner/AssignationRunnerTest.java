package runner;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class AssignationRunnerTest {
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

    // * PRINTS AND ASSIGNMENTS

    @Test public void simpleNumberAssignation() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/simpleNumberPrint.txt");
        String expected = expectedTransformer.transform(List.of("10"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void simpleStringAssignation() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/simpleStringPrint.txt");
        String expected = expectedTransformer.transform(List.of("Hello World"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void complexStringAssignation() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/complexStringPrint.txt");
        String expected = expectedTransformer.transform(List.of("Hello & World! 20"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void additionNumberAssignation() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/additionNumberPrint.txt");
        String expected = expectedTransformer.transform(List.of("10.0"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void subtractionNumberAssignation() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/subtractionNumberPrint.txt");
        String expected = expectedTransformer.transform(List.of("10.0"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void multiplicationNumberAssignation() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/multiplicationNumberPrint.txt");
        String expected = expectedTransformer.transform(List.of("10.0"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void divisionNumberAssignation() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/divisionNumberPrint.txt");
        String expected = expectedTransformer.transform(List.of("10.0"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void additionStringAssignation() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/additionStringPrint.txt");
        String expected = expectedTransformer.transform(List.of("Hello World"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void subtractionStringAssignation() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/subtractionStringPrint.txt");
        });
    }

    @Test public void multiplicationStringPrint() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/multiplicationStringPrint.txt");
        });
    }

    @Test public void divisionStringPrint() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/divisionStringPrint.txt");
        });
    }

    @Test public void additionStringNumberPrint() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/additionStringNumberPrint.txt");
        String expected = expectedTransformer.transform(List.of("Hello 10"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void subtractionStringNumberPrint() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/subtractionStringNumberPrint.txt");
        });
    }

    @Test public void multiplicationStringNumberPrint() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/multiplicationStringNumberPrint.txt");
        });
    }

    @Test public void validReassignment() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/validReassignment.txt");
        String expected = expectedTransformer.transform(List.of("Doe"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void invalidReassignment() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/invalidReassignment.txt");
        });
    }

    @Test public void printBeforeReassignment() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/printBeforeReassignment.txt");
        String expected = expectedTransformer.transform(List.of("John","Doe"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void divisionStringNumberPrint() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/divisionStringNumberPrint.txt");
        });
    }

    @Test public void combinedNumberOperations() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/combinedNumberOperations.txt");
        String expected = expectedTransformer.transform(List.of("48.0"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void combinedNumberOperationsWithParenthesis() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/combinedNumberOperationsWithParenthesis.txt");
        String expected = expectedTransformer.transform(List.of("30.0"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void variableNumberAddition() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/variableNumberAddition.txt");
        String expected = expectedTransformer.transform(List.of("20.0"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void variableStringAddition() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/variableStringAddition.txt");
        String expected = expectedTransformer.transform(List.of("Hello World"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void variableStringNumberAddition() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/variableStringNumberAddition.txt");
        String expected = expectedTransformer.transform(List.of("Hello 10"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void variableNumberSubtraction() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/variableNumberSubtraction.txt");
        String expected = expectedTransformer.transform(List.of("0.0"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void variableStringSubtraction() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/variableStringSubtraction.txt");
        });
    }

    @Test public void variableStringNumberSubtraction() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/variableStringNumberSubtraction.txt");
        });
    }

    @Test public void variableNumberMultiplication() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/variableNumberMultiplication.txt");
        String expected = expectedTransformer.transform(List.of("10.0"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void variableStringMultiplication() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/variableStringMultiplication.txt");
        });
    }

    @Test public void variableStringNumberMultiplication() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/variableStringNumberMultiplication.txt");
        });
    }

    @Test public void variableNumberDivision() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/assignation/variableNumberDivision.txt");
        String expected = expectedTransformer.transform(List.of("10.0"));
        assertEquals(expected, outContent.toString());
    }

    @Test public void variableStringDivision() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/variableStringDivision.txt");
        });
    }

    @Test public void variableStringNumberDivision() {
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/variableStringNumberDivision.txt");
        });
    }

    @Test public void duplicateSameVariableAssignation(){
        Runner runner = new Runner();
        assertThrows(RuntimeException.class, () -> {
            runner.run("src/test/resources/assignation/duplicateSameVariableAssignation.txt");
        });
    }
}
