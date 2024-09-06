package linter;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReadInputTests {
  @Test
  public void readInputActiveSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/1.1/configReadInputActive.json";
    String filePath = "src/test/resources/readInput/readInputActiveSuccessTest.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(new FileInputStream(filePath), new FileInputStream(configFilePath), "1.1");
  }

  @Test
  public void readInputDisabledSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/1.1/configAllDisabled.json";
    String filePath = "src/test/resources/readInput/readInputActiveSuccessTest.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(new FileInputStream(filePath), new FileInputStream(configFilePath), "1.1");
  }

  @Test
  public void readInputActiveFailTest() {
    String configFilePath = "src/test/resources/config/1.1/configReadInputActive.json";
    String filePath = "src/test/resources/readInput/readInputActiveFailTest.txt";

    LinterRunner linterRunner = new LinterRunner();
    assertThrows(
            RuntimeException.class,
            () -> {
              linterRunner.linterRun(new FileInputStream(filePath), new FileInputStream(configFilePath),"1.1");
            });
  }

  @Test
  public void readInputActiveAndCamelCaseActiveSuccessTest() throws IOException {
    String configFilePath = "src/test/resources/config/1.1/configReadInputActiveAndCamelCaseActive.json";
    String filePath = "src/test/resources/readInput/readInputActiveAndCamelCaseActiveSuccessTest.txt";

    LinterRunner linterRunner = new LinterRunner();
    linterRunner.linterRun(new FileInputStream(filePath), new FileInputStream(configFilePath),"1.1");
  }
}
