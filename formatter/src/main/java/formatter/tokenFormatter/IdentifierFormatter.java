package formatter.tokenFormatter;

import formatter.rules.identifier.IdentifierRule;
import formatter.rules.Rule;
import token.Token;

import java.util.List;

public class IdentifierFormatter implements TokenFormatter {
    @Override
    public List<Token> formatToken(Token token, List<Rule> rules) {
      if (rules.isEmpty()) return List.of(token);
      List<Token> result = List.of(token);
      for (Rule rule : rules) {
        if (rule instanceof IdentifierRule) {
          result = rule.format(result);
        }
      }
      return result;
    }
}
