package formatter.rules;

import token.Token;

import java.util.List;

public interface Rule {
  List<Token> accept(RuleVisitor visitor, List<Token> tokens);
}
