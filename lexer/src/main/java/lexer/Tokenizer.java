package lexer;

import fileReader.InputReader;
import result.LexingResult;
import result.SuccessfulResult;
import result.UnsuccessfulResult;
import token.TokenType;
import token.ValueToken;

public class Tokenizer {
  private final InputReader input;
  private int currentChar;
  private int line;
  private int column;
  private final MapReader mapReader = new MapReader();

  public Tokenizer(InputReader input) {
    this.input = input;
    this.line = 0;
    this.column = 0;
  }

  public LexingResult tokenize(InputReader input) {
    this.currentChar = input.current();

    if (isCarriageReturn()) {
      advance();
    }

    if (Character.isWhitespace(currentChar)) {
      return whiteSpace();
    }

    if (Character.isLetter(currentChar)) {
      return character();
    }

    if (Character.isDigit(currentChar)) {
      return number();
    }

    if (currentChar == '"') {
      return string();
    }

    String charAsString = Character.toString((char) currentChar);
    if (mapReader.containsKey(charAsString)) {
      SuccessfulResult result = new SuccessfulResult(new ValueToken(mapReader.getTokenType(charAsString), charAsString, column, line));
      advance();
      return result;
    }

    return new UnsuccessfulResult("Invalid character: " + currentChar + " at " + line + ":" + column);

  }

  private void advance() {
    column++;
    if (!input.hasNext()) {
      currentChar = '\0';
    } else {
      currentChar = input.next();
    }
  }

  private LexingResult character() {
    StringBuilder result = new StringBuilder();
    int newColumn = column;
    while (currentChar != '\0' && (Character.isLetterOrDigit(currentChar) || currentChar == '_')) {
      result.append(currentChar);
      advance();
    }
    String value = result.toString();
    return new SuccessfulResult(new ValueToken(mapReader.getTokenType(value), value, newColumn, line));
  }

  private LexingResult number() {
    StringBuilder result = new StringBuilder();
    int newColumn = column;
    while (currentChar != '\0' && Character.isDigit(currentChar)) {
      result.append(currentChar);
      advance();
    }
    return new SuccessfulResult(new ValueToken(TokenType.NUMBER, result.toString(), newColumn, line));
  }

  private LexingResult string() {
    StringBuilder result = new StringBuilder();
    int newColumn = column;
    advance();
    while (currentChar != '\0' && currentChar != '"') {
      result.append(currentChar);
      advance();
    }
    advance();
    return new SuccessfulResult(new ValueToken(TokenType.STRING, result.toString(), newColumn, line));
  }

  private boolean isCarriageReturn() {
    return currentChar == 13;
  }

  private LexingResult whiteSpace() {
    if (isLineBreak()) {
      ValueToken valueToken = new ValueToken(TokenType.LINE_BREAK, "\n", column, line);
      advance();
      lineBreak();
      return new SuccessfulResult(valueToken);
    } else {
      ValueToken valueToken = new ValueToken(TokenType.WHITESPACE, String.valueOf(currentChar), column, line);
      advance();
      return new SuccessfulResult(valueToken);
    }
  }

  private boolean isLineBreak() {
    return currentChar == 10;
  }

  private void lineBreak() {
    line++;
    column = 0;
  }
}
