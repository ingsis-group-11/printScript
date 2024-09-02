package cli.commands;

import fileWriter.FileWriter;
import formatter.FormatterRunner;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "format", description = "Format a printScript file using a config file")
public class FormattingCommand implements Runnable {

  @Parameters(index = "0", description = "The input source file to format")
  private String inputFile;

  @Parameters(index = "1", description = "The config file that have the formatter rules")
  private String configFile;

  @Override
  public void run() {
    System.out.println("Formatting file...");
    try {
      FormatterRunner formatterRunner = new FormatterRunner();
      FileWriter fileWriter = new FileWriter(inputFile);
      formatterRunner.format(inputFile, configFile, fileWriter);
      System.out.println("File has been formatted :)");
    } catch (Exception e) {
      System.err.print(e);
      System.exit(1);
    }
  }
}
