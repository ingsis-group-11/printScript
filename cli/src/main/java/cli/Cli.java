package cli;

import cli.commands.AnalyzingCommand;
import cli.commands.ExecutionCommand;
import cli.commands.FormattingCommand;
import cli.commands.ValidationCommand;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

@Command(name = "printScript", mixinStandardHelpOptions = true, version = "printScript 1.0",
        description = "A CLI tool for printScript operations",
        subcommands = {ValidationCommand.class, ExecutionCommand.class, FormattingCommand.class, AnalyzingCommand.class})
public class Cli implements Runnable{
  @Option(names = {"-v", "--version"}, description = "Version of the file to interpret")
  private String version = "1.0";

  @Override
  public void run() {
    // Default behavior when no subcommand is provided
    CommandLine.usage(this, System.out);
  }

  public static void main(String[] args) {
    int exitCode = new CommandLine(new Cli()).execute(args);
    System.exit(exitCode);
  }
}
