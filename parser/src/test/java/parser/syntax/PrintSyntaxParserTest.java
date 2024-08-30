package parser.syntax;

import AST.nodes.*;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class PrintSyntaxParserTest {

  @Test
  public void testSyntaxParsePrint() {
    // println("Hello");
    List<Token> tokens =
            List.of(
                    new ValueToken(TokenType.PRINT_KEYWORD, "println", 1, 1),
                    new ValueToken(TokenType.PARENTHESIS_OPEN, "(", 8, 1),
                    new ValueToken(TokenType.STRING, "Hello", 9, 1),
                    new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
                    new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    Iterator<Token> tokenIterator = tokens.iterator();
    PrintSyntaxParser parser = new PrintSyntaxParser();
    SyntaxResult astNode = parser.syntaxParse(tokenIterator);
  }

  @Test
  public void testSyntaxParsePrintError() {
    // println("Hello");
    List<Token> tokens =
            List.of(
                    new ValueToken(TokenType.PRINT_KEYWORD, "println", 1, 1),
                    new ValueToken(TokenType.STRING, "Hello", 9, 1),
                    new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
                    new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    Iterator<Token> tokenIterator = tokens.iterator();
    PrintSyntaxParser parser = new PrintSyntaxParser();
    SyntaxResult astNode = parser.syntaxParse(tokenIterator);
  }
}
