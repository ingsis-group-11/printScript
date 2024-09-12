package parser.syntax.factory;

import java.util.Map;
import parser.syntax.handler.PrimaryExpressionHandler;
import parser.syntax.map.ExpressionHandlerMap1_0;
import parser.syntax.map.ExpressionHandlerMap1_1;
import token.TokenType;

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
