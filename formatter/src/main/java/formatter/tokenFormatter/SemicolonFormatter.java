package formatter.tokenFormatter;

import formatter.rules.semicolon.SemicolonRule;
import formatter.rules.visitor.FormatterTokenVisitor;
import formatter.rules.Rule;
import token.Token;

import java.util.List;

public class SemicolonFormatter implements TokenFormatter {
    @Override
    public List<Token> formatToken(Token token, List<Rule> rules) {
      if (rules.isEmpty()) return List.of(token);
      FormatterTokenVisitor visitor = new FormatterTokenVisitor();
      List<Token> result = List.of(token);
      for (Rule rule : rules) {
        if (rule instanceof SemicolonRule) {
          result = rule.accept(visitor, result);
        }
      }
      return result;
    }
}
