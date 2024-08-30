package parser;

import static org.junit.jupiter.api.Assertions.*;

import AST.nodes.*;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import token.Token;
import token.TokenType;
import token.ValueToken;

public class ParserTest {

  @Test
  public void testParser() {
    // GIVEN
    // let name: string = "Olive";
    // println(name);
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 1, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 4, 1),
            new ValueToken(TokenType.IDENTIFIER, "name", 5, 1),
            new ValueToken(TokenType.COLON, ":", 9, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 10, 1),
            new ValueToken(TokenType.STRING_TYPE, "string", 11, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.ASSIGN, "=", 18, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 19, 1),
            new ValueToken(TokenType.STRING, "Olive", 20, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 27, 1));

    // WHEN
    Iterator<Token> tokenIterator = tokens.iterator();
    Parser parser = new Parser();
    ASTNode astNode = parser.parse(tokenIterator);

  }

  @Test
  public void testOperationParsing() {
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 1, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 4, 1),
            new ValueToken(TokenType.IDENTIFIER, "num", 5, 1),
            new ValueToken(TokenType.COLON, ":", 9, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 10, 1),
            new ValueToken(TokenType.STRING_TYPE, "number", 11, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.ASSIGN, "=", 18, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 19, 1),
            new ValueToken(TokenType.NUMBER, "20", 20, 1),
            new ValueToken(TokenType.OPERATOR, "+", 27, 1),
            new ValueToken(TokenType.NUMBER, "10", 1, 1),
            new ValueToken(TokenType.OPERATOR, "*", 27, 1),
            new ValueToken(TokenType.NUMBER, "5", 1, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 27, 1));

    Iterator<Token> tokenIterator = tokens.iterator();
    Parser parser = new Parser();
    ASTNode astNode = parser.parse(tokenIterator);
  }
}
