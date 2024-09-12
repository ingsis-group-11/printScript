package ast.nodes;

import ast.AstVisitor;

public class AssignationNode implements AstNode {
  private final DeclarationNode declaration;
  private final AstNode expression;
  private final Integer line;
  private final Integer column;

  public AssignationNode(
      DeclarationNode declaration, AstNode expression, Integer line, Integer column) {
    this.declaration = declaration;
    this.expression = expression;
    this.line = line;
    this.column = column;
  }

  public DeclarationNode getDeclaration() {
    return declaration;
  }

  public AstNode getExpression() {
    return expression;
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
