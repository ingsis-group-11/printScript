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


    Iterator<Token> tokenIterator = tokens.iterator();

    // WHEN
    Parser parser = new Parser();
    ASTNode astNode = parser.parse(tokenIterator);
  }

  @Test
  public void testOperationParsing() {
    List<Token> tokens =
        // let num: number = 20+10;
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 1, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 4, 1),
            new ValueToken(TokenType.IDENTIFIER, "num", 5, 1),
            new ValueToken(TokenType.COLON, ":", 8, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 9, 1),
            new ValueToken(TokenType.STRING_TYPE, "number", 10, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 16, 1),
            new ValueToken(TokenType.ASSIGN, "=", 17, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 18, 1),
            new ValueToken(TokenType.STRING, "20", 19, 1),
            new ValueToken(TokenType.OPERATOR, "+", 21, 1),
            new ValueToken(TokenType.STRING, "10", 22, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 24, 1));

    Parser parser = new Parser();
    Iterator<Token> tokenIterator = tokens.iterator();
    ASTNode astNode = parser.parse(tokenIterator);
  }
}
