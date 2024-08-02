package token;

public record NoValueToken(TokenType type) {

    public String getValue() {
        return "";
    }

    public String getType() {
        return type.toString();
    }

}
