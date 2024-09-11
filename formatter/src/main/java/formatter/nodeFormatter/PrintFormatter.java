package formatter.nodeFormatter;

import formatter.rules.alwaysActive.AlwaysActiveRules;
import formatter.rules.print.PrintRule;
import formatter.rules.Rule;
import token.Token;

import java.util.List;

public class PrintFormatter implements NodeFormatter {
  @Override
  public List<Token> formatToken(List<Token> tokens, List<Rule> rules) {
    if (rules.isEmpty()) return tokens;
    List<Token> result = tokens;
    for (Rule rule : rules) {
      if (rule instanceof PrintRule || rule instanceof AlwaysActiveRules) {
        result = rule.format(result);
      }
    }
    return result;
  }
}
