package parser.syntax;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import token.Token;
import token.TokenType;

public class TokenStream {
  private final Iterator<Token> iterator;
  private Token currentToken;
  private Token lastToken;
  private final List<String> errorMessages = new ArrayList<>();

  public TokenStream(List<Token> tokens) {
    // Filter out WHITESPACE and LINEBREAK tokens
    List<Token> filteredTokens = tokens.stream()
            .filter(token -> token.getType() != TokenType.WHITESPACE && token.getType() != TokenType.LINE_BREAK)
            .collect(Collectors.toList());
    this.iterator = filteredTokens.iterator();
    advance();
  }

  public void advance() {
    lastToken = currentToken;
    if (iterator.hasNext()) {
      currentToken = iterator.next();
    } else {
      currentToken = null;
    }
  }

  public Token getCurrentToken() {
    return currentToken;
  }

  public boolean match(TokenType type) {
    return currentToken != null && currentToken.getType() == type;
  }

  public void expect(TokenType type, String errorMessage) {
    if (currentToken == null || !match(type)) {
      String message =
              errorMessage
                      + (currentToken != null
                      ? " at column " + currentToken.getColumn() + " line " + currentToken.getLine()
                      : " at column "
                      + (lastToken != null ? lastToken.getColumn() : "unknown")
                      + " line "
                      + (lastToken != null ? lastToken.getLine() : "unknown"));
      errorMessages.add(message);
    } else {
      advance();
    }
  }

  public List<String> getErrorMessages() {
    return errorMessages;
  }
}