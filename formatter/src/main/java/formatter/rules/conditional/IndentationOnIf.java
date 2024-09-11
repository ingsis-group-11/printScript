package formatter.rules.conditional;

import token.Token;

import java.util.List;

public class IndentationOnIf implements IfRule {
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