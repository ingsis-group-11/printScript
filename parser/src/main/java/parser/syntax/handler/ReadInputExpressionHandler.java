package parser.syntax.handler;

import ast.nodes.AstNode;
import ast.nodes.ReadInputNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import parser.syntax.result.ExpressionResult;
import token.Token;

public class ReadInputExpressionHandler implements PrimaryExpressionHandler {
  @Override
  public ExpressionResult handle(TokenStream tokenStream, Token token) {
    tokenStream = tokenStream.advance();
    ExpressionResult result = ExpressionFactory.parseBinaryExpression(tokenStream, 0);
    AstNode expression = result.astNode();
    tokenStream = result.tokenStream();
    return new ExpressionResult(new ReadInputNode(expression, token.getLine(), token.getColumn()), tokenStream);
  }
}