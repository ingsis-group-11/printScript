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
import java.util.Iterator;
import java.util.List;

public class LinterRunner {
  public void linterRun(String filePath, String configRulesPath) throws IOException {
    FileIterator fileIterator = new FileIterator(filePath);
    Iterator<Token> tokens = lexRun(fileIterator);
    Iterator<ASTNode> nodes = parseRun(tokens);
    Linter linter = new Linter();
    while (nodes.hasNext()) {
      ASTNode node = nodes.next();
      linter.lint(node, configRulesPath);
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

  private ASTNode parseRun(Iterator<Token> tokens) {
    Parser parser = new Parser();
    ASTNode node = parser.parse(tokens);
    parser.resolveErrors();
    return node;
  }
}
