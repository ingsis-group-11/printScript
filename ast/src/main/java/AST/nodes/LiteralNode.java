package AST.nodes;

import AST.ASTVisitor;
import token.Token;
import token.TokenType;

public class LiteralNode implements ASTNode {
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
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public Integer getLine() {
    return null;
  }

  @Override
  public Integer getColumn() {
    return null;
  }
}