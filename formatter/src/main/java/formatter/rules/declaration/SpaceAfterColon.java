package formatter.rules.declaration;

import formatter.rules.TokenIndex;
import formatter.rules.assignation.AssignationRule;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpaceAfterColon implements AssignationRule {
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
    int colonIndex = tokenIndex.getIndex(tokens, TokenType.COLON);
    result.add(colonIndex + 1, new ValueToken(TokenType.WHITESPACE, " ", tokens.get(colonIndex).getColumn() + 1,
        tokens.get(colonIndex).getLine()));
    return result;
  }
}
