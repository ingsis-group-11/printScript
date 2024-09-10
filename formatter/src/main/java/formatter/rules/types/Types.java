package formatter.rules.types;

import formatter.rules.Rule;
import token.Token;

import java.util.List;

public class Types implements TypesRule {
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
