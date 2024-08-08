package lexer;

import token.TokenType;

import java.util.HashMap;

public class MapReader {
    HashMap<String, TokenType> tokenMap = new HashMap<>();

    public MapReader() {
        tokenMap.put("let", TokenType.LET_KEYWORD);
        tokenMap.put("println", TokenType.PRINT_KEYWORD);
        tokenMap.put("string", TokenType.STRING_TYPE);
        tokenMap.put("number", TokenType.NUMBER_TYPE);
        tokenMap.put(";", TokenType.SEMICOLON);
        tokenMap.put("=", TokenType.ASSIGN);
        tokenMap.put("+", TokenType.OPERATOR);
        tokenMap.put("-", TokenType.OPERATOR);
        tokenMap.put("*", TokenType.OPERATOR);
        tokenMap.put("/", TokenType.OPERATOR);
        tokenMap.put(")", TokenType.PARENTHESIS_CLOSE);
        tokenMap.put("(", TokenType.PARENTHESIS_OPEN);
        tokenMap.put(":", TokenType.COLON);
    }

    public TokenType getTokenType(String word) {
        if(tokenMap.get(word) == null) {
            return TokenType.IDENTIFIER;
        }
        return tokenMap.get(word);
    }

    public boolean containsKey(String key) {
        return tokenMap.containsKey(key);
    }
}
