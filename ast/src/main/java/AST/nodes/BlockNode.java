package AST.nodes;

import AST.ASTVisitor;
import java.util.List;

public class BlockNode implements ASTNode {
  private final List<ASTNode> statements;

  public BlockNode(List<ASTNode> statements) {
    this.statements = statements;
  }

  public List<ASTNode> getStatements() {
    return statements;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
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
