package ast.nodes;

import ast.AstVisitor;

public class ReadEnvNode implements AstNode {
  private final Integer line;
  private final Integer column;
  private final AstNode expression;

  public ReadEnvNode(AstNode expression, Integer line, Integer column) {
    this.line = line;
    this.column = column;
    this.expression = expression;
  }

  @Override
  public Integer getLine() {
    return line;
  }

  @Override
  public Integer getColumn() {
    return column;
  }

  public AstNode getExpression() {
    return expression;
  }

  @Override
  public <T> T accept(AstVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
