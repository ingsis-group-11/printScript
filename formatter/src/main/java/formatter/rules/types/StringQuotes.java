package formatter.rules.types;

import formatter.rules.visitor.RuleVisitor;
import token.Token;

import java.util.List;

public class StringQuotes implements StringRule {
  @Override
  public List<Token> accept(RuleVisitor visitor, List<Token> tokens) {
    return visitor.visit(this, tokens);
  }
}
