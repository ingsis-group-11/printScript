package cli;

import providers.observer.Observer;

public class ParserObserver implements Observer {
  @Override
  public void update(String message) {
    System.out.print(message);
  }
}
