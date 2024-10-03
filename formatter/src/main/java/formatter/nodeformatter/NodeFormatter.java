package formatter.nodeformatter;

import ast.tokens.AstToken;
import formatter.rules.Rule;
import java.util.List;

public interface NodeFormatter {
  List<AstToken> formatToken(List<AstToken> tokens, List<Rule> rules);
}
