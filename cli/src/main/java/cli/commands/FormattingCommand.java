package cli.commands;

import providers.outputprovider.FileWriter;
import runner.FormatterRunner;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.FileInputStream;

@Command(name = "format", description = "Format a printScript file using a config file")
public class FormattingCommand implements Runnable {

  @Parameters(index = "0", description = "The input source file to format")
  private String inputFile;

  @Parameters(index = "1", description = "The output file that will be formatted")
  private String outputFile;

  @Parameters(index = "2", description = "The config file that have the formatter rules")
  private String configFile;

  @Option(names = {"-v", "--version"}, description = "The version to execute printScript", defaultValue = "1.0")
  private String version;

  @Override
  public void run() {
    System.out.println("Formatting file...");
    try {
      FormatterRunner formatterRunner = new FormatterRunner();
      FileWriter fileWriter = new FileWriter(outputFile);
      formatterRunner.format(new FileInputStream(inputFile), new FileInputStream(configFile), fileWriter, version);
      System.out.println("File has been formatted :)");
    } catch (Exception e) {
      System.err.print(e.getMessage());
      System.exit(1);
    }
  }
}
