package parser.syntax.factory;

import ast.nodes.AstNode;
import ast.nodes.OperatorNode;
import java.util.Map;
import parser.syntax.TokenStream;
import parser.syntax.handler.PrimaryExpressionHandler;
import parser.syntax.result.ExpressionResult;
import token.Token;
import token.TokenType;

public class ExpressionFactory {
  private static Map<TokenType, PrimaryExpressionHandler> handlers;

  public static ExpressionResult createExpression(TokenStream tokenStream, String version) {
    handlers = ExpressionHandlerMapFactory.getHandlerMap(version);
    return parseBinaryExpression(tokenStream, 0);
  }

  private static ExpressionResult parsePrimaryExpression(TokenStream tokenStream) {
    Token token = tokenStream.getCurrentToken();
    if (token != null) {
      PrimaryExpressionHandler handler = handlers.get(token.getType());
      if (handler != null) {
        return handler.handle(tokenStream, token);
      }
    }
    assert token != null;
    throw new IllegalArgumentException(
        "Invalid expression "
            + token.getValue()
            + " at column "
            + token.getColumn()
            + " line "
            + token.getLine());
  }

  public static ExpressionResult parseBinaryExpression(TokenStream tokenStream, int precedence) {
    ExpressionResult leftResult = parsePrimaryExpression(tokenStream);
    tokenStream = leftResult.tokenStream();  // Update tokenStream
    tokenStream = tokenStream.advance();  // Advance immutably

    return parseBinaryExpressionRecursive(tokenStream, leftResult.astNode(), precedence);
  }

  private static ExpressionResult parseBinaryExpressionRecursive(
      TokenStream tokenStream, AstNode left, int precedence) {
    Token token = tokenStream.getCurrentToken();
    if (token == null || token.getType() != TokenType.OPERATOR) {
      return new ExpressionResult(left, tokenStream);
    }

    int tokenPrecedence = getPriority(token);
    if (tokenPrecedence < precedence) {
      return new ExpressionResult(left, tokenStream);
    }

    tokenStream = tokenStream.advance();
    ExpressionResult rightResult = parseBinaryExpression(tokenStream, tokenPrecedence + 1);
    left = new OperatorNode(token.getValue(), left, rightResult.astNode(), token.getLine(), token.getColumn());

    return parseBinaryExpressionRecursive(rightResult.tokenStream(), left, precedence);
  }

  private static int getPriority(Token token) {
    return switch (token.getValue()) {
      case "*", "/" -> 2;
      case "+", "-" -> 1;
      default -> 0;
    };
  }
}