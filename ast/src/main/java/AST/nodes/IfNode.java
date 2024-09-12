package AST.nodes;

import AST.ASTVisitor;

public class IfNode implements ASTNode {
  private final ASTNode condition;
  private final BlockNode ifBlock;
  private final BlockNode elseBlock;
  private final Integer line;
  private final Integer column;

  public IfNode(
      ASTNode condition, BlockNode ifBlock, BlockNode elseBlock, Integer line, Integer column) {
    this.condition = condition;
    this.ifBlock = ifBlock;
    this.elseBlock = elseBlock;
    this.line = line;
    this.column = column;
  }

  public ASTNode getCondition() {
    return condition;
  }

  public BlockNode getIfBlock() {
    return ifBlock;
  }

  public BlockNode getElseBlock() {
    return elseBlock;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
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
