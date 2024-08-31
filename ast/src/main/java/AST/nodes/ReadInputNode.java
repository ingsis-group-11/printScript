package AST.nodes;

import AST.ASTVisitor;
import token.TokenType;

public class ReadInputNode implements ASTNode {
  private final Integer line;
  private final Integer column;
  private final ASTNode string;

  public ReadInputNode(ASTNode string, Integer line, Integer column) {
    this.line = line;
    this.column = column;
    this.string = string;
  }

  @Override
  public Integer getLine() {
    return line;
  }

  @Override
  public Integer getColumn() {
    return column;
  }

  public ASTNode getString() {
    return string;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
