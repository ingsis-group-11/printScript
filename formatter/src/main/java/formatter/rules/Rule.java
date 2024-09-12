package formatter.rules;

import java.util.List;
import token.Token;

public interface Rule {
  void setValue(String value);

  List<Token> format(List<Token> tokens);
}
