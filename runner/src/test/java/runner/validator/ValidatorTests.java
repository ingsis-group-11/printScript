package runner.validator;

import org.junit.jupiter.api.Test;
import runner.ValidationRunner;
import runner.printScript.observers.RunnerTestObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorTests {
  @Test
  public void validatorVersionShouldFailTest() {
    RunnerTestObserver runnerTestObserver = new RunnerTestObserver();
    String filePath = "src/test/resources/validator/1.0/validatorTest.txt";
    ValidationRunner validationRunner = new ValidationRunner();
    assertThrows(
            RuntimeException.class,
            () -> {
              validationRunner.validate(new FileInputStream(filePath), "1.0", runnerTestObserver);
            });
  }

  @Test
  public void validatorVersionShouldPassTest() throws IOException {
    RunnerTestObserver runnerTestObserver = new RunnerTestObserver();
    String filePath = "src/test/resources/validator/1.0/validatorTest.txt";
    ValidationRunner validationRunner = new ValidationRunner();
    validationRunner.validate(new FileInputStream(filePath), "1.1", runnerTestObserver);
  }
}
