package formatter.tokenFormatter;

import formatter.rules.assign.AssignRule;
import formatter.rules.Rule;
import token.Token;

import java.util.List;

public class AssignFormatter implements TokenFormatter {
    @Override
    public List<Token> formatToken(Token token, List<Rule> rules) {
      if (rules.isEmpty()) return List.of(token);
      List<Token> result = List.of(token);
      for (Rule rule : rules) {
        if (rule instanceof AssignRule) {
          result = rule.format(result);
        }
      }
      return result;
    }
}
