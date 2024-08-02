package token;

public record ValueToken(TokenType type, String value) implements Token {

    public String getValue() {
        return value;
    }

    public String getType() {
        return type.toString();
    }

}
