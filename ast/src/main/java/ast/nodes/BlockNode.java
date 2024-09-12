package ast.nodes;

import ast.AstVisitor;
import java.util.List;

public class BlockNode implements AstNode {
  private final List<AstNode> statements;

  public BlockNode(List<AstNode> statements) {
    this.statements = statements;
  }

  public List<AstNode> getStatements() {
    return statements;
  }

  @Override
  public <T> T accept(AstVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public Integer getLine() {
    return statements.get(0).getLine();
  }

  @Override
  public Integer getColumn() {
    return statements.get(0).getColumn();
  }
}
