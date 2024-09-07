package parser.syntax.factory;

import AST.nodes.ASTNode;
import AST.nodes.OperatorNode;
import parser.syntax.TokenStream;
import parser.syntax.handler.PrimaryExpressionHandler;
import token.Token;
import token.TokenType;

import java.util.Map;

public class ExpressionFactory {
  private static Map<TokenType, PrimaryExpressionHandler> handlers;

  public static ASTNode createExpression(TokenStream tokenStream, String version) {
    handlers = HandlerMapFactory.getHandlerMap(version);
    return parseBinaryExpression(tokenStream, 0);
  }

  private static ASTNode parsePrimaryExpression(TokenStream tokenStream) {
    Token token = tokenStream.getCurrentToken();
    if (token != null) {
      PrimaryExpressionHandler handler = handlers.get(token.getType());
      if (handler != null) {
        return handler.handle(tokenStream, token);
      }
    }
    assert token != null;
    throw new IllegalArgumentException(
        "Invalid expression " + token.getValue() + " at column " + token.getColumn() + " line " + token.getLine());
  }

  public static ASTNode parseBinaryExpression(TokenStream tokenStream, int precedence) {
    ASTNode left = parsePrimaryExpression(tokenStream);
    return parseBinaryExpressionRecursive(tokenStream, left, precedence);
  }

  private static ASTNode parseBinaryExpressionRecursive(
      TokenStream tokenStream, ASTNode left, int precedence) {
    Token token = tokenStream.getCurrentToken();
    if (token == null || token.getType() != TokenType.OPERATOR) {
      return left;
    }

    int tokenPrecedence = getPriority(token);
    if (tokenPrecedence < precedence) {
      return left;
    }

    tokenStream.advance();
    ASTNode right = parseBinaryExpression(tokenStream, tokenPrecedence + 1);
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