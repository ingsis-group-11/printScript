package parser.syntax.factory;

import ast.nodes.AstNode;
import ast.nodes.OperatorNode;
import java.util.Map;
import parser.syntax.TokenStream;
import parser.syntax.handler.PrimaryExpressionHandler;
import token.Token;
import token.TokenType;

public class ExpressionFactory {
  private static Map<TokenType, PrimaryExpressionHandler> handlers;

  public static AstNode createExpression(TokenStream tokenStream, String version) {
    handlers = ExpressionHandlerMapFactory.getHandlerMap(version);
    return parseBinaryExpression(tokenStream, 0);
  }

  private static AstNode parsePrimaryExpression(TokenStream tokenStream) {
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

  public static AstNode parseBinaryExpression(TokenStream tokenStream, int precedence) {
    AstNode left = parsePrimaryExpression(tokenStream);
    return parseBinaryExpressionRecursive(tokenStream, left, precedence);
  }

  private static AstNode parseBinaryExpressionRecursive(
      TokenStream tokenStream, AstNode left, int precedence) {
    Token token = tokenStream.getCurrentToken();
    if (token == null || token.getType() != TokenType.OPERATOR) {
      return left;
    }

    int tokenPrecedence = getPriority(token);
    if (tokenPrecedence < precedence) {
      return left;
    }

    tokenStream.advance();
    AstNode right = parseBinaryExpression(tokenStream, tokenPrecedence + 1);
    left = new OperatorNode(token.getValue(), left, right, token.getLine(), token.getColumn());

    return parseBinaryExpressionRecursive(tokenStream, left, precedence);
  }

  private static int getPriority(Token token) {
    return switch (token.getValue()) {
      case "*", "/" -> 2;
      case "+", "-" -> 1;
      default -> 0;
    };
  }
}
