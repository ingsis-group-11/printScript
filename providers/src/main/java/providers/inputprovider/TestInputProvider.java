package providers.inputprovider;

import java.util.Iterator;
import java.util.List;

public class TestInputProvider implements InputProvider {
  private final Iterator<String> inputs;

  public TestInputProvider(List<String> inputs) {
    this.inputs = inputs.iterator();
  }

  public TestInputProvider() {
    this.inputs = List.of("").iterator();
  }

  @Override
  public String getInput(String message) {
    if (inputs.hasNext()) {
      return inputs.next();
    } else {
      throw new RuntimeException("No more inputs to read");
    }
  }

  public boolean hasInputsToRead() {
    return inputs.hasNext();
  }
}
