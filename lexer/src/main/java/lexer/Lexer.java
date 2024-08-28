package lexer;

import result.*;
import token.Token;

import java.util.List;

public class Lexer {
  public LexingResult lex(String stringFile) {
    Tokenizer tokenizer = new Tokenizer();
    LexingResult lexerResult = tokenizer.tokenize(stringFile);

    return lexerResult;
  }
}
