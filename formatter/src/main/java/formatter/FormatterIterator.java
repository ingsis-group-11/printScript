package formatter;

import token.Token;

import java.io.IOException;
import java.util.Iterator;

public class FormatterIterator implements Iterator<String> {
  private final Formatter formatter;
  private final Iterator<Token> iterator;

  private final String configPathRules;

  public FormatterIterator(Iterator<Token> iterator, String configPathRules) {
    this.configPathRules = configPathRules;
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
      result = formatter.formatFile(iterator, configPathRules);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
}
