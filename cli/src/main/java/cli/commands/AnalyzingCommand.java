package cli.commands;

import linter.LinterRunner;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;


@Command(name = "analyze", description = "Analyze a printScript file using a config file")
public class AnalyzingCommand implements Runnable {

  @Parameters(index = "0", description = "The source file to analyze")
  private String sourceFile;

  @Parameters(index = "1", description = "The config file that have the linter rules")
  private String configFile;

  @Option(names = {"-v", "--version"}, description = "The version to execute printScript", defaultValue = "1.0")
  private String version;

  @Override
  public void run() {
    System.out.println("Analyzing file...");
    try {
      LinterRunner linterRunner = new LinterRunner();
      linterRunner.linterRun(sourceFile, configFile, version);
      System.out.println("File has no linter errors :)");
    } catch (Exception e) {
      System.err.print(e);
      System.exit(1);
    }
  }
}
