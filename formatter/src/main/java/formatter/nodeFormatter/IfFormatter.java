package formatter.nodeFormatter;

import formatter.rules.Rule;
import formatter.rules.conditional.IfRule;
import java.util.List;
import token.Token;

public class IfFormatter implements NodeFormatter {

  @Override
  public List<Token> formatToken(List<Token> tokens, List<Rule> rules) {
    if (rules.isEmpty()) return tokens;
    List<Token> result = tokens;
    for (Rule rule : rules) {
      if (rule instanceof IfRule) {
        result = rule.format(result);
      }
    }
    return result;
  }
}
