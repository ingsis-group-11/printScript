package iterator;

import java.util.NoSuchElementException;
import lexer.Lexer;
import providers.iterator.PrintScriptIterator;
import result.*;
import token.Token;

public class TokenIterator implements PrintScriptIterator<Token> {
  private final Lexer lexer;
  private final FileReaderIterator inputIterator;
  private Token currentToken;
  private Token lastToken;

  public TokenIterator(FileReaderIterator inputIterator, String version) {
    this.inputIterator = inputIterator;
    this.lexer = new Lexer(inputIterator, version);
    this.lastToken = null;
    if (inputIterator.hasNext()) {
      this.currentToken = this.next();
    } else {
      this.currentToken = null;
    }
  }

  @Override
  public boolean hasNext() {
    return inputIterator.hasNext();
  }

  @Override
  public Token next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No more tokens to parse");
    }
    LexingResult result = lexer.lex();
    if (result instanceof SuccessfulResult) {
      lastToken = currentToken;
      currentToken = ((SuccessfulResult) result).token();
      return ((SuccessfulResult) result).token();
    } else {
      throw new RuntimeException(((UnsuccessfulResult) result).message());
    }
  }

  @Override
  public Token current() {
    return currentToken;
  }

  @Override
  public Token last() {
    return lastToken;
  }
}
