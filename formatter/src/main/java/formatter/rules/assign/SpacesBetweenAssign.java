package formatter.rules.assign;

import formatter.rules.TokenIndex;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpacesBetweenAssign implements AssignRule {
  private final TokenIndex tokenIndex = new TokenIndex();
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<Token> format(List<Token> tokens) {
    if (Objects.equals(value, "false")) {
      return tokens;
    }
    List<Token> result = new ArrayList<>(tokens);
    int assignIndex = tokenIndex.getIndex(tokens, TokenType.ASSIGN);
    result.add(assignIndex, new ValueToken(TokenType.WHITESPACE, " ", tokens.get(assignIndex).getColumn(),
        tokens.get(assignIndex).getLine()));
    result.add(assignIndex + 2, new ValueToken(TokenType.WHITESPACE, " ",
        tokens.get(assignIndex).getColumn() + 2, tokens.get(assignIndex).getLine()));
    return result;
  }
}
