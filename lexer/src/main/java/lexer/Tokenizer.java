package lexer;

import result.LexingResult;
import result.SuccessfulResult;
import result.UnsuccessfulResult;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private String inputText;
    private int position;
    private char currentChar;
    private int line;
    private int column;
    private final MapReader mapReader = new MapReader();

    public Tokenizer() {
        this.position = 0;
        this.line = 1;
        this.column = 1;
    }

    private void advance() {
        position++;
        column++;
        if (position >= inputText.length()) {
            currentChar = '\0'; // End of input
        } else {
            currentChar = inputText.charAt(position);
        }
    }

    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    private Token identifier() {
        StringBuilder result = new StringBuilder();
        int newColumn = column;
        while (currentChar != '\0' && (Character.isLetterOrDigit(currentChar) || currentChar == '_')) {
            result.append(currentChar);
            advance();
        }
        String value = result.toString();
        return new ValueToken(mapReader.getTokenType(value), value, newColumn, line);
    }

    private Token number() {
        StringBuilder result = new StringBuilder();
        int newColumn = column;
        while (currentChar != '\0' && Character.isDigit(currentChar)) {
            result.append(currentChar);
            advance();
        }
        return new ValueToken(TokenType.NUMBER, result.toString(), newColumn, line);
    }

    private Token string() {
        StringBuilder result = new StringBuilder();
        int newColumn = column;
        advance(); // Skip opening quote
        while (currentChar != '\0' && currentChar != '"') {
            result.append(currentChar);
            advance();
        }
        advance(); // Skip closing quote
        return new ValueToken(TokenType.STRING, result.toString(), newColumn, line);
    }

    public LexingResult tokenize(String inputText) {
        this.currentChar = inputText.charAt(0);
        this.inputText = inputText;
        List<Token> tokens = new ArrayList<>();

        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }

            if (Character.isLetter(currentChar)) {
                tokens.add(identifier());
                continue;
            }

            if (Character.isDigit(currentChar)) {
                tokens.add(number());
                continue;
            }

            if (currentChar == '"') {
                tokens.add(string());
                continue;
            }

            String charAsString = String.valueOf(currentChar);
            if (mapReader.containsKey(charAsString)) {
                TokenType tokenType = mapReader.getTokenType(charAsString);
                if(tokenType.equals(TokenType.SEMICOLON)) {
                    isSemicolon();
                }
                tokens.add(new ValueToken(mapReader.getTokenType(charAsString), charAsString, column, line));
                advance();
                continue;
            }

            return new UnsuccessfulResult("Unexpected character: " + charAsString, column, line);
        }

        return new SuccessfulResult(tokens);
    }

    private void isSemicolon() {
        line++;
        column = 0;
    }
}
