package parser.syntax.handler;

import ast.nodes.AstNode;
import ast.nodes.ReadEnvNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import token.Token;

public class ReadEnvExpressionHandler implements PrimaryExpressionHandler {

  @Override
  public AstNode handle(TokenStream tokenStream, Token token) {
    tokenStream.advance();
    AstNode expression = ExpressionFactory.parseBinaryExpression(tokenStream, 0);
    return new ReadEnvNode(expression, token.getLine(), token.getColumn());
  }
}
