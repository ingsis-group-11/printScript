package parser.syntax.factory;

import java.util.Map;
import parser.syntax.handler.PrimaryExpressionHandler;
import parser.syntax.map.ExpressionHandlerMapV1;
import parser.syntax.map.ExpressionHandlerMapV2;
import token.TokenType;

public class ExpressionHandlerMapFactory {
  public static Map<TokenType, PrimaryExpressionHandler> getHandlerMap(String version) {
    switch (version) {
      case "1.0":
        return ExpressionHandlerMapV1.getHandlers();
      case "1.1":
        return ExpressionHandlerMapV2.getHandlers();
      default:
        throw new IllegalArgumentException("Unsupported version: " + version);
    }
  }
}
