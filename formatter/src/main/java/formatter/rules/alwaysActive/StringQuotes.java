package formatter.rules.alwaysActive;

import formatter.rules.TokenIndex;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.List;

public class StringQuotes implements AlwaysActiveRules {
  private final TokenIndex tokenIndex = new TokenIndex();
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<Token> format(List<Token> tokens) {
    List<Token> result = new ArrayList<>(tokens);
    int stringIndex = tokenIndex.getIndex(tokens, TokenType.STRING);
    Token token = tokens.get(stringIndex);
    result.remove(stringIndex);
    result.add(stringIndex, new ValueToken(TokenType.STRING, '"' + token.getValue() + '"', token.getColumn() + 1,
        token.getLine()));
    return result;
  }
}
