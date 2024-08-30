package parser.syntax;

import static org.junit.jupiter.api.Assertions.*;

import AST.nodes.*;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.Token;
import token.TokenType;
import token.ValueToken;

public class AssignationSyntaxParserTest {

  @Test
  public void testSyntaxParseString() {
    // GIVEN
    // let name: string = "John";
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 0, 0),
            new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
            new ValueToken(TokenType.COLON, ":", 8, 0),
            new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
            new ValueToken(TokenType.ASSIGN, "=", 17, 0),
            new ValueToken(TokenType.STRING, "John", 19, 0),
            new ValueToken(TokenType.SEMICOLON, ";", 25, 0));

    // WHEN
    Iterator<Token> tokenIterator = tokens.iterator();
    AssignationSyntaxParser parser = new AssignationSyntaxParser();
    SyntaxResult astNode = parser.syntaxParse(tokenIterator);
  }

  @Test
  public void testSyntaxParseNumber() {
    // GIVEN
    // let age: number = "25";
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 0, 0),
            new ValueToken(TokenType.IDENTIFIER, "age", 4, 0),
            new ValueToken(TokenType.COLON, ":", 7, 0),
            new ValueToken(TokenType.STRING_TYPE, "number", 9, 0),
            new ValueToken(TokenType.ASSIGN, "=", 16, 0),
            new ValueToken(TokenType.STRING, "25", 18, 0),
            new ValueToken(TokenType.SEMICOLON, ";", 22, 0));

    // WHEN
    Iterator<Token> tokenIterator = tokens.iterator();
    AssignationSyntaxParser parser = new AssignationSyntaxParser();
    SyntaxResult astNode = parser.syntaxParse(tokenIterator);
  }

  @Test
  public void testSyntaxParseOperation() {
    // let name: string = "Olive" + "hello" + "bye";
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 1, 1),
            new ValueToken(TokenType.IDENTIFIER, "name", 5, 1),
            new ValueToken(TokenType.COLON, ":", 8, 0),
            new ValueToken(TokenType.STRING_TYPE, "string", 11, 1),
            new ValueToken(TokenType.ASSIGN, "=", 18, 1),
            new ValueToken(TokenType.STRING, "Olive", 20, 1),
            new ValueToken(TokenType.OPERATOR, "+", 27, 1),
            new ValueToken(TokenType.STRING, "hello", 29, 1),
            new ValueToken(TokenType.OPERATOR, "+", 27, 1),
            new ValueToken(TokenType.STRING, "bye", 29, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 27, 1));

    // WHEN
    Iterator<Token> tokenIterator = tokens.iterator();
    AssignationSyntaxParser parser = new AssignationSyntaxParser();
    SyntaxResult astNode = parser.syntaxParse(tokenIterator);
  }
}
