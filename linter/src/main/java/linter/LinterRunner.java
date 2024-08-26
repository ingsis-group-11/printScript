package linter;

import AST.nodes.ASTNode;
import fileReader.FileReader;
import lexer.Lexer;
import parser.Parser;
import result.LexingResult;
import result.SuccessfulResult;
import result.UnsuccessfulResult;
import token.Token;

import java.io.IOException;
import java.util.List;

public class LinterRunner {
  public void linterRun(String filePath, String configRulesPath) throws IOException {
    String fileString = new FileReader().readFile(filePath);
    List<Token> tokens = lexRun(fileString);
    List<ASTNode> ASTNodes = parseRun(tokens);
    Linter linter = new Linter();
    linter.lint(ASTNodes, configRulesPath);
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

  private List<ASTNode> parseRun(List<Token> tokens) {
    Parser parser = new Parser();
    List<ASTNode> ASTNodes = parser.parse(tokens);
    parser.resolveErrors();
    return ASTNodes;
  }
}
