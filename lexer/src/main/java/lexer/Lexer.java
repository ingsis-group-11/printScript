package lexer;

import factory.LexerMapFactory;
import iterator.InputReader;
import result.LexingResult;

public class Lexer {
  private final Tokenizer tokenizer;
  private final InputReader input;

  public Lexer(InputReader input, String version) {
    LexerMapFactory factory = new LexerMapFactory();
    tokenizer = new Tokenizer(input, factory.getLexerMap(version));
    this.input = input;
  }

  public LexingResult lex() {
    return tokenizer.tokenize(input);
  }
}
