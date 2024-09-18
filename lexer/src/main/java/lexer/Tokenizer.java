package lexer;

import iterator.InputReader;
import result.LexingResult;
import result.SuccessfulResult;
import result.UnsuccessfulResult;
import token.TokenType;
import token.ValueToken;

public class Tokenizer {
  private int line;
  private int column;
  private final MapReader mapReader;

  public Tokenizer(MapReader mapReader) {
    this.line = 1;
    this.column = 1;
    this.mapReader = mapReader;
  }

  public LexingResult tokenize(InputReader input) {
    char currentChar = input.current();

    if (isCarriageReturn(currentChar)) {
      currentChar = advance(input);
    }

    if (Character.isWhitespace(currentChar)) {
      return tokenizeWhiteSpace(currentChar, input);
    }

    if (Character.isLetter(currentChar)) {
      return tokenizeCharacters(currentChar, input);
    }

    if (Character.isDigit(currentChar)) {
      return tokenizeNumbers(currentChar, input);
    }

    if (currentChar == '"') {
      return tokenizeString(input);
    }

    String charAsString = Character.toString(currentChar);
    if (mapReader.containsKey(charAsString)) {
      SuccessfulResult result =
          new SuccessfulResult(
              new ValueToken(mapReader.getTokenType(charAsString), charAsString, column, line));
      advance(input);
      return result;
    }

    return new UnsuccessfulResult(
        "Invalid character: " + currentChar + " at " + line + ":" + column);
  }

  private char advance(InputReader input) {
    char currentChar;
    if (!input.hasNext()) {
      currentChar = '\0';
    } else {
      currentChar = input.next();
      if (!isCarriageReturn(currentChar)) {
        column++;
      }
    }
    return currentChar;
  }

  private LexingResult tokenizeCharacters(char currentChar, InputReader input) {
    StringBuilder result = new StringBuilder();
    int newColumn = column;
    while (currentChar != '\0' && (Character.isLetterOrDigit(currentChar) || currentChar == '_')) {
      result.append(currentChar);
      currentChar = advance(input);
    }
    String value = result.toString();
    return new SuccessfulResult(
        new ValueToken(mapReader.getTokenType(value), value, newColumn, line));
  }

  private LexingResult tokenizeNumbers(char currentChar, InputReader input) {
    StringBuilder result = new StringBuilder();
    int newColumn = column;
    while (currentChar != '\0' && (Character.isDigit(currentChar) || currentChar == '.')) {
      result.append(currentChar);
      currentChar = advance(input);
    }
    return new SuccessfulResult(
        new ValueToken(TokenType.NUMBER, result.toString(), newColumn, line));
  }

  private LexingResult tokenizeString(InputReader input) {
    StringBuilder result = new StringBuilder();
    int newColumn = column;
    char currentChar = advance(input);
    while (currentChar != '\0' && currentChar != '"') {
      result.append(currentChar);
      currentChar = advance(input);
    }
    advance(input);
    return new SuccessfulResult(
        new ValueToken(TokenType.STRING, result.toString(), newColumn, line));
  }

  private boolean isCarriageReturn(char currentChar) {
    return currentChar == 13;
  }

  private LexingResult tokenizeWhiteSpace(char currentChar, InputReader input) {
    if (isLineBreak(currentChar)) {
      ValueToken valueToken = new ValueToken(TokenType.LINE_BREAK, "\n", column, line);
      advance(input);
      lineBreak();
      return new SuccessfulResult(valueToken);
    } else {
      ValueToken valueToken =
          new ValueToken(TokenType.WHITESPACE, String.valueOf(currentChar), column, line);
      advance(input);
      return new SuccessfulResult(valueToken);
    }
  }

  private boolean isLineBreak(char currentChar) {
    return currentChar == 10;
  }

  private void lineBreak() {
    line++;
    column = 1;
  }
}
