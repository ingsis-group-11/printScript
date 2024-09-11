package runner;

import org.junit.jupiter.api.Test;
import providers.printProvider.TestPrintProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ifTest {
  private final ExpectedTransformer expectedTransformer = new ExpectedTransformer();

  @Test
  public void ifFalseCondition() throws IOException {
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(new FileInputStream("src/test/resources/ifStatement/ifFalseCondition.txt"),"1.1", testPrintProvider);
    String expected = expectedTransformer.transform(List.of("outside of conditional"));
    assertEquals(expected, testPrintProvider.getMessages().next());
  }

  @Test
  public void ifTrueCondition() throws IOException {
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(new FileInputStream("src/test/resources/ifStatement/ifTrueCondition.txt"),"1.1");
    String expected = expectedTransformer.transform(List.of("if statement working correctly"));
    assertEquals(expected, testPrintProvider.getMessages().next());
  }

  @Test
  public void ifPrint() throws IOException {
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(new FileInputStream("src/test/resources/ifStatement/ifPrint.txt"),"1.1");

  }

  @Test
  public void elsePrint() throws IOException {
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    Runner runner = new Runner();
    runner.run(new FileInputStream("src/test/resources/ifStatement/elsePrint.txt"),"1.1");

  }
}
