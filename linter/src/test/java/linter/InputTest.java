package linter;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class InputTest {
  @Test
  public void inputActiveSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/configInputExpressionActive.json";
    String filePath = "src/test/resources/printScript/inputWithoutExpression.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(filePath, configFilePath);
  }
}
