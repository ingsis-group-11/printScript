package cli.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "format", description = "Format a printScript file using a config file")
public class FormattingCommand implements Runnable {

  @Parameters(index = "0", description = "The source file to format")
  private String sourceFile;

  @Parameters(index = "1", description = "The config file that have the formatter rules")
  private String configFile;

  @Override
  public void run() {
    System.out.println("Formatting...");
  }
}
