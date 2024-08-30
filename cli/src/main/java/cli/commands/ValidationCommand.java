package cli.commands;

import fileReader.FileReaderIterator;
import iterator.TokenIterator;
import lexer.Lexer;
import parser.iterator.ASTIterator;
import parser.Parser;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import token.Token;

import java.io.File;
import java.util.Iterator;

@Command(name = "validate", description = "Validates the semantic and syntax errors in a printScript file")
public class ValidationCommand implements Runnable {

  @Parameters(index = "0", description = "The source file to validate")
  private String sourceFile;

  @Override
  public void run() {
    System.out.println("Validating file...");
    try {
      FileReaderIterator fileIterator = new FileReaderIterator(new File(sourceFile));
      Iterator<Token> tokens = new TokenIterator(fileIterator, new Lexer());
      new ASTIterator(new Parser(), tokens);
      System.out.println("File has no semantic or syntax errors :)");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
