package parser.syntax.handler;

import ast.nodes.AstNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import token.Token;
import token.TokenType;

public class ParenthesisExpressionHandler implements PrimaryExpressionHandler {
  @Override
  public AstNode handle(TokenStream tokenStream, Token token) {
    tokenStream.advance();
    AstNode expression = ExpressionFactory.parseBinaryExpression(tokenStream, 0);
    tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
    tokenStream.advance();
    return expression;
  }
}
