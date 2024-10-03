package ast.nodes;

import ast.AstVisitor;
import ast.tokens.AstToken;
import ast.tokens.AstTokenType;

public class VariableNode implements AstNode {
  private final AstToken astToken;
  private final Integer line;
  private final Integer column;

  public VariableNode(AstToken astToken) {
    this.astToken = astToken;
    this.line = astToken.getLine();
    this.column = astToken.getColumn();
  }

  public AstToken getToken() {
    return astToken;
  }

  @Override
  public <T> T accept(AstVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public Integer getLine() {
    return line;
  }

  @Override
  public Integer getColumn() {
    return column;
  }

  public String getValue() {
    return astToken.getValue();
  }

  public AstTokenType getType() {
    return astToken.getType();
  }
}
