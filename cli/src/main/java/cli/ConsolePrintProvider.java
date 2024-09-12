package cli;


import providers.printProvider.PrintProvider;

import java.util.ArrayList;
import java.util.List;

public class ConsolePrintProvider implements PrintProvider {

  private List<String> messages = new ArrayList<>();

  @Override
  public void print(String message) {
    messages.add(message);
  }

  public void printMessages() {
    for (String message : messages) {
      System.out.println(message);
    }
  }
}
