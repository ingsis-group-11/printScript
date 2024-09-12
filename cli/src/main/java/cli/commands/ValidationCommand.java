package cli.commands;

import cli.ParserObserver;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import runner.ValidationRunner;

import java.io.FileInputStream;

@Command(name = "validate", description = "Validates the semantic and syntax errors in a printScript file")
public class ValidationCommand implements Runnable {

  @Parameters(index = "0", description = "The source file to validate")
  private String sourceFile;

  @Option(names = {"-v", "--version"}, description = "The version to execute printScript", defaultValue = "1.0")
  private String version;

  @Override
  public void run() {
    System.out.println("Validating file...");
    try {
      ParserObserver parserObserver = new ParserObserver();
      ValidationRunner validationRunner = new ValidationRunner();
      validationRunner.validate(new FileInputStream(sourceFile), version, parserObserver);
      System.out.println("\nFile has no semantic or syntax errors :)");
    } catch (Exception e) {
      System.err.print(e.getMessage());
      System.exit(1);
    }
  }
}
