package linter;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import runner.LinterRunner;

public class CamelCaseTests {

  @Test
  public void camelCaseActiveSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/1.0/configCamelCaseActive.json";
    String filePath = "src/test/resources/printScript/1.0/camelCase.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(
        new FileInputStream(filePath), new FileInputStream(configFilePath), "1.0");
  }

  @Test
  public void camelCaseActiveFailTest() {
    String configFilePath = "src/test/resources/config/1.0/configCamelCaseActive.json";
    String filePath = "src/test/resources/printScript/1.0/snakeCase.txt";

    LinterRunner linterRunner = new LinterRunner();
    assertThrows(
        RuntimeException.class,
        () -> {
          linterRunner.linterRun(
              new FileInputStream(filePath), new FileInputStream(configFilePath), "1.0");
        });
  }

  @Test
  public void camelCaseDisabledSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/1.0/configAllDisabled.json";
    String filePath = "src/test/resources/printScript/1.0/snakeCase.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(
        new FileInputStream(filePath), new FileInputStream(configFilePath), "1.0");
  }
}
