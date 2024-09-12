package parser.syntax.handler;

import ast.nodes.AstNode;
import ast.nodes.ReadInputNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import token.Token;

public class ReadInputExpressionHandler implements PrimaryExpressionHandler {
  @Override
  public AstNode handle(TokenStream tokenStream, Token token) {
    tokenStream.advance();
    AstNode expression = ExpressionFactory.parseBinaryExpression(tokenStream, 0);
    return new ReadInputNode(expression, token.getLine(), token.getColumn());
  }
}
