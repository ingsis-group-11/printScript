package parser.syntax.handler;

import ast.nodes.AstNode;
import ast.nodes.LiteralNode;
import parser.syntax.TokenStream;
import parser.syntax.map.TokenGenerator;
import token.Token;

public class LiteralExpressionHandler implements PrimaryExpressionHandler {
  private final TokenGenerator tokenGenerator = new TokenGenerator();

  @Override
  public AstNode handle(TokenStream tokenStream, Token token) {
    tokenStream.advance();
    return new LiteralNode(tokenGenerator.getAstToken(token));
  }
}
