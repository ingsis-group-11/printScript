package ast.nodes;

import ast.AstVisitor;
import token.TokenType;

public class EmptyNode implements AstNode {
  private final TokenType variableType;

  public EmptyNode(TokenType type) {
    this.variableType = type;
  }

  public TokenType getType() {
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
