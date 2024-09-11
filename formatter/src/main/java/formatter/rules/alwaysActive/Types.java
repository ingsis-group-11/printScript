package formatter.rules.alwaysActive;

import token.Token;

import java.util.List;

public class Types implements AlwaysActiveRules {
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
