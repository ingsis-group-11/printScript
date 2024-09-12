package ast.nodes;

import ast.AstVisitor;

public class PrintNode implements AstNode {
  private final AstNode expression;
  private final Integer line;
  private final Integer column;

  public PrintNode(AstNode expression, Integer line, Integer column) {
    this.expression = expression;
    this.line = line;
    this.column = column;
  }

  public AstNode getExpression() {
    return expression;
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
}
