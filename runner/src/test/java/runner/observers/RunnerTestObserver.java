package runner.observers;

import parser.Observer;

import java.util.ArrayList;
import java.util.List;

public class RunnerTestObserver implements Observer {
  private final List<String> messages = new ArrayList<>();
  @Override
  public void update(String message) {
    messages.add(message);
  }

  public List<String> getMessages(){
    return messages;
  }
}
