package ast.nodes;

import ast.AstVisitor;
import ast.tokens.AstTokenType;

public class EmptyNode implements AstNode {
  private final AstTokenType variableType;

  public EmptyNode(AstTokenType type) {
    this.variableType = type;
  }

  public AstTokenType getType() {
    return variableType;
  }

  @Override
  public <T> T accept(AstVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public Integer getLine() {
    return null;
  }

  @Override
  public Integer getColumn() {
    return null;
  }
}
