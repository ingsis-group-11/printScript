package cli.commands;

import providers.printProvider.ConsolePrintProvider;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import runner.Runner;

@Command(name = "execute", description = "Executes a printScript file")
public class ExecutionCommand implements Runnable {

  @Parameters(index = "0", description = "The source file to validate")
  private String sourceFile;

  @Override
  public void run() {
    try {
      ConsolePrintProvider printProvider = new ConsolePrintProvider();
      Runner runner = new Runner();
      runner.run(sourceFile, printProvider);
    } catch (Exception e) {
      System.err.print(e);
      System.exit(1);
    }
  }
}
