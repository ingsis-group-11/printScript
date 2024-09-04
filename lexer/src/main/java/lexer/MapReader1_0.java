package lexer;

import java.util.HashMap;
import token.TokenType;

public class MapReader1_0 implements MapReader {
  HashMap<String, TokenType> tokenMap = new HashMap<>();

  public MapReader1_0() {
    tokenMap.put("let", TokenType.LET_KEYWORD);
    tokenMap.put("println", TokenType.PRINT_KEYWORD);
    tokenMap.put("string", TokenType.STRING_TYPE);
    tokenMap.put("number", TokenType.NUMBER_TYPE);
    tokenMap.put(";", TokenType.SEMICOLON);
    tokenMap.put("=", TokenType.ASSIGN);
    tokenMap.put("+", TokenType.OPERATOR);
    tokenMap.put("-", TokenType.OPERATOR);
    tokenMap.put("*", TokenType.OPERATOR);
    tokenMap.put("/", TokenType.OPERATOR);
    tokenMap.put(")", TokenType.PARENTHESIS_CLOSE);
    tokenMap.put("(", TokenType.PARENTHESIS_OPEN);
    tokenMap.put(":", TokenType.COLON);
    tokenMap.put(" ", TokenType.WHITESPACE);
    tokenMap.put("\n", TokenType.LINE_BREAK);
  }

  public TokenType getTokenType(String word) {
    if (tokenMap.containsKey(word)) {
      return tokenMap.get(word);
    }
    return TokenType.IDENTIFIER;
  }

  public boolean containsKey(String key) {
    return tokenMap.containsKey(key);
  }
}
