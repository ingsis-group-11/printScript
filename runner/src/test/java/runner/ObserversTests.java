package runner;

import org.junit.jupiter.api.Test;
import parser.Observer;
import providers.inputProvider.TestInputProvider;
import providers.printProvider.TestPrintProvider;
import runner.observers.RunnerTestObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ObserversTests {
  private final ExpectedTransformer expectedTransformer = new ExpectedTransformer();

  @Test
  public void observerTest() throws IOException {
    RunnerTestObserver runnerTestObserver = new RunnerTestObserver();
    List<Observer> observers = List.of(runnerTestObserver);
    TestInputProvider testInputProvider = new TestInputProvider(List.of("47.63"));
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    String expected = expectedTransformer.transform(List.of("47.63"));
    Runner runner = new Runner();
    runner.run(new FileInputStream("src/test/resources/input/printNumberReadInput.txt"),"1.1",testPrintProvider, testInputProvider, observers);
    assertFalse(testInputProvider.hasInputsToRead());
    assertEquals(expected, testPrintProvider.getMessages().next());
    assertEquals(List.of(".", "."), runnerTestObserver.getMessages());
  }
}