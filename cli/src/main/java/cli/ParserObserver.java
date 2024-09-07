package cli;

import parser.Observer;

public class ParserObserver implements Observer {
  @Override
  public void update(String message) {
    System.out.print(message);
  }
}
