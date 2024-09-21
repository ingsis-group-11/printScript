package formatter.nodeformatter;

import ast.tokens.AstToken;
import formatter.rules.Rule;
import formatter.rules.alwaysactive.AlwaysActiveRules;
import java.util.List;

public class ReadFormatter implements NodeFormatter {
  @Override
  public List<AstToken> formatToken(List<AstToken> tokens, List<Rule> rules) {
    if (rules.isEmpty()) {
      return tokens;
    }
    List<AstToken> result = tokens;
    for (Rule rule : rules) {
      if (rule instanceof AlwaysActiveRules) {
        result = rule.format(result);
      }
    }
    return result;
  }
}
