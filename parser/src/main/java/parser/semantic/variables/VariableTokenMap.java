package parser.semantic.variables;

import ast.tokens.AstTokenType;
import java.util.HashMap;
import java.util.Map;

public class VariableTokenMap {

  private final Map<AstTokenType, AstTokenType> variableTokenMap = new HashMap<>();

  public VariableTokenMap() {
    variableTokenMap.put(AstTokenType.NUMBER_TYPE, AstTokenType.NUMBER);
    variableTokenMap.put(AstTokenType.STRING_TYPE, AstTokenType.STRING);
    variableTokenMap.put(AstTokenType.BOOLEAN_TYPE, AstTokenType.BOOLEAN);
  }

  public AstTokenType getType(AstTokenType variableType) {
    return variableTokenMap.get(variableType);
  }
}
