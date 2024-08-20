package token;

public record ValueToken(TokenType type, String value, Integer column, Integer line)
    implements Token {
  @Override
  public String getValue() {
    return value;
  }

  @Override
  public Integer getColumn() {
    return column;
  }

  @Override
  public Integer getLine() {
    return line;
  }

  @Override
  public TokenType getType() {
    return type;
  }
}
