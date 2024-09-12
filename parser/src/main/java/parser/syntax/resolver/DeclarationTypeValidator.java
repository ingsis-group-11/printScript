package parser.syntax.resolver;

import token.TokenType;

public class DeclarationTypeValidator {
  public static boolean isValidDeclarationType(TokenType type) {
    return switch (type) {
      case TokenType.STRING_TYPE, TokenType.NUMBER_TYPE, TokenType.BOOLEAN_TYPE -> true;
      default -> false;
    };
  }
}
