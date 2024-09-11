package formatter;

import AST.nodes.ASTNode;
import formatter.nodeFormatter.TokenListFactory;
import formatter.rules.Rule;
import formatter.nodeFormatter.NodeFormatter;
import token.Token;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Formatter {
  TokenOutput tokenOutput = new TokenOutput();
  TokenListFactory tokenListFactory = new TokenListFactory();

  public String formatFile(Iterator<ASTNode> nodes, List<Rule> rules) throws IOException {
    ASTMap nodeMap = new ASTMap();

    ASTNode node = nodes.next();
    tokenListFactory.addRules(rules);
    List<Token> tokens = node.accept(tokenListFactory);
    if (nodeMap.containsNode(node)) {
      NodeFormatter tokenFormatter = nodeMap.getNodeFormatter(node);
      return tokenOutput.toString(tokenFormatter.formatToken(tokens, rules));
    }
    else {
      return tokenOutput.toString(tokens);
    }
  }
}