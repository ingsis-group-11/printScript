package token;

public record NoValueToken(TokenType type) implements Token {

    public String getType() {
        return type.toString();
    }

}
