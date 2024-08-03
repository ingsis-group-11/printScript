package token;

public record NoValueToken(TokenType type) implements Token {

    @Override
    public TokenType getType() {
        return type;
    }

    @Override
    public String getValue() {
        return null;
    }
}
