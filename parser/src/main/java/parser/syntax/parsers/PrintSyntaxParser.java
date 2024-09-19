package parser.syntax.parsers;

import ast.nodes.AstNode;
import ast.nodes.PrintNode;
import java.util.Optional;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import token.TokenType;

public class PrintSyntaxParser implements SyntaxParser {

  @Override
  public AstNode syntaxParse(TokenStream tokens, String version) {
    AstNode result = parsePrint(tokens, version);
    return result;
  }

  private AstNode parsePrint(TokenStream tokenStream, String version) {
    handleExpect(tokenStream.expect(TokenType.PRINT_KEYWORD, "Expected 'println'"));
    tokenStream.advance();
    handleExpect(tokenStream.expect(TokenType.PARENTHESIS_OPEN, "Expected '('"));
    tokenStream.advance();
    AstNode expressionNode = ExpressionFactory.createExpression(tokenStream, version);
    handleExpect(tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'"));
    tokenStream.advance();
    handleExpect(tokenStream.expect(TokenType.SEMICOLON, "Expected ';'"));
    tokenStream.advance();
    return new PrintNode(expressionNode, expressionNode.getLine(), expressionNode.getColumn());
  }

  private void handleExpect(Optional<Exception> exception) {
    exception.ifPresent(
        e -> {
          throw new RuntimeException(e);
        });
  }
}
