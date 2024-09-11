package formatter.nodeFormatter;

import formatter.rules.Rule;
import formatter.rules.alwaysActive.AlwaysActiveRules;
import formatter.rules.declaration.DeclarationRule;
import token.Token;

import java.util.List;

public class DeclarationFormatter implements NodeFormatter {
    @Override
    public List<Token> formatToken(List<Token> tokens, List<Rule> rules) {
      if (rules.isEmpty()) return tokens;
      List<Token> result = tokens;
      for (Rule rule : rules) {
        if (rule instanceof DeclarationRule || rule instanceof AlwaysActiveRules) {
          result = rule.format(result);
        }
      }
      return result;
    }
}
