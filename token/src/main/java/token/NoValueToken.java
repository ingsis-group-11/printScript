package token;

public record NoValueToken(TokenType type, Integer column, Integer line) implements Token {

    @Override
    public TokenType getType() {
        return type;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public Integer getColumn() {
        return column;
    }

    @Override
    public Integer getLine() {
        return line;
    }
}
