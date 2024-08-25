package runner;

import AST.nodes.ASTNode;
import interpreter.Interpreter;
import java.io.IOException;
import java.util.List;
import lexer.Lexer;
import parser.Parser;
import parser.semantic.result.SemanticResult;
import result.LexingResult;
import result.SuccessfulResult;
import result.UnsuccessfulResult;
import token.Token;

public class Runner {
  public void run(String filePath) throws IOException {
    String fileString = new FileReader().readFile(filePath);
    List<Token> tokens = lexRun(fileString);
    List<ASTNode> ASTNodes = parseRun(tokens);
    interpretRun(ASTNodes);
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

  private void interpretRun(List<ASTNode> nodes) {
    Interpreter interpreter = new Interpreter();
    interpreter.interpret(nodes);
  }
}
