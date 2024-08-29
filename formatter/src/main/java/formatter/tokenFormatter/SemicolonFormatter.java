package formatter.tokenFormatter;

import formatter.rules.FormatterTokenVisitor;
import formatter.rules.PrintRule;
import formatter.rules.Rule;
import formatter.rules.SemicolonRule;
import token.Token;

import java.util.List;

public class SemicolonFormatter implements TokenFormatter {
    @Override
    public List<Token> formatToken(List<Token> tokens, List<Rule> rules) {
      if (rules.isEmpty()) return tokens;
      FormatterTokenVisitor visitor = new FormatterTokenVisitor();
      List<Token> result = List.copyOf(tokens);
      for (Rule rule : rules) {
        if (rule instanceof SemicolonRule) {
          result = rule.accept(visitor, result);
        }
      }
      return result;    }
}