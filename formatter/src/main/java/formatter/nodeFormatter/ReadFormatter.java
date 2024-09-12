package formatter.nodeFormatter;

import formatter.rules.Rule;
import formatter.rules.alwaysActive.AlwaysActiveRules;
import token.Token;

import java.util.List;

public class ReadFormatter implements NodeFormatter{
  @Override
  public List<Token> formatToken(List<Token> tokens, List<Rule> rules) {

    if (rules.isEmpty()) return tokens;
    List<Token> result = tokens;
    for (Rule rule : rules) {
      if (rule instanceof AlwaysActiveRules) {
        result = rule.format(result);
      }
    }
    return result;
  }
}
