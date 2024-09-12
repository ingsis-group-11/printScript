package formatter.nodeFormatter;

import formatter.rules.Rule;
import java.util.List;
import token.Token;

public interface NodeFormatter {
  List<Token> formatToken(List<Token> tokens, List<Rule> rules);
}
