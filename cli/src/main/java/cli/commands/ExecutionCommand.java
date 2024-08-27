package cli.commands;

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
      Runner runner = new Runner();
      runner.run(sourceFile);
    } catch (Exception e) {
      System.err.print(e);
      System.exit(1);
    }
  }
}
