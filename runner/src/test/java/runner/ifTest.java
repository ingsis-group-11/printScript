package runner;

import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.IOException;

public class ifTest {
  private final ExpectedTransformer expectedTransformer = new ExpectedTransformer();

  @Test
  public void ifFalseCondition() throws IOException {
    Runner runner = new Runner();
    runner.run(new FileInputStream("src/test/resources/ifStatement/ifFalseCondition.txt"),"1.1");

  }
}
