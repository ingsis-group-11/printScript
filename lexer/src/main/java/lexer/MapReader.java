package lexer;

import token.TokenType;

public interface MapReader {
  TokenType getTokenType(String word);
  boolean containsKey(String key);
}
