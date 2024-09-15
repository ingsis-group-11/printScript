package parser.iterator;

import java.util.Iterator;
import java.util.List;
import providers.iterator.PrintScriptIterator;
import token.Token;

public class TestTokenIterator implements PrintScriptIterator<Token> {

  private final Iterator<Token> iterator;
  private final Token currentToken;
  private final Token lastToken;

  public TestTokenIterator(List<Token> tokens) {
    this.iterator = tokens.iterator();
    this.lastToken = null;
    if (iterator.hasNext()) {
      this.currentToken = iterator.next();
    } else {
      this.currentToken = null;
    }
  }

  private TestTokenIterator(Iterator<Token> iterator, Token lastToken, Token currentToken) {
    this.iterator = iterator;
    this.lastToken = lastToken;
    this.currentToken = currentToken;
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public PrintScriptIterator<Token> next() {
    Token newLastToken = currentToken;
    Token newCurrentToken = iterator.hasNext() ? iterator.next() : null;

    return new TestTokenIterator(iterator, newLastToken, newCurrentToken);  // Return a new instance
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