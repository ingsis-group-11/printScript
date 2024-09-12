package formatter.rules.alwaysactive;

import formatter.rules.TokenIndex;
import java.util.ArrayList;
import java.util.List;
import token.Token;
import token.TokenType;
import token.ValueToken;

public class LineBreakAfterSemicolon implements AlwaysActiveRules {
  private final TokenIndex tokenIndex = new TokenIndex();
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<Token> format(List<Token> tokens) {
    List<Token> result = new ArrayList<>(tokens);
    int semicolonIndex = tokenIndex.getIndex(tokens, TokenType.SEMICOLON);
    result.add(
        semicolonIndex + 1,
        new ValueToken(
            TokenType.LINE_BREAK,
            "\n",
            tokens.get(semicolonIndex).getColumn() + 1,
            tokens.get(semicolonIndex).getLine()));
    return result;
  }
}