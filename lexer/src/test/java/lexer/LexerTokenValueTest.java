package lexer;

import fileReader.FileReaderIterator;
import iterator.TokenIterator;
import org.junit.jupiter.api.Test;
import token.Token;
import token.TokenType;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LexerTokenValueTest {

  @Test
  public void tokenizeOneOperationTest() throws IOException {
    //5 + 3;
    String filePath = "src/test/resources/operation.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokenIterator = new TokenIterator(fileReaderIterator, "1.0");

    assertEquals(tokenIterator.next().getValue(), "5");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "+");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "3");
    assertEquals(tokenIterator.next().getValue(), ";");
    assertFalse(tokenIterator.hasNext());
  }

  @Test
  public void tokenizeMultipleOperationTest() throws IOException {
    //5 + 3;
    //10 / 2;
    String filePath = "src/test/resources/multiple_operations.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokenIterator = new TokenIterator(fileReaderIterator, "1.0");

    assertEquals(tokenIterator.next().getValue(), "5");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "+");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "3");
    assertEquals(tokenIterator.next().getValue(), ";");
    assertEquals(tokenIterator.next().getValue(), "\n");
    assertEquals(tokenIterator.next().getValue(), "10");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "/");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "2");
    assertEquals(tokenIterator.next().getValue(), ";");
    assertFalse(tokenIterator.hasNext());
  }

  @Test
  public void tokenizeCompleteCode() throws IOException {
    //let a: string = "Hello ";
    //let b: string = "World!";
    //println("Result: "+a+b);

    String filePath = "src/test/resources/complete.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokenIterator = new TokenIterator(fileReaderIterator, "1.0");
    assertEquals(tokenIterator.next().getValue(), "let");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "a");
    assertEquals(tokenIterator.next().getValue(), ":");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "string");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "=");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "Hello ");
    assertEquals(tokenIterator.next().getValue(), ";");
    assertEquals(tokenIterator.next().getValue(), "\n");

    assertEquals(tokenIterator.next().getValue(), "let");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "b");
    assertEquals(tokenIterator.next().getValue(), ":");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "string");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "=");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "World!");
    assertEquals(tokenIterator.next().getValue(), ";");
    assertEquals(tokenIterator.next().getValue(), "\n");

    assertEquals(tokenIterator.next().getValue(), "println");
    assertEquals(tokenIterator.next().getValue(), "(");
    assertEquals(tokenIterator.next().getValue(), "Result: ");
    assertEquals(tokenIterator.next().getValue(), "+");
    assertEquals(tokenIterator.next().getValue(), "a");
    assertEquals(tokenIterator.next().getValue(), "+");
    assertEquals(tokenIterator.next().getValue(), "b");
    assertEquals(tokenIterator.next().getValue(), ")");
    assertEquals(tokenIterator.next().getValue(), ";");
  }

  @Test
  public void tokenizeBooleanTest() throws IOException {
    //true false
    String filePath = "src/test/resources/boolean.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokenIterator = new TokenIterator(fileReaderIterator, "1.1");

    assertEquals(tokenIterator.next().getValue(), "true");
    assertEquals(tokenIterator.next().getValue(), " ");
    assertEquals(tokenIterator.next().getValue(), "false");
  }

  @Test
  public void tokenizeReadInput() throws IOException {
    //true false
    String filePath = "src/test/resources/readInput.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokenIterator = new TokenIterator(fileReaderIterator, "1.1");

    assertEquals(tokenIterator.next().getValue(), "readInput");
    assertEquals(tokenIterator.next().getValue(), "(");
    assertEquals(tokenIterator.next().getValue(), "Test");
    assertEquals(tokenIterator.next().getValue(), ")");
    assertEquals(tokenIterator.next().getValue(), ";");
  }
}