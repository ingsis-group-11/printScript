package parser.semantic.variables;

import token.TokenType;

public class VariableType {
  private final TokenType type;
  private final Boolean mutable;

  public VariableType(TokenType type, Boolean mutable) {
    this.type = type;
    this.mutable = mutable;
  }

  public TokenType getType() {
    return type;
  }

  public Boolean isMutable() {
    return mutable;
  }
}
