package formatter.rules;

import token.Token;

import java.util.List;

public interface Rule {
  void setValue(String value);
  List<Token> format(List<Token> tokens);
}
