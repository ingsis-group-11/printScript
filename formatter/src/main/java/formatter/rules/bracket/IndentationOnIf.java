package formatter.rules.bracket;

import token.Token;

import java.util.List;

public class IndentationOnIf implements BracketRule {
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<Token> format(List<Token> tokens) {
    return List.of();
  }
}