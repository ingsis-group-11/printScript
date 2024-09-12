package formatter.rules;

import java.util.List;
import token.Token;
import token.TokenType;

public class TokenIndex {
  public int getIndex(List<Token> tokens, TokenType type) {
    for (int i = 0; i < tokens.size(); i++) {
      if (tokens.get(i).getType() == type) {
        return i;
      }
    }
    return -1;
  }

  public int getIndex(List<Token> tokens, TokenType type, int start) {
    for (int i = start; i < tokens.size(); i++) {
      if (tokens.get(i).getType() == type) {
        return i;
      }
    }
    return -1;
  }
}
