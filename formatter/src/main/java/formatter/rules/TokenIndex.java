package formatter.rules;

import token.Token;
import token.TokenType;

import java.util.List;

public class TokenIndex {
  public int getIndex(List<Token> tokens, TokenType type) {
    for (int i = 0; i < tokens.size(); i++) {
      if (tokens.get(i).getType() == type) {
        return i;
      }
    }
    return -1;
  }
}
