package parser.syntax.parsers;

import ast.nodes.AstNode;
import ast.nodes.ReadEnvNode;
import java.util.Optional;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import token.TokenType;

public class ReadEnvSyntaxParser implements SyntaxParser {
  @Override
  public AstNode syntaxParse(TokenStream tokens, String version) {
    AstNode result = parseReadInput(tokens, version);
    return result;
  }

  private AstNode parseReadInput(TokenStream tokenStream, String version) {
    handleExpect(tokenStream.expect(TokenType.READ_ENV, "Expected 'readEnv'"));

    handleExpect(tokenStream.expect(TokenType.PARENTHESIS_OPEN, "Expected '('"));

    AstNode expressionNode = ExpressionFactory.createExpression(tokenStream, version);
    handleExpect(tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'"));
    int line = tokenStream.getCurrentToken().getLine();
    int column = tokenStream.getCurrentToken().getColumn();
    handleExpect(tokenStream.expect(TokenType.SEMICOLON, "Expected ';'"));
    return new ReadEnvNode(expressionNode, line, column);
  }

  private void handleExpect(Optional<Exception> exception) {
    exception.ifPresent(
        e -> {
          if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
          } else {
            throw new RuntimeException(e);
          }
        });
  }
}
