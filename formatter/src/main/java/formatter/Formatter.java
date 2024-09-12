package formatter;

import ast.nodes.AstNode;
import formatter.nodeformatter.NodeFormatter;
import formatter.rules.Rule;
import formatter.tokenfactory.TokenListFactory;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import token.Token;

public class Formatter {
  TokenOutput tokenOutput = new TokenOutput();
  TokenListFactory tokenListFactory = new TokenListFactory();

  public String formatFile(Iterator<AstNode> nodes, List<Rule> rules) throws IOException {
    AstMap nodeMap = new AstMap();

    AstNode node = nodes.next();
    tokenListFactory.addRules(rules);
    List<Token> tokens = node.accept(tokenListFactory);
    if (nodeMap.containsNode(node)) {
      NodeFormatter tokenFormatter = nodeMap.getNodeFormatter(node);
      return tokenOutput.toString(tokenFormatter.formatToken(tokens, rules));
    } else {
      return tokenOutput.toString(tokens);
    }
  }
}
