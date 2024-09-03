package lexer;

import fileReader.InputReader;
import result.*;

public class Lexer {
  private final Tokenizer tokenizer;
  private final InputReader input;

  public Lexer(InputReader input) {
    tokenizer = new Tokenizer(input);
    this.input = input;
  }

  public LexingResult lex() {
    return tokenizer.tokenize(input);
  }
}
