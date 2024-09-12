package formatter.nodeFormatter;

import formatter.rules.Rule;
import formatter.rules.alwaysActive.AlwaysActiveRules;
import formatter.rules.assignation.AssignationRule;
import java.util.List;
import token.Token;

public class AssignationFormatter implements NodeFormatter {
  @Override
  public List<Token> formatToken(List<Token> tokens, List<Rule> rules) {
    if (rules.isEmpty()) return tokens;
    List<Token> result = tokens;
    for (Rule rule : rules) {
      if (rule instanceof AssignationRule || rule instanceof AlwaysActiveRules) {
        result = rule.format(result);
      }
    }
    return result;
  }
}
