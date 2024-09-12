package parser.syntax.handler;

import AST.nodes.ASTNode;
import AST.nodes.ReadInputNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import token.Token;

public class ReadInputExpressionHandler implements PrimaryExpressionHandler {
  @Override
  public ASTNode handle(TokenStream tokenStream, Token token) {
    tokenStream.advance();
    ASTNode expression = ExpressionFactory.parseBinaryExpression(tokenStream, 0);
    return new ReadInputNode(expression, token.getLine(), token.getColumn());
  }
}
