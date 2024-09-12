package parser.syntax.handler;

import AST.nodes.ASTNode;
import AST.nodes.VariableNode;
import parser.syntax.TokenStream;
import token.Token;

public class VariableExpressionHandler implements PrimaryExpressionHandler {
  @Override
  public ASTNode handle(TokenStream tokenStream, Token token) {
    tokenStream.advance();
    return new VariableNode(token);
  }
}
