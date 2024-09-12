package formatter.nodeformatter;

import formatter.rules.Rule;
import formatter.rules.alwaysactive.AlwaysActiveRules;
import java.util.List;
import token.Token;

public class ReadFormatter implements NodeFormatter {
  @Override
  public List<Token> formatToken(List<Token> tokens, List<Rule> rules) {
    if (rules.isEmpty()) {
      return tokens;
    }
    List<Token> result = tokens;
    for (Rule rule : rules) {
      if (rule instanceof AlwaysActiveRules) {
        result = rule.format(result);
      }
    }
    return result;
  }
}
