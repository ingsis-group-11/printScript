package formatter;

import ast.nodes.AstNode;
import formatter.rules.Rule;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FormatterIterator implements Iterator<String> {
  private final Formatter formatter;
  private final Iterator<AstNode> iterator;
  private final List<Rule> rules;

  public FormatterIterator(Iterator<AstNode> iterator, List<Rule> rules) {
    this.rules = rules;
    this.formatter = new Formatter();
    this.iterator = iterator;
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public String next() {
    String result;
    try {
      result = formatter.formatFile(iterator, rules);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
}
