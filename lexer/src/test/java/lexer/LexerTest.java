package lexer;

import java.io.IOException;
import java.util.List;

import fileReader.FileReader;
import org.junit.jupiter.api.Test;
import result.LexingResult;
import result.SuccessfulResult;
import result.UnsuccessfulResult;
import token.Token;
import token.TokenType;
import token.ValueToken;

import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {

  @Test
  public void tokenizeOneOperationTest() throws IOException {
    String filePath = "src/test/resources/operation.txt";
    String file = new FileReader().readFile(filePath);
    Lexer lexer = new Lexer();

    LexingResult tokensResult = lexer.lex(file);

    if (tokensResult instanceof SuccessfulResult) {
      List<Token> tokens = ((SuccessfulResult) tokensResult).tokens();
      assertEquals(6, tokens.size());
      assertEquals("5", tokens.get(0).getValue());
      assertEquals(" ", tokens.get(1).getValue());
      assertEquals("+", tokens.get(2).getValue());
      assertEquals(" ", tokens.get(3).getValue());
      assertEquals("3", tokens.get(4).getValue());
      assertEquals(";", tokens.get(5).getValue());
    }
    else {
      fail();
    }
  }

  @Test
  public void tokenizeMultipleOperationsTest() throws IOException {
    // 5+3;
    // 10/2;
    String filePath = "src/test/resources/multiple_operations.txt";
    String file = new FileReader().readFile(filePath);
    Lexer lexer = new Lexer();
    LexingResult tokensResult = lexer.lex(file);

    if (tokensResult instanceof SuccessfulResult) {

      List<Token> tokens = ((SuccessfulResult) tokensResult).tokens();

      assertEquals(9, tokens.size());
      assertEquals("5", tokens.get(0).getValue());
      assertEquals("+", tokens.get(1).getValue());
      assertEquals("3", tokens.get(2).getValue());
      assertEquals(";", tokens.get(3).getValue());
      assertEquals("\n", tokens.get(4).getValue());
      assertEquals("10", tokens.get(5).getValue());
      assertEquals("/", tokens.get(6).getValue());
      assertEquals("2", tokens.get(7).getValue());
      assertEquals(";", tokens.get(8).getValue());
    }
    else {
      fail();
    }
  }

  @Test
  public void tokenizeVariableAssignationTest() throws IOException {
    //let name: string = "John";
    String filePath = "src/test/resources/variable_assignation.txt";
    String file = new FileReader().readFile(filePath);
    Lexer lexer = new Lexer();

    LexingResult tokensResult = lexer.lex(file);

    if (tokensResult instanceof SuccessfulResult) {
      List<Token> tokens = ((SuccessfulResult) tokensResult).tokens();

      assertEquals(11, tokens.size());
      assertEquals(TokenType.LET_KEYWORD, tokens.get(0).getType());
      assertEquals(TokenType.WHITESPACE, tokens.get(1).getType());
      assertEquals("name", tokens.get(2).getValue());
      assertEquals(TokenType.IDENTIFIER, tokens.get(2).getType());
      assertEquals(TokenType.COLON, tokens.get(3).getType());
      assertEquals(TokenType.WHITESPACE, tokens.get(4).getType());
      assertEquals(TokenType.STRING_TYPE, tokens.get(5).getType());
      assertEquals(TokenType.WHITESPACE, tokens.get(6).getType());
      assertEquals("=", tokens.get(7).getValue());
      assertEquals(TokenType.WHITESPACE, tokens.get(8).getType());
      assertEquals("John", tokens.get(9).getValue());
      assertEquals(TokenType.STRING, tokens.get(9).getType());
      assertEquals(";", tokens.get(10).getValue());
    }
    else {
      fail();
    }
  }

  // TODO: Fix this test

  /* @Test
  public void tokenizeCompleteTest() throws IOException {
    String filePath = "src/test/resources/complete.txt";
    String file = new FileReader().readFile(filePath);
    Lexer lexer = new Lexer();

    LexingResult result = lexer.lex(file);

    if (result instanceof SuccessfulResult) {
      List<Token> tokens = ((SuccessfulResult) result).tokens();

      assertEquals(new ValueToken(TokenType.LET_KEYWORD, "let", 1, 1), tokens.get(0));
      assertEquals(new ValueToken(TokenType.IDENTIFIER, "a", 5, 1), tokens.get(1));
      assertEquals(
          new ValueToken(TokenType.PRINT_KEYWORD, "println", 2, 4),
          tokens.get(20)); // TODO: Fix column number error (expected is 1)
    }
    else{
      fail();
    }
  }*/
}
