package cli.commands;

import cli.ParserObserver;
import java.io.FileInputStream;
import java.util.List;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import providers.inputProvider.ConsoleInputProvider;
import providers.observer.Observer;
import providers.printProvider.ConsolePrintProvider;
import runner.Runner;

@Command(name = "execute", description = "Executes a printScript file")
public class ExecutionCommand implements Runnable {

  @Parameters(index = "0", description = "The source file to validate")
  private String sourceFile;

  @Option(names = {"-v", "--version"},
          description = "The version to execute printScript",
          defaultValue = "1.0")
  private String version;

  @Override
  public void run() {
    try {
      List<Observer> parserObservers = List.of(new ParserObserver());
      ConsolePrintProvider printProvider = new ConsolePrintProvider();
      ConsoleInputProvider consoleInputProvider = new ConsoleInputProvider();
      Runner runner = new Runner();
      runner.run(new FileInputStream(sourceFile),
              version, printProvider,
              consoleInputProvider,
              parserObservers);
      System.out.println();
      printProvider.printMessages();
    } catch (Exception e) {
      System.err.print(e.getMessage());
      System.exit(1);
    }
  }
}
