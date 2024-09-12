package ast.nodes;

import ast.AstVisitor;

public class OperatorNode implements AstNode {
  private final String operator;
  private final AstNode left;
  private final AstNode right;
  private final Integer line;
  private final Integer column;

  public OperatorNode(String operator, AstNode left, AstNode right, Integer line, Integer column) {
    this.operator = operator;
    this.left = left;
    this.right = right;
    this.line = line;
    this.column = column;
  }

  public String getOperator() {
    return operator;
  }

  public AstNode getLeftNode() {
    return left;
  }

  public AstNode getRightNode() {
    return right;
  }

  @Override
  public Integer getLine() {
    return line;
  }

  @Override
  public Integer getColumn() {
    return column;
  }

  @Override
  public <T> T accept(AstVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
