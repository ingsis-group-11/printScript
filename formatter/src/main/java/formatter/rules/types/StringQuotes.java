package formatter.rules.types;

import formatter.rules.Rule;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.List;

public class StringQuotes implements StringRule {
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<Token> format(List<Token> tokens) {
    Token token = tokens.getLast();
    List<Token> result = new ArrayList<>();
    result.add(new ValueToken(TokenType.STRING, '"' + token.getValue() + '"', token.getColumn() + 1,
        token.getLine()));
    return result;
  }
}
