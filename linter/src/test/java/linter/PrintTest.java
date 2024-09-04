package linter;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrintTest {

  @Test
  public void printActiveSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/configPrintExpressionActive.json";
    String filePath = "src/test/resources/printScript/printWithoutExpression.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(filePath, configFilePath,"1.0");
  }

  @Test
  public void PrintActiveFailTest() {
    String configFilePath = "src/test/resources/config/configPrintExpressionActive.json";
    String filePath = "src/test/resources/printScript/printWithExpression.txt";

    LinterRunner linterRunner = new LinterRunner();
    assertThrows(
            RuntimeException.class,
            () -> {
              linterRunner.linterRun(filePath, configFilePath,"1.0");
            });
  }

  @Test
  public void printDisabledSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/configAllDisabled.json";
    String filePath = "src/test/resources/printScript/printWithExpression.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(filePath, configFilePath,"1.0");
  }
}
