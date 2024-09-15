package parser.syntax.handler;

import ast.nodes.AstNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import parser.syntax.result.ExpressionResult;
import token.Token;
import token.TokenType;

public class ParenthesisExpressionHandler implements PrimaryExpressionHandler {
  @Override
  public ExpressionResult handle(TokenStream tokenStream, Token token) {
    tokenStream = tokenStream.advance();  // Advance immutably
    ExpressionResult result = ExpressionFactory.parseBinaryExpression(tokenStream, 0);
    AstNode expression = result.astNode();
    tokenStream = result.tokenStream();
    tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
    tokenStream = tokenStream.advance();  // Advance immutably
    return new ExpressionResult(expression, tokenStream);
  }
}