package variableMap;

import AST.nodes.LiteralNode;
import token.TokenType;

public class VariableAssignation {
  private final LiteralNode literalNode;
  private final Boolean mutable;

  public VariableAssignation(LiteralNode literalNode, Boolean mutable) {
    this.literalNode = literalNode;
    this.mutable = mutable;
  }

  public String getValue() {
    return literalNode.getValue();
  }

  public Boolean isMutable() {
    return mutable;
  }

  public TokenType getType() {
    return literalNode.getType();
  }

  public LiteralNode getLiteralNode() {
    return literalNode;
  }
}
