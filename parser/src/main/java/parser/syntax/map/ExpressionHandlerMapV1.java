package parser.syntax.map;

import java.util.HashMap;
import java.util.Map;
import parser.syntax.handler.LiteralExpressionHandler;
import parser.syntax.handler.ParenthesisExpressionHandler;
import parser.syntax.handler.PrimaryExpressionHandler;
import parser.syntax.handler.VariableExpressionHandler;
import token.TokenType;

public class ExpressionHandlerMapV1 {
  public static Map<TokenType, PrimaryExpressionHandler> getHandlers() {
    Map<TokenType, PrimaryExpressionHandler> handlers = new HashMap<>();
    handlers.put(TokenType.NUMBER, new LiteralExpressionHandler());
    handlers.put(TokenType.STRING, new LiteralExpressionHandler());
    handlers.put(TokenType.BOOLEAN, new LiteralExpressionHandler());
    handlers.put(TokenType.IDENTIFIER, new VariableExpressionHandler());
    handlers.put(TokenType.PARENTHESIS_OPEN, new ParenthesisExpressionHandler());
    return handlers;
  }
}
