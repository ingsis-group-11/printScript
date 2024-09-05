package formatter.tokenFormatter;

import formatter.rules.Rule;
import formatter.rules.bracket.BracketRule;
import formatter.rules.visitor.FormatterTokenVisitor;
import token.Token;

import java.util.List;

public class BracketFormatter implements TokenFormatter {
  @Override
  public List<Token> formatToken(Token token, List<Rule> rules) {
    if (rules.isEmpty()) return List.of(token);
    FormatterTokenVisitor visitor = new FormatterTokenVisitor();
    List<Token> result = List.of(token);
    for (Rule rule : rules) {
      if (rule instanceof BracketRule) {
        result = rule.accept(visitor, result);
      }
    }
    return result;
  }
}
