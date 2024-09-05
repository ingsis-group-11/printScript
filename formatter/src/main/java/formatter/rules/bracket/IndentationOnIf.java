package formatter.rules.bracket;

import formatter.rules.conditional.IfRule;
import formatter.rules.visitor.RuleVisitor;
import token.Token;

import java.util.List;

public class IndentationOnIf implements IfRule {
  private final int indentationLevel;

  public IndentationOnIf(int indentationLevel) {
    this.indentationLevel = indentationLevel;
  }

  @Override
  public List<Token> accept(RuleVisitor visitor, List<Token> tokens) {
    return visitor.visit(this, tokens, indentationLevel);
  }
}
