package formatter.tokenFormatter;

import formatter.rules.ColonRule;
import formatter.rules.FormatterTokenVisitor;
import formatter.rules.Rule;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.List;

public class ColonFormatter implements TokenFormatter {
    @Override
    public List<Token> formatToken(List<Token> tokens, List<Rule> rules) {
      if (rules.isEmpty()) return tokens;
      if (!sameTypeRules(rules)) {
        tokens.add(new ValueToken(TokenType.COLON, ":", tokens.getLast().getColumn() + 1,
            tokens.getLast().getLine()));
        return tokens;
      }
      FormatterTokenVisitor visitor = new FormatterTokenVisitor();
      List<Token> result = List.copyOf(tokens);
      for (Rule rule : rules) {
        if (rule instanceof ColonRule) {
          result = rule.accept(visitor, result);
        }
      }
      return result;
    }

  private boolean sameTypeRules(List<Rule> rules) {
    return rules.stream().anyMatch(rule -> rule instanceof ColonRule);
  }
}
