package runner.printScript;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import providers.printProvider.TestPrintProvider;
import runner.Runner;

public class ConstAssignationTest {
  private final ExpectedTransformer expectedTransformer = new ExpectedTransformer();

  @Test
  public void constValidAssignationTest() throws IOException {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(
        new FileInputStream("src/test/resources/assignation/1.1/constValidAssignation.txt"),
        "1.1",
        printProvider);
    String expected = expectedTransformer.transform(List.of("15.7"));
    assertEquals(expected, printProvider.getMessages().next());
  }

  @Test
  public void constInvalidResignationTest() {
    TestPrintProvider printProvider = new TestPrintProvider();
    Runner runner = new Runner();
    assertThrows(
        RuntimeException.class,
        () -> {
          runner.run(
              new FileInputStream(
                  "src/test/resources/assignation/1.1/constInvalidResignationTest.txt"),
              "1.1",
              printProvider);
        });
  }
}
