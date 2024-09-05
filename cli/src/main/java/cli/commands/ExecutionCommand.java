package cli.commands;

import providers.printProvider.ConsolePrintProvider;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import runner.Runner;

import java.io.FileInputStream;

@Command(name = "execute", description = "Executes a printScript file")
public class ExecutionCommand implements Runnable {

  @Parameters(index = "0", description = "The source file to validate")
  private String sourceFile;

  @Option(names = {"-v", "--version"}, description = "The version to execute printScript", defaultValue = "1.0")
  private String version;

  @Override
  public void run() {
    try {
      ConsolePrintProvider printProvider = new ConsolePrintProvider();
      Runner runner = new Runner();
      runner.run(new FileInputStream(sourceFile),version, printProvider);
    } catch (Exception e) {
      System.err.print(e);
      System.exit(1);
    }
  }
}
