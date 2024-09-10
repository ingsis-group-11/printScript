package formatter.tokenFormatter;

import formatter.rules.semicolon.SemicolonRule;
import formatter.rules.Rule;
import token.Token;

import java.util.List;

public class SemicolonFormatter implements TokenFormatter {
    @Override
    public List<Token> formatToken(Token token, List<Rule> rules) {
      if (rules.isEmpty()) return List.of(token);
      List<Token> result = List.of(token);
      for (Rule rule : rules) {
        if (rule instanceof SemicolonRule) {
          result = rule.format(result);
        }
      }
      return result;
    }
}
