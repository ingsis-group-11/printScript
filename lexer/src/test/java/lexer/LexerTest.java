package lexer;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import fileReader.FileReaderIterator;
import iterator.TokenIterator;
import org.junit.jupiter.api.Test;
import token.Token;
import token.TokenType;

import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {

  @Test
  public void tokenizeOneOperationTest() throws IOException {
    // 5 + 3;
    String filePath = "src/test/resources/operation.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokenIterator = new TokenIterator(fileReaderIterator, new Lexer());

    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.OPERATOR);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertFalse(tokenIterator.hasNext());
  }

}