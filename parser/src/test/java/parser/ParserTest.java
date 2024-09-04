package parser;

import static org.junit.jupiter.api.Assertions.*;

import AST.nodes.*;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import parser.iterator.ASTIterator;
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
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator);
    ASTNode firstAST = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAST);
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
            new ValueToken(TokenType.NUMBER_TYPE, "number", 11, 1),
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
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator);
    ASTNode firstAST = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAST);
  }

  @Test
  public void testInvalidPrint() throws RuntimeException {
    // println"Hello");
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.PRINT_KEYWORD, "println", 1, 1),
            new ValueToken(TokenType.STRING, "Hello", 9, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    Iterator<Token> tokenIterator = tokens.iterator();
    Parser parser = new Parser();
    RuntimeException exception = assertThrows(RuntimeException.class, () -> parser.parse(tokenIterator));
    assertEquals("Syntax errors:\n" +
        "Expected '(' at column 9 line 1\n", exception.getMessage());
  }

  @Test
  public void testSyntaxParse() {
    // GIVEN
    // let name: string;
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 0, 0),
            new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
            new ValueToken(TokenType.COLON, ":", 8, 0),
            new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
            new ValueToken(TokenType.SEMICOLON, ";", 17, 0));

    // WHEN
    Iterator<Token> tokenIterator = tokens.iterator();
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator);
    ASTNode firstAST = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAST);
  }

  @Test
  public void testSyntaxParseResignationPrint() {
    // GIVEN
    // let name: string;
    // name = "John"
    // println(name);
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 0, 0),
            new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
            new ValueToken(TokenType.COLON, ":", 8, 0),
            new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
            new ValueToken(TokenType.SEMICOLON, ";", 17, 0),
            new ValueToken(TokenType.LINE_BREAK, "\n", 17, 0),
            new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
            new ValueToken(TokenType.ASSIGN, "=", 8, 0),
            new ValueToken(TokenType.STRING, "John", 10, 0),
            new ValueToken(TokenType.SEMICOLON, ";", 17, 0),
            new ValueToken(TokenType.LINE_BREAK, "\n", 17, 0),
            new ValueToken(TokenType.PRINT_KEYWORD, "println", 1, 1),
            new ValueToken(TokenType.PARENTHESIS_OPEN, "(", 8, 1),
            new ValueToken(TokenType.IDENTIFIER, "name", 9, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 14, 1)
            );


    // WHEN
    Iterator<Token> tokenIterator = tokens.iterator();
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator);
    ASTNode firstAST = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAST);
    ASTNode secondAST = nodes.next();
    assertInstanceOf(ReassignmentNode.class, secondAST);


  }

}
