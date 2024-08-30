package runner;

import AST.nodes.ASTNode;
import interpreter.Interpreter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import lexer.Lexer;
import parser.Parser;
import result.LexingResult;
import result.SuccessfulResult;
import result.UnsuccessfulResult;
import token.Token;

public class Runner {
  public void run(String filePath) throws IOException {
    FileIterator fileIterator = new FileIterator(new File(filePath));
    Iterator<Token> tokens = lexRun(fileIterator);
    Iterator<ASTNode> ASTNodes = parseRun(tokens);
    interpretRun(ASTNodes);
  }

  private Iterator<Token> lexRun(String filePath) {
    Lexer lexer = new Lexer();
    LexingResult lexerResult = lexer.lex(filePath);

    resolveLexerErrors(lexerResult);

    Iterator<Token> tokens = ((SuccessfulResult) lexerResult).tokens();
    return tokens;
  }

  private void resolveLexerErrors(LexingResult lexerResult) {
    if (lexerResult instanceof UnsuccessfulResult) {
      throw new RuntimeException(((UnsuccessfulResult) lexerResult).message());
    }
  }

  private Iterator<ASTNode> parseRun(Iterator<Token> tokens) {
    Parser parser = new Parser();
    Iterator<ASTNode> ASTNodes = parser.parse(tokens);
    parser.resolveErrors();
    return ASTNodes;
  }

  private void interpretRun(Iterator<ASTNode> nodes) {
    Interpreter interpreter = new Interpreter();
    interpreter.interpret(nodes);
  }
}
