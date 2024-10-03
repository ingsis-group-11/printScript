package parser.semantic.variables;

import ast.tokens.AstTokenType;

public class VariableType {
  private final AstTokenType type;
  private final Boolean mutable;

  public VariableType(AstTokenType type, Boolean mutable) {
    this.type = type;
    this.mutable = mutable;
  }

  public AstTokenType getType() {
    return type;
  }

  public Boolean isMutable() {
    return mutable;
  }
}
