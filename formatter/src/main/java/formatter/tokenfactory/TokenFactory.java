package formatter.tokenfactory;

import ast.tokens.AstToken;
import ast.tokens.AstTokenType;
import ast.tokens.ValueAstToken;

public class TokenFactory {
  public AstToken createToken(AstTokenType type, String value, int column, int line) {
    return new ValueAstToken(type, value, column, line);
  }
}
