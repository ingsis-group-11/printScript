package parser.syntax;

import providers.iterator.PrintScriptIterator;
import token.Token;
import token.TokenType;

public class TokenStream {
  private final PrintScriptIterator<Token> iterator;

  public TokenStream(PrintScriptIterator<Token> iterator) {
    this.iterator = iterator;
  }

  public TokenStream advance() {
    PrintScriptIterator<Token> newIterator = iterator;
    if (iterator.hasNext()) {
      newIterator = iterator.next();
      while ((newIterator.current().getType() == TokenType.WHITESPACE
          || newIterator.current().getType() == TokenType.LINE_BREAK)
          && newIterator.hasNext()) {
        newIterator = newIterator.next();
      }
    }
    return new TokenStream(newIterator);
  }

  public boolean hasNext() {
    return iterator.hasNext();
  }

  public Token getCurrentToken() {
    return iterator.current();
  }

  public boolean match(TokenType type) {
    return iterator.current() != null && iterator.current().getType() == type;
  }

  public void expect(TokenType type, String errorMessage) {
    Token current = iterator.current();
    if (current == null || !match(type)) {
      Token last = iterator.last();
      String message =
          errorMessage
              + " at column "
              + (last.getColumn() + last.getValue().length())
              + " line "
              + last.getLine();
      throw new RuntimeException(message);
    }
  }
}
