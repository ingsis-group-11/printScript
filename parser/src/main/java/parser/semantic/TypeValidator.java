package parser.semantic;

import ast.tokens.AstTokenType;
import parser.semantic.variables.VariableTokenMap;

public class TypeValidator {
  private static final VariableTokenMap variableTokenMap = new VariableTokenMap();

  public static boolean validateType(AstTokenType variableType, AstTokenType expressionType) {
    if (variableTokenMap.getType(variableType) == expressionType) {
      return true;
    } else if (expressionType == AstTokenType.IDENTIFIER) {
      return true;
    } else {
      return expressionType == AstTokenType.READ_INPUT;
    }
  }
}
