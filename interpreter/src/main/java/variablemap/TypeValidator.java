package variablemap;

import token.TokenType;

public class TypeValidator {
  private static final VariableTokenMap variableTokenMap = new VariableTokenMap();

  public static boolean validateType(TokenType variableType, TokenType expressionType) {
    if (variableTokenMap.getType(variableType) == expressionType) {
      return true;
    } else if (expressionType == TokenType.IDENTIFIER) {
      return true;
    } else {
      return expressionType == TokenType.READ_INPUT;
    }
  }
}
