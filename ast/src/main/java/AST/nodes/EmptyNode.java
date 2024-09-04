package AST.nodes;

import AST.ASTVisitor;
import token.TokenType;

public class EmptyNode implements ASTNode {
  private final TokenType variableType;

  public EmptyNode (TokenType type) {
    this.variableType = type;
  }

  public TokenType getType() {
    return variableType;
  }


  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
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