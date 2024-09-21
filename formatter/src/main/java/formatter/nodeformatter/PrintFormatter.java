package formatter.nodeformatter;

import ast.tokens.AstToken;
import formatter.rules.Rule;
import formatter.rules.alwaysactive.AlwaysActiveRules;
import formatter.rules.print.PrintRule;
import java.util.List;

public class PrintFormatter implements NodeFormatter {
  @Override
  public List<AstToken> formatToken(List<AstToken> tokens, List<Rule> rules) {
    if (rules.isEmpty()) {
      return tokens;
    }
    List<AstToken> result = tokens;
    for (Rule rule : rules) {
      if (rule instanceof PrintRule || rule instanceof AlwaysActiveRules) {
        result = rule.format(result);
      }
    }
    return result;
  }
}
