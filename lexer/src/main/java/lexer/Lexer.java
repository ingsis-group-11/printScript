package lexer;

import fileReader.InputReader;
import result.*;

import java.io.IOException;

public class Lexer {

  public LexingResult lex(InputReader input) {
    Tokenizer tokenizer = new Tokenizer(input, 0, 0);

    return tokenizer.tokenize(input);
  }

  public boolean hasNext(InputReader input) throws IOException {
    return input.hasNext();
  }
}
