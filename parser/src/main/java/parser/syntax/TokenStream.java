package parser.syntax;

import java.util.Iterator;
import token.Token;
import token.TokenType;

public class TokenStream {
  private final Iterator<Token> iterator;
  private Token currentToken;
  private Token lastToken;

  public TokenStream(Iterator<Token> iterator) {
    this.iterator = iterator;
    advance();
  }

  public void advance() {
    lastToken = currentToken;
    while (iterator.hasNext()) {
      currentToken = iterator.next();
      if (currentToken.getType() != TokenType.WHITESPACE) {
        return;
      }
    }
    currentToken = null;
  }

  public boolean hasNext() {
    return currentToken != null;
  }

  public Token getCurrentToken() {

    if (currentToken.getType() == TokenType.LINE_BREAK || currentToken.getType() == TokenType.WHITESPACE) {
      advance();
    }

    return currentToken;
  }

  public Token getLastToken() {
    return lastToken;
  }


  public boolean match(TokenType type) {
    return currentToken != null && currentToken.getType() == type;
  }

  public void expect(TokenType type, String errorMessage) {
    if (currentToken == null || !match(type)) {
      String message =
          errorMessage
              + (currentToken != null
              ? " at column " + (lastToken.getColumn() + lastToken.getValue().length()) + " line " + currentToken.getLine()
              : " at column "
              + (lastToken != null ? (lastToken.getColumn() + lastToken.getValue().length()) : "unknown")
              + " line "
              + (lastToken != null ? lastToken.getLine() : "unknown"));
      throw new RuntimeException(message);
    } else {
      advance();
    }
  }
}