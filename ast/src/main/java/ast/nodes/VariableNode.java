package ast.nodes;

import ast.AstVisitor;
import token.Token;
import token.TokenType;

public class VariableNode implements AstNode {
  private final Token token;
  private final Integer line;
  private final Integer column;

  public VariableNode(Token token) {
    this.token = token;
    this.line = token.getLine();
    this.column = token.getColumn();
  }

  public Token getToken() {
    return token;
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

  public String getValue() {
    return token.getValue();
  }

  public TokenType getType() {
    return token.getType();
  }
}
