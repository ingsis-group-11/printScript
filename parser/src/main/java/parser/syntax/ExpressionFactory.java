package parser.syntax;

import AST.nodes.*;
import token.Token;
import token.TokenType;

public class ExpressionFactory {

  public static ASTNode createExpression(TokenStream tokenStream) {
    return parseBinaryExpression(tokenStream, 0);
  }

  private static ASTNode parsePrimaryExpression(TokenStream tokenStream) {
    Token token = tokenStream.getCurrentToken();
    if (token != null) {
      if (token.getType() == TokenType.NUMBER || token.getType() == TokenType.STRING) {
        tokenStream.advance();
        return new LiteralNode(token);
      } else if (token.getType() == TokenType.IDENTIFIER) {
        tokenStream.advance();
        return new VariableNode(token);
      } else if (token.getType() == TokenType.PARENTHESIS_OPEN) {
        tokenStream.advance();
        ASTNode expression = parseBinaryExpression(tokenStream, 0);
        tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
        return expression;
      } else if(token.getType() == TokenType.READINPUT_KEYWORD){
        tokenStream.advance();
        tokenStream.expect(TokenType.PARENTHESIS_OPEN, "Expected '('");
        ASTNode expression = parseBinaryExpression(tokenStream, 0);
        tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");

        return new ReadInputNode(expression, token.getLine(), token.getColumn());
      }
    }
    assert token != null;
    throw new IllegalArgumentException(
        "Invalid expression " + token.getValue() + " at column " + token.getColumn() + " line " + token.getLine());
  }

  private static ASTNode parseBinaryExpression(TokenStream tokenStream, int precedence) {
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
