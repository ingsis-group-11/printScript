package parser.syntax.handler;

import AST.nodes.ASTNode;
import AST.nodes.LiteralNode;
import parser.syntax.TokenStream;
import token.Token;

public class LiteralExpressionHandler implements PrimaryExpressionHandler {
  @Override
  public ASTNode handle(TokenStream tokenStream, Token token) {
    tokenStream.advance();
    return new LiteralNode(token);
  }
}
