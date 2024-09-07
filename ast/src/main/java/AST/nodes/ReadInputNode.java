package AST.nodes;

import AST.ASTVisitor;

public class ReadInputNode implements ASTNode {
  private final Integer line;
  private final Integer column;
  private final ASTNode expression;

  public ReadInputNode(ASTNode expression, Integer line, Integer column) {
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

  public ASTNode getExpression() {
    return expression;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}