package iterator;

import java.util.NoSuchElementException;
import lexer.Lexer;
import providers.iterator.PrintScriptIterator;
import result.LexingResult;
import result.SuccessfulResult;
import result.UnsuccessfulResult;
import token.Token;

public class TokenIterator implements PrintScriptIterator<Token> {
  private final Lexer lexer;
  private final FileReaderIterator inputIterator;
  private final Token currentToken;
  private final Token lastToken;

  public TokenIterator(FileReaderIterator inputIterator, String version) {
    this.inputIterator = inputIterator;
    this.lexer = new Lexer(inputIterator, version);
    this.lastToken = null;
    this.currentToken = inputIterator.hasNext() ? this.next().current() : null;
  }

  private TokenIterator(FileReaderIterator inputIterator, Lexer lexer, Token lastToken, Token currentToken) {
    this.inputIterator = inputIterator;
    this.lexer = lexer;
    this.lastToken = lastToken;
    this.currentToken = currentToken;
  }

  @Override
  public boolean hasNext() {
    return inputIterator.hasNext();
  }

  @Override
  public PrintScriptIterator<Token> next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No more tokens to parse");
    }
    LexingResult result = lexer.lex();
    if (result instanceof SuccessfulResult) {
      Token newLastToken = currentToken;
      Token newCurrentToken = ((SuccessfulResult) result).token();
      return new TokenIterator(inputIterator, lexer, newLastToken, newCurrentToken);  // Return new iterator
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