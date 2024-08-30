package formatter.rules;

import formatter.rules.visitor.RuleVisitor;
import token.Token;

import java.util.Iterator;
import java.util.List;

public class LineBreakAfterSemicolon implements SemicolonRule {
  @Override
  public List<Token> accept(RuleVisitor visitor, Iterator<Token> tokens) {
    return visitor.visit(this, tokens);
  }
}
