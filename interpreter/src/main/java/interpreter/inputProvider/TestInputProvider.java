package interpreter.inputProvider;

import java.util.List;
import java.util.Iterator;

public class TestInputProvider implements InputProvider {
  private final Iterator<String> inputs;

  public TestInputProvider(List<String> inputs) {
    this.inputs = inputs.iterator();
  }

  @Override
  public String getInput(String message) {
    if (inputs.hasNext()) {
      return inputs.next();
    } else {
      throw new RuntimeException("No more inputs to read");
    }
  }
}