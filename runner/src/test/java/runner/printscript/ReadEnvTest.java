package runner.printscript;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import providers.printprovider.TestPrintProvider;
import runner.Runner;

public class ReadEnvTest {
  private final ExpectedTransformer expectedTransformer = new ExpectedTransformer();

  @Test
  public void simpleReadEnvWithoutAssignationSuccess() throws IOException {
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/readEnv/simpleReadEnvWithoutAssignation.txt"),
        "1.1");
  }

  @Test
  public void printReadEnvSuccess() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/readEnv/printReadEnvSuccess.txt"),
        "1.1",
        printProvider);
    String expected = expectedTransformer.transform(List.of("Test variable"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void printReadEnvFail() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run(
              new FileInputStream("src/test/resources/readEnv/printReadEnvFail.txt"),
              "1.1",
              printProvider);
        });
  }

  @Test
  public void assignationPrintReadEnvSuccess() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/readEnv/assignationPrintReadEnvSuccess.txt"),
        "1.1",
        printProvider);
    String expected = expectedTransformer.transform(List.of("Test variable"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void multipleAssignationPrintReadEnvSuccess() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(
        new FileInputStream(
            "src/test/resources/readEnv/multipleAssignationPrintReadEnvSuccess.txt"),
        "1.1",
        printProvider);
    String expected = expectedTransformer.transform(List.of("Test variable Another test variable"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void assignationToNumberReadEnvFail() {
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run(
              new FileInputStream("src/test/resources/readEnv/assignationToNumberReadEnvFail.txt"),
              "1.1");
        });
  }

  @Test
  public void assignationToBooleanReadEnvFail() {
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
        });
  }
}
