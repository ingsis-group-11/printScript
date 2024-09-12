package formatter.rules.conditional;

import formatter.rules.TokenIndex;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import token.Token;
import token.TokenType;
import token.ValueToken;

public class BracketOnSameLineAsIf implements IfRule {
  private final TokenIndex tokenIndex = new TokenIndex();
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<Token> format(List<Token> tokens) {
    List<Token> result = new ArrayList<>(tokens);
    int bracketIndex = tokenIndex.getIndex(tokens, TokenType.BRACE_OPEN);
    if (Objects.equals(value, "false")) {
      result.add(
          bracketIndex,
          new ValueToken(
              TokenType.LINE_BREAK,
              "\n",
              tokens.get(bracketIndex).getColumn() + 1,
              tokens.get(bracketIndex).getLine()));
    } else {
      result.add(
          bracketIndex,
          new ValueToken(
              TokenType.WHITESPACE,
              " ",
              tokens.get(bracketIndex).getColumn() + 1,
              tokens.get(bracketIndex).getLine()));
    }
    return result;
  }
}
