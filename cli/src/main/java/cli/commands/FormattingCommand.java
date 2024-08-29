package cli.commands;

import formatter.FormatterRunner;
import linter.LinterRunner;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "format", description = "Format a printScript file using a config file")
public class FormattingCommand implements Runnable {

  @Parameters(index = "0", description = "The input source file to format")
  private String inputFile;

  @Parameters(index = "1", description = "The output source file to format")
  private String outputFile;

  @Parameters(index = "2", description = "The config file that have the formatter rules")
  private String configFile;

  @Override
  public void run() {
    System.out.println("Formatting file...");
    try {
      FormatterRunner formatterRunner = new FormatterRunner();
      formatterRunner.format(inputFile, outputFile, configFile);
      System.out.println("File has been formatted :)");
    } catch (Exception e) {
      System.err.print(e);
      System.exit(1);
    }
  }
}
