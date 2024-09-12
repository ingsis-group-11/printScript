package parser.syntax;

import providers.iterator.PrintScriptIterator;
import token.Token;
import token.TokenType;

public class TokenStream {
  private final PrintScriptIterator<Token> iterator;

  public TokenStream(PrintScriptIterator<Token> iterator) {
    this.iterator = iterator;
  }

  public void advance() {

    if (iterator.hasNext()) {
      iterator.next();
      while ((iterator.current().getType() == TokenType.WHITESPACE
              || iterator.current().getType() == TokenType.LINE_BREAK)
          && iterator.hasNext()) {
        iterator.next();
      }
    }
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
