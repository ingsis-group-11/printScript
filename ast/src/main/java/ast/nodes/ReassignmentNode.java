package ast.nodes;

import ast.AstVisitor;

public class ReassignmentNode implements AstNode {
  private final VariableNode variableNode;
  private final AstNode expression;
  private final Integer line;
  private final Integer column;

  public ReassignmentNode(
      VariableNode variableNode, AstNode expression, Integer line, Integer column) {
    this.variableNode = variableNode;
    this.expression = expression;
    this.line = line;
    this.column = column;
  }

  @Override
  public Integer getLine() {
    return line;
  }

  @Override
  public Integer getColumn() {
    return column;
  }

  public VariableNode getVariableNode() {
    return variableNode;
  }

  public AstNode getExpression() {
    return expression;
  }

  @Override
  public <T> T accept(AstVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
