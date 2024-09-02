package formatter.rules.assign;

import formatter.rules.visitor.RuleVisitor;
import token.Token;

import java.util.List;

public class SpacesBetweenAssign implements AssignRule {
  @Override
  public List<Token> accept(RuleVisitor visitor, List<Token> tokens) {
    return visitor.visit(this, tokens);
  }
}
