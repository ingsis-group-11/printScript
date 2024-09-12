package ast.nodes;

import ast.AstVisitor;

public class IfNode implements AstNode {
  private final AstNode condition;
  private final BlockNode ifBlock;
  private final BlockNode elseBlock;
  private final Integer line;
  private final Integer column;

  public IfNode(
      AstNode condition, BlockNode ifBlock, BlockNode elseBlock, Integer line, Integer column) {
    this.condition = condition;
    this.ifBlock = ifBlock;
    this.elseBlock = elseBlock;
    this.line = line;
    this.column = column;
  }

  public AstNode getCondition() {
    return condition;
  }

  public BlockNode getIfBlock() {
    return ifBlock;
  }

  public BlockNode getElseBlock() {
    return elseBlock;
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
