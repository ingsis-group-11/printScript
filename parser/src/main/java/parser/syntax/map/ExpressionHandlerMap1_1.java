package parser.syntax.map;

import parser.syntax.handler.*;
import token.TokenType;

import java.util.HashMap;
import java.util.Map;

public class ExpressionHandlerMap1_1 {
  public static Map<TokenType, PrimaryExpressionHandler> getHandlers() {
    Map<TokenType, PrimaryExpressionHandler> handlers = new HashMap<>();
    handlers.put(TokenType.NUMBER, new LiteralExpressionHandler());
    handlers.put(TokenType.STRING, new LiteralExpressionHandler());
    handlers.put(TokenType.BOOLEAN, new LiteralExpressionHandler());
    handlers.put(TokenType.IDENTIFIER, new VariableExpressionHandler());
    handlers.put(TokenType.PARENTHESIS_OPEN, new ParenthesisExpressionHandler());
    handlers.put(TokenType.READ_INPUT, new ReadInputExpressionHandler());
    return handlers;
  }
}