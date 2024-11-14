package runner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import providers.inputprovider.TestInputProvider;
import providers.observer.Observer;

public class ValidationRunner {
  private List<Observer> observers = new ArrayList<>();

  public void setObservers(List<Observer> observers) {
    this.observers = observers;
  }

  public void validate(InputStream sourceFile, String version) throws IOException {
    Runner runner = new Runner();
    runner.setObservers(observers);
    runner.run(sourceFile, version);
  }

  public void validate(InputStream sourceFile, String version, TestInputProvider testInputProvider)
      throws IOException {
    Runner runner = new Runner();
    runner.setObservers(observers);
    runner.run(sourceFile, version, testInputProvider);
  }
}
