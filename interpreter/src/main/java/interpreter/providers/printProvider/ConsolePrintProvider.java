package interpreter.providers.printProvider;

public class ConsolePrintProvider implements PrintProvider {
  @Override
  public void print(String message) {
    System.out.println(message);
  }
}
