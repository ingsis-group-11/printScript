package ast.tokens;

public interface AstToken {

  AstTokenType getType();

  String getValue();

  Integer getColumn();

  Integer getLine();
}
