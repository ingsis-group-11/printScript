package AST.nodes;

import AST.ASTVisitor;

public class OperatorNode implements ASTNode {
  private final String operator;
  private final ASTNode left;
  private final ASTNode right;
  private final Integer line;
  private final Integer column;

  public OperatorNode(String operator, ASTNode left, ASTNode right, Integer line, Integer column) {
    this.operator = operator;
    this.left = left;
    this.right = right;
    this.line = line;
    this.column = column;
  }

  public String getOperator() {
    return operator;
  }

  public ASTNode getLeftNode() {
    return left;
  }

  public ASTNode getRightNode() {
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
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
