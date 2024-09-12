package parser.syntax.handler;

import AST.nodes.ASTNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import token.Token;
import token.TokenType;

public class ParenthesisExpressionHandler implements PrimaryExpressionHandler {
  @Override
  public ASTNode handle(TokenStream tokenStream, Token token) {
    tokenStream.advance();
    ASTNode expression = ExpressionFactory.parseBinaryExpression(tokenStream, 0);
    tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
    tokenStream.advance();
    return expression;
  }
}
