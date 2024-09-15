package parser.syntax.handler;


import ast.nodes.VariableNode;
import parser.syntax.TokenStream;
import parser.syntax.result.ExpressionResult;
import token.Token;

public class VariableExpressionHandler implements PrimaryExpressionHandler {
  @Override
  public ExpressionResult handle(TokenStream tokenStream, Token token) {
    return new ExpressionResult(new VariableNode(token), tokenStream);
  }
}