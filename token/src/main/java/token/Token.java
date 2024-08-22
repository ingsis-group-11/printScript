package token;

public interface Token {

  TokenType getType();

  String getValue();

  Integer getColumn();

  Integer getLine();
}
