package parser.syntax.handler;

import ast.nodes.AstNode;
import ast.nodes.VariableNode;
import parser.syntax.TokenStream;
import token.Token;

public class VariableExpressionHandler implements PrimaryExpressionHandler {
  @Override
  public AstNode handle(TokenStream tokenStream, Token token) {
    tokenStream.advance();
    return new VariableNode(token);
  }
}
