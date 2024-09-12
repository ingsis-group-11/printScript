package parser.syntax.map;

import java.util.HashMap;
import java.util.Map;
import parser.syntax.handler.LiteralExpressionHandler;
import parser.syntax.handler.ParenthesisExpressionHandler;
import parser.syntax.handler.PrimaryExpressionHandler;
import parser.syntax.handler.ReadEnvExpressionHandler;
import parser.syntax.handler.ReadInputExpressionHandler;
import parser.syntax.handler.VariableExpressionHandler;
import token.TokenType;

public class ExpressionHandlerMapV2 {
  public static Map<TokenType, PrimaryExpressionHandler> getHandlers() {
    Map<TokenType, PrimaryExpressionHandler> handlers = new HashMap<>();
    handlers.put(TokenType.NUMBER, new LiteralExpressionHandler());
    handlers.put(TokenType.STRING, new LiteralExpressionHandler());
    handlers.put(TokenType.BOOLEAN, new LiteralExpressionHandler());
    handlers.put(TokenType.IDENTIFIER, new VariableExpressionHandler());
    handlers.put(TokenType.PARENTHESIS_OPEN, new ParenthesisExpressionHandler());
    handlers.put(TokenType.READ_INPUT, new ReadInputExpressionHandler());
    handlers.put(TokenType.READ_ENV, new ReadEnvExpressionHandler());
    return handlers;
  }
}
