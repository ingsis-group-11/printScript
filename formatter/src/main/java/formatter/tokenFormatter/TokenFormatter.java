package formatter.tokenFormatter;

import formatter.rules.Rule;
import token.Token;

import java.util.List;

public interface TokenFormatter {
  List<Token> formatToken(Token token, List<Rule> rules);
}
