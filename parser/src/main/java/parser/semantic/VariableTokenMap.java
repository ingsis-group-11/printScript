package parser.semantic;

import token.TokenType;

import java.util.HashMap;
import java.util.Map;

public class VariableTokenMap {

  private final Map<TokenType, TokenType> variableTokenMap = new HashMap<>();

  public VariableTokenMap () {
    variableTokenMap.put(TokenType.NUMBER_TYPE, TokenType.NUMBER);
    variableTokenMap.put(TokenType.STRING_TYPE, TokenType.STRING);
    variableTokenMap.put(TokenType.BOOLEAN_TYPE, TokenType.BOOLEAN);
  }

  public TokenType getType(TokenType variableType) {
    return variableTokenMap.get(variableType);
  }
}
