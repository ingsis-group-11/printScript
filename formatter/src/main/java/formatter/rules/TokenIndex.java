package formatter.rules;

import java.util.List;

import ast.tokens.AstToken;
import ast.tokens.AstTokenType;

public class TokenIndex {
  public int getIndex(List<AstToken> tokens, AstTokenType type) {
    for (int i = 0; i < tokens.size(); i++) {
      if (tokens.get(i).getType() == type) {
        return i;
      }
    }
    return -1;
  }

  public int getIndex(List<AstToken> tokens, AstTokenType type, int start) {
    for (int i = start; i < tokens.size(); i++) {
      if (tokens.get(i).getType() == type) {
        return i;
      }
    }
    return -1;
  }
}
