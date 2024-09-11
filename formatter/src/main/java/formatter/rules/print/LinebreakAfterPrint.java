package formatter.rules.print;

import formatter.rules.TokenIndex;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.List;

public class LinebreakAfterPrint implements PrintRule {
  private final TokenIndex tokenIndex = new TokenIndex();
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
    public List<Token> format(List<Token> tokens) {
    int lineBreaks = Integer.parseInt(value);
    List<Token> result = new ArrayList<>(tokens);
    int printEndIndex = tokenIndex.getIndex(tokens, TokenType.SEMICOLON);
    for (int i = 0; i < lineBreaks - 1; i++) {
      result.add(printEndIndex + 1, new ValueToken(TokenType.LINE_BREAK, "\n", tokens.get(printEndIndex).getColumn(),
          tokens.get(printEndIndex).getLine() + i));
    }
    return result;
    }
}
