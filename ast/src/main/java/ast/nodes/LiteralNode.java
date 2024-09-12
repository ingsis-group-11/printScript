package ast.nodes;

import ast.AstVisitor;
import token.Token;
import token.TokenType;

public class LiteralNode implements AstNode {
  private final Token token;

  public LiteralNode(Token value) {
    this.token = value;
  }

  public String getValue() {
    return token.getValue();
  }

  public Token getToken() {
    return token;
  }

  public TokenType getType() {
    return token.getType();
  }

  @Override
  public <T> T accept(AstVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public Integer getLine() {
    return token.getLine();
  }

  @Override
  public Integer getColumn() {
    return token.getColumn();
  }
}
