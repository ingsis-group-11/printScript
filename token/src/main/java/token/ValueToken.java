package token;

public record ValueToken(TokenType type, String value) implements Token {

    public String getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }

}
