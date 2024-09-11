package parser.iterator;

import providers.iterator.PrintScriptIterator;
import token.Token;

import java.util.Iterator;
import java.util.List;

public class TestTokenIterator implements PrintScriptIterator<Token> {

  private final Iterator<Token> iterator;
  private Token currentToken;
  private Token lastToken;

  public TestTokenIterator(List<Token> tokens){

    this.iterator = tokens.iterator();
    this.lastToken = null;
    if (iterator.hasNext()) {
      currentToken = iterator.next();
    }
    else {
      currentToken = null;
    }
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public Token next() {
    lastToken = currentToken;
    currentToken = iterator.next();
    return currentToken;
  }

  @Override
  public Token current() {
    return currentToken;
  }

  @Override
  public Token last() {
    return lastToken;
  }
}
