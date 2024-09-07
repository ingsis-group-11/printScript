package formatter;

import fileReader.InputStreamToString;
import token.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class FormatterIterator implements Iterator<String> {
  private final Formatter formatter;
  private final Iterator<Token> iterator;

  private final String jsonString;

  public FormatterIterator(Iterator<Token> iterator, InputStream configRules) {
    this.jsonString = new InputStreamToString().read(configRules);
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
      result = formatter.formatFile(iterator, jsonString);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
}
