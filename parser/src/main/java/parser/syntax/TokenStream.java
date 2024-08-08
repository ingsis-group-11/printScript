package parser.syntax;

import token.Token;
import token.TokenType;

import java.util.Iterator;
import java.util.List;

public class TokenStream {
    private final Iterator<Token> iterator;
    private Token currentToken;
    private Token lastToken;

    public TokenStream(List<Token> tokens) {
        this.iterator = tokens.iterator();
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

    public Token getLastToken() {
        return lastToken;
    }

    public boolean match(TokenType type) {
        return currentToken != null && currentToken.getType() == type;
    }

    public void expect(TokenType type, String errorMessage) {
        if (currentToken == null || !match(type)) {
            String message =
                    errorMessage +
                            (currentToken != null ? " at column " +
                                    currentToken.getColumn() + " line " +
                                    currentToken.getLine() :
                                    " at column " +
                                    (lastToken != null ? lastToken.getColumn() : "unknown") +
                                            " line " +
                                            (lastToken != null ? lastToken.getLine() : "unknown"));
            throw new RuntimeException(message);
        }
        advance();
    }
}