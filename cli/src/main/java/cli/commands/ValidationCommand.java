package cli.commands;

import AST.nodes.ASTNode;
import fileReader.FileReaderIterator;
import iterator.TokenIterator;
import parser.iterator.ASTIterator;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import token.Token;

import java.io.File;
import java.util.Iterator;

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
      FileReaderIterator fileIterator = new FileReaderIterator(new File(sourceFile));
      Iterator<Token> tokens = new TokenIterator(fileIterator, version);
      Iterator<ASTNode> nodes = new ASTIterator(tokens);
      while (nodes.hasNext()){
        nodes.next();
      }
      System.out.println("File has no semantic or syntax errors :)");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
