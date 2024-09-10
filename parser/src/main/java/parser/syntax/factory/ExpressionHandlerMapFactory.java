package parser.syntax.factory;

import parser.syntax.handler.PrimaryExpressionHandler;
import parser.syntax.map.ExpressionHandlerMap1_0;
import parser.syntax.map.ExpressionHandlerMap1_1;
import token.TokenType;

import java.util.Map;

public class ExpressionHandlerMapFactory {
  public static Map<TokenType, PrimaryExpressionHandler> getHandlerMap(String version) {
    switch (version) {
      case "1.0":
        return ExpressionHandlerMap1_0.getHandlers();
      case "1.1":
        return ExpressionHandlerMap1_1.getHandlers();
      default:
        throw new IllegalArgumentException("Unsupported version: " + version);
    }
  }
}
