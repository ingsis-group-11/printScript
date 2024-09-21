package variablemap;

import ast.nodes.LiteralNode;
import ast.tokens.AstTokenType;

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

  public AstTokenType getType() {
    return literalNode.getType();
  }

  public LiteralNode getLiteralNode() {
    return literalNode;
  }
}
