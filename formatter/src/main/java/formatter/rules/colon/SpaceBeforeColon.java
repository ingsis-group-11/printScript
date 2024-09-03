package formatter.rules.colon;

import formatter.rules.visitor.RuleVisitor;
import token.Token;

import java.util.List;

public class SpaceBeforeColon implements ColonRule {
  @Override
  public List<Token> accept(RuleVisitor visitor, List<Token> tokens) {
    return visitor.visit(this, tokens);
  }
}