package linter;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrintTest {

  @Test
  public void printActiveSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/1.0/configPrintExpressionActive.json";
    String filePath = "src/test/resources/printScript/1.0/printWithoutExpression.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(new FileInputStream(filePath), new FileInputStream(configFilePath),"1.0");
  }

  @Test
  public void PrintActiveFailTest() {
    String configFilePath = "src/test/resources/config/1.0/configPrintExpressionActive.json";
    String filePath = "src/test/resources/printScript/1.0/printWithExpression.txt";

    LinterRunner linterRunner = new LinterRunner();
    assertThrows(
            RuntimeException.class,
            () -> {
              linterRunner.linterRun(new FileInputStream(filePath), new FileInputStream(configFilePath),"1.0");
            });
  }

  @Test
  public void printDisabledSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/1.0/configAllDisabled.json";
    String filePath = "src/test/resources/printScript/1.0/printWithExpression.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(new FileInputStream(filePath), new FileInputStream(configFilePath),"1.0");
  }
}
