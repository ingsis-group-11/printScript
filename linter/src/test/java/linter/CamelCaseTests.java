package linter;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CamelCaseTests {

  @Test
  public void camelCaseActiveSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/configCamelCaseActive.json";
    String filePath = "src/test/resources/printScript/camelCase.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(filePath, configFilePath,"1.0");
  }

  @Test
  public void camelCaseActiveFailTest() {
    String configFilePath = "src/test/resources/config/configCamelCaseActive.json";
    String filePath = "src/test/resources/printScript/snakeCase.txt";

    LinterRunner linterRunner = new LinterRunner();
    assertThrows(
            RuntimeException.class,
            () -> {
              linterRunner.linterRun(filePath, configFilePath,"1.0");
            });
  }

  @Test
  public void camelCaseDisabledSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/configAllDisabled.json";
    String filePath = "src/test/resources/printScript/snakeCase.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(filePath, configFilePath,"1.0");
  }
}
