package formatter.tokenfactory;

import token.Token;
import token.TokenType;
import token.ValueToken;

public class TokenFactory {
  public Token createToken(TokenType type, String value, int column, int line) {
    return new ValueToken(type, value, column, line);
  }
}
