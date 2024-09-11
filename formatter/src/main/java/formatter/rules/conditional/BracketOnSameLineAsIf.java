package formatter.rules.conditional;

import formatter.rules.TokenIndex;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    if (Objects.equals(value, "false")) {
      int ifIndex = tokenIndex.getIndex(tokens, TokenType.IF_KEYWORD);
      int bracketIndex = tokenIndex.getIndex(tokens, TokenType.BRACKET_OPEN);
      result.add(ifIndex + 1,new ValueToken(TokenType.WHITESPACE, " ",
          tokens.get(ifIndex).getColumn() + 1, tokens.get(ifIndex).getLine()));
      result.add(bracketIndex + 1, new ValueToken(TokenType.LINE_BREAK, "\n",
          tokens.get(bracketIndex).getColumn() + 1, tokens.get(bracketIndex).getLine()));
      return tokens;
    }
    int ifIndex = tokenIndex.getIndex(tokens, TokenType.IF_KEYWORD);
    int bracketIndex = tokenIndex.getIndex(tokens, TokenType.BRACKET_OPEN);
    result.add(ifIndex, new ValueToken(TokenType.WHITESPACE, " ", tokens.get(ifIndex).getColumn() + 1,
        tokens.get(ifIndex).getLine()));
    result.add(bracketIndex, new ValueToken(TokenType.WHITESPACE, " ",
        tokens.get(bracketIndex).getColumn() + 1, tokens.get(bracketIndex).getLine()));
    return result;
  }
}

