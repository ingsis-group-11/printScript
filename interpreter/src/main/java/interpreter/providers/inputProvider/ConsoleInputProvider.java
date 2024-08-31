package interpreter.providers.inputProvider;

import java.util.Scanner;

public class ConsoleInputProvider implements InputProvider {
  @Override
  public String getInput(String message) {
    Scanner scanner = new Scanner(System.in);
    System.out.print(message);
    return scanner.nextLine();
  }
}
