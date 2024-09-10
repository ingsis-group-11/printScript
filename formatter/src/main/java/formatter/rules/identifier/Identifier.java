package formatter.rules.identifier;

import token.Token;

import java.util.List;

public class Identifier implements IdentifierRule {
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
    public List<Token> format(List<Token> tokens) {
      return tokens;
    }
}
