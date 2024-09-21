package ast.tokens;

public record ValueAstToken(AstTokenType type, String value, Integer column, Integer line)
    implements AstToken {
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
  public AstTokenType getType() {
    return type;
  }
}
