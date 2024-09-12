package ast.nodes;

import ast.AstVisitor;

public interface AstNode {
  <T> T accept(AstVisitor<T> visitor);

  public Integer getLine();

  public Integer getColumn();
}
