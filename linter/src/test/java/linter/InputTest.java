package linter;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputTest {
  @Test
  public void inputActiveSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/configInputExpressionActive.json";
    String filePath = "src/test/resources/printScript/inputWithoutExpression.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(filePath, configFilePath);
  }

  @Test
  public void inputActiveFailTest() {
    String configFilePath = "src/test/resources/config/configInputExpressionActive.json";
    String filePath = "src/test/resources/printScript/inputWithExpression.txt";

    LinterRunner linterRunner = new LinterRunner();
    assertThrows(
            RuntimeException.class,
            () -> {
              linterRunner.linterRun(filePath, configFilePath);
            });
  }

  @Test
  public void inputDisabledSuccessWithoutExpressionTest() throws IOException {
    String configFilePath = "src/test/resources/config/configAllDisabled.json";
    String filePath = "src/test/resources/printScript/inputWithExpression.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(filePath, configFilePath);
  }

  @Test
  public void inputDisabledSuccessWithExpressionTest() throws IOException {
    String configFilePath = "src/test/resources/config/configAllDisabled.json";
    String filePath = "src/test/resources/printScript/inputWithoutExpression.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(filePath, configFilePath);
  }
}
