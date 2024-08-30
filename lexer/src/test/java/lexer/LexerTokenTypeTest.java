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

public class LexerTokenTypeTest {

  @Test
  public void tokenizeOneOperationTest() throws IOException {
    // 5 + 3;
    String filePath = "src/test/resources/operation.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokenIterator = new TokenIterator(fileReaderIterator, new Lexer(fileReaderIterator));

    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.OPERATOR);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertFalse(tokenIterator.hasNext());
  }

  @Test
  public void tokenizeMultipleOperationTest() throws IOException {
    //5 + 3;
    //10 / 2;
    String filePath = "src/test/resources/multiple_operations.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokenIterator = new TokenIterator(fileReaderIterator, new Lexer(fileReaderIterator));

    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.OPERATOR);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertEquals(tokenIterator.next().getType(), TokenType.LINE_BREAK);
    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.OPERATOR);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertFalse(tokenIterator.hasNext());
  }

  @Test
  public void tokenizeCompleteCode() throws IOException {
    //let a: string = "Hello ";
    //let b: string = "World!";
    //println("Result: "+a+b);

    String filePath = "src/test/resources/complete.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokenIterator = new TokenIterator(fileReaderIterator, new Lexer(fileReaderIterator));
    assertEquals(tokenIterator.next().getType(), TokenType.LET_KEYWORD);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.COLON);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING_TYPE);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.ASSIGN);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertEquals(tokenIterator.next().getType(), TokenType.LINE_BREAK);

    assertEquals(tokenIterator.next().getType(), TokenType.LET_KEYWORD);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.COLON);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING_TYPE);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.ASSIGN);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertEquals(tokenIterator.next().getType(), TokenType.LINE_BREAK);

    assertEquals(tokenIterator.next().getType(), TokenType.PRINT_KEYWORD);
    assertEquals(tokenIterator.next().getType(), TokenType.PARENTHESIS_OPEN);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.OPERATOR);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.OPERATOR);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.PARENTHESIS_CLOSE);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
  }



  @Test
  public void tokenizeReassignmentCompleteCode() throws IOException {
    //let name: string = "John";
    //name = "Doe";

    String filePath = "src/test/resources/reassignment.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokenIterator = new TokenIterator(fileReaderIterator, new Lexer(fileReaderIterator));
    assertEquals(tokenIterator.next().getType(), TokenType.LET_KEYWORD);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.COLON);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING_TYPE);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.ASSIGN);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertEquals(tokenIterator.next().getType(), TokenType.LINE_BREAK);

    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.ASSIGN);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
  }
}