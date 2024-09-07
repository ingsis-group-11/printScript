package linter;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SnakeCaseTests {

  @Test
  public void snakeCaseActiveSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/1.0/configSnakeCaseActive.json";
    String filePath = "src/test/resources/printScript/1.0/snakeCase.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(new FileInputStream(filePath), new FileInputStream(configFilePath),"1.0");
  }

  @Test
  public void snakeCaseActiveFailTest() {
    String configFilePath = "src/test/resources/config/1.0/configSnakeCaseActive.json";
    String filePath = "src/test/resources/printScript/1.0/camelCase.txt";

    LinterRunner linterRunner = new LinterRunner();
    assertThrows(
            RuntimeException.class,
            () -> {
              linterRunner.linterRun(new FileInputStream(filePath), new FileInputStream(configFilePath),"1.0");
            });
  }

  @Test
  public void snakeCaseDisabledSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/1.0/configAllDisabled.json";
    String filePath = "src/test/resources/printScript/1.0/snakeCase.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(new FileInputStream(filePath), new FileInputStream(configFilePath),"1.0");
  }
}
