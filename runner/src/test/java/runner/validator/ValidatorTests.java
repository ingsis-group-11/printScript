package runner.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import runner.ValidationRunner;
import runner.printscript.observers.RunnerTestObserver;

public class ValidatorTests {
  @Test
  public void validatorVersionShouldFailTest() {
    RunnerTestObserver runnerTestObserver = new RunnerTestObserver();
    String filePath = "src/test/resources/validator/1.0/validatorTest.txt";
    ValidationRunner validationRunner = new ValidationRunner();
    validationRunner.setObservers(List.of(runnerTestObserver));
    assertThrows(
        RuntimeException.class,
        () -> {
          validationRunner.validate(new FileInputStream(filePath), "1.0");
        });
  }

  @Test
  public void validatorVersionShouldPassTest() throws IOException {
    RunnerTestObserver runnerTestObserver = new RunnerTestObserver();
    String filePath = "src/test/resources/validator/1.0/validatorTest.txt";
    ValidationRunner validationRunner = new ValidationRunner();
    validationRunner.setObservers(List.of(runnerTestObserver));
    validationRunner.validate(new FileInputStream(filePath), "1.1");
  }
}
