package formatter.nodeFormatter;

import formatter.rules.Rule;
import token.Token;

import java.util.List;

public interface NodeFormatter {
  List<Token> formatToken(List<Token> tokens, List<Rule> rules);
}
