package parser.syntax.handler;

import ast.nodes.LiteralNode;
import parser.syntax.TokenStream;
import parser.syntax.result.ExpressionResult;
import token.Token;

public class LiteralExpressionHandler implements PrimaryExpressionHandler {
  @Override
  public ExpressionResult handle(TokenStream tokenStream, Token token) {
    return new ExpressionResult(new LiteralNode(token), tokenStream);
  }
}