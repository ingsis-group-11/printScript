package formatter.rules;

import formatter.rules.visitor.RuleVisitor;
import token.Token;

import java.util.Iterator;
import java.util.List;

public interface Rule {
  List<Token> accept(RuleVisitor visitor, Iterator<Token> tokens);
}
