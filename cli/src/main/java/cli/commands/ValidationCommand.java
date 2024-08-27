package cli.commands;

import AST.nodes.ASTNode;
import fileReader.FileReader;
import lexer.Lexer;
import parser.Parser;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import result.LexingResult;
import result.SuccessfulResult;
import result.UnsuccessfulResult;
import token.Token;

import java.io.IOException;
import java.util.List;

@Command(name = "validate", description = "Validates the semantic and syntax errors in a printScript file")
public class ValidationCommand implements Runnable {

  @Parameters(index = "0", description = "The source file to validate")
  private String sourceFile;

  @Override
  public void run() {
    System.out.print("Validating file...");
    try {
      String fileString = new FileReader().readFile(sourceFile);
      List<Token> tokens = lexRun(fileString);
      parseRun(tokens);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private List<Token> lexRun(String filePath) {
    Lexer lexer = new Lexer();
    LexingResult lexerResult = lexer.lex(filePath);

    resolveLexerErrors(lexerResult);

    List<Token> tokens = ((SuccessfulResult) lexerResult).tokens();
    return tokens;
  }

  private void resolveLexerErrors(LexingResult lexerResult) {
    if (lexerResult instanceof UnsuccessfulResult) {
      throw new RuntimeException(((UnsuccessfulResult) lexerResult).message());
    }
  }

  private void parseRun(List<Token> tokens) {
    Parser parser = new Parser();
    parser.parse(tokens);
    parser.resolveErrors();
  }
}
