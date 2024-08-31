package interpreter.providers.printProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestPrintProvider implements PrintProvider {
  private final List<String> messages = new ArrayList<>();

  @Override
  public void print(String message) {
    messages.add(message);
  }

  public Iterator<String> getMessages() {
    return messages.iterator();
  }
}

