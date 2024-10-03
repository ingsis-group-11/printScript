package formatter.rules;

import ast.tokens.AstToken;
import java.util.List;

public interface Rule {
  void setValue(String value);

  List<AstToken> format(List<AstToken> tokens);
}
