package ast.nodes;

import ast.AstVisitor;
import ast.tokens.AstToken;
import ast.tokens.AstTokenType;

public class LiteralNode implements AstNode {
  private final AstToken astToken;

  public LiteralNode(AstToken value) {
    this.astToken = value;
  }

  public String getValue() {
    return astToken.getValue();
  }

  public AstToken getToken() {
    return astToken;
  }

  public AstTokenType getType() {
    return astToken.getType();
  }

  @Override
  public <T> T accept(AstVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public Integer getLine() {
    return astToken.getLine();
  }

  @Override
  public Integer getColumn() {
    return astToken.getColumn();
  }
}
