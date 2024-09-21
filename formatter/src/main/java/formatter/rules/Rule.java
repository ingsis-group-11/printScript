package formatter.rules;

import java.util.List;

import ast.tokens.AstToken;

public interface Rule {
  void setValue(String value);

  List<AstToken> format(List<AstToken> tokens);
}
