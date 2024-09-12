package parser.syntax.handler;

import ast.nodes.AstNode;
import ast.nodes.LiteralNode;
import parser.syntax.TokenStream;
import token.Token;

public class LiteralExpressionHandler implements PrimaryExpressionHandler {
  @Override
  public AstNode handle(TokenStream tokenStream, Token token) {
    tokenStream.advance();
    return new LiteralNode(token);
  }
}
