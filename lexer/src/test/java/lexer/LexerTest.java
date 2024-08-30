package lexer;

import java.io.IOException;

import fileReader.StringReader;
import org.junit.jupiter.api.Test;
import result.LexingResult;
import result.SuccessfulResult;
import token.Token;
import token.TokenType;

import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {

  @Test
  public void tokenizeOneOperationTest() throws IOException {
    String input = "5 + 3;";
    StringReader stringReader = new StringReader(input);
    Lexer lexer = new Lexer();

    SuccessfulResult tokenResult = (SuccessfulResult) lexer.lex(stringReader);
    assertEquals("5", tokenResult.token().getValue());

    tokenResult = (SuccessfulResult) lexer.lex(stringReader);
    assertEquals(" ", tokenResult.token().getValue());

    tokenResult = (SuccessfulResult) lexer.lex(stringReader);
    assertEquals("+", tokenResult.token().getValue());

    tokenResult = (SuccessfulResult) lexer.lex(stringReader);
    assertEquals(" ", tokenResult.token().getValue());

    tokenResult = (SuccessfulResult) lexer.lex(stringReader);
    assertEquals("3", tokenResult.token().getValue());

    tokenResult = (SuccessfulResult) lexer.lex(stringReader);
    assertEquals(";", tokenResult.token().getValue());
  }

}