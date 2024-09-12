package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import AST.ExpressionTypeVisitor;
import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import AST.nodes.PrintNode;
import AST.nodes.ReadEnvNode;
import AST.nodes.ReassignmentNode;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import parser.iterator.ASTIterator;
import parser.iterator.TestTokenIterator;
import token.Token;
import token.TokenType;
import token.ValueToken;

public class ParserTest {

  @Test
  public void testParser() {
    // GIVEN
    // let name: string = "Olive";
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
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator, "1.0");
    ASTNode firstAst = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAst);
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

    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator, "1.0");
    ASTNode firstAst = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAst);
  }

  @Test
  public void testInvalidPrint() throws RuntimeException {
    // println"Hello");
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.PRINT_KEYWORD, "println", 1, 1),
            new ValueToken(TokenType.STRING, "Hello", 8, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Parser parser = new Parser("1.0");
    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> parser.parse(tokenIterator));
    assertEquals("Expected '(' at column 8 line 1", exception.getMessage());
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
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator, "1.0");
    ASTNode firstAst = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAst);
  }

  @Test
  public void testSyntaxParseResignationStringPrint() {
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
            new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator, "1.0");
    ASTNode firstAst = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAst);
    ASTNode secondAst = nodes.next();
    assertInstanceOf(ReassignmentNode.class, secondAst);
  }

  @Test
  public void parseBooleanAssignation() {
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 1, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 4, 1),
            new ValueToken(TokenType.IDENTIFIER, "bool", 5, 1),
            new ValueToken(TokenType.COLON, ":", 9, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 10, 1),
            new ValueToken(TokenType.BOOLEAN_TYPE, "boolean", 11, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.ASSIGN, "=", 18, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 19, 1),
            new ValueToken(TokenType.BOOLEAN, "true", 20, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 27, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator, "1.0");
    ASTNode firstAst = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAst);
    assertEquals(TokenType.BOOLEAN, firstAst.accept(new ExpressionTypeVisitor()));
  }

  @Test
  public void testSyntaxParseResignationBooleanPrint() {
    // GIVEN
    // let bool: boolean;
    // bool = true;
    // println(bool);
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 0, 0),
            new ValueToken(TokenType.IDENTIFIER, "bool", 4, 0),
            new ValueToken(TokenType.COLON, ":", 8, 0),
            new ValueToken(TokenType.BOOLEAN_TYPE, "boolean", 10, 0),
            new ValueToken(TokenType.SEMICOLON, ";", 17, 0),
            new ValueToken(TokenType.LINE_BREAK, "\n", 18, 0),
            new ValueToken(TokenType.IDENTIFIER, "bool", 4, 0),
            new ValueToken(TokenType.ASSIGN, "=", 8, 0),
            new ValueToken(TokenType.BOOLEAN, "true", 10, 0),
            new ValueToken(TokenType.SEMICOLON, ";", 17, 0),
            new ValueToken(TokenType.LINE_BREAK, "\n", 17, 0),
            new ValueToken(TokenType.PRINT_KEYWORD, "println", 1, 1),
            new ValueToken(TokenType.PARENTHESIS_OPEN, "(", 8, 1),
            new ValueToken(TokenType.IDENTIFIER, "bool", 9, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator, "1.1");
    ASTNode firstAst = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAst);
    ASTNode secondAst = nodes.next();
    assertInstanceOf(ReassignmentNode.class, secondAst);
    assertEquals(TokenType.BOOLEAN, secondAst.accept(new ExpressionTypeVisitor()));
    ASTNode thirdAst = nodes.next();
    assertInstanceOf(PrintNode.class, thirdAst);
  }

  @Test
  public void testReadInputParser() {
    // GIVEN
    // let name: string = readInput("Enter your name: ");
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
            new ValueToken(TokenType.READ_INPUT, "readInput", 20, 1),
            new ValueToken(TokenType.PARENTHESIS_OPEN, "(", 29, 1),
            new ValueToken(TokenType.STRING, "Enter your name: ", 30, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 47, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 48, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator, "1.1");
    ASTNode firstAst = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAst);
  }

  @Test
  public void testReadEnvParser() {
    // GIVEN
    // let name: string = readEnv("TEST");
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
            new ValueToken(TokenType.READ_ENV, "readEnv", 20, 1),
            new ValueToken(TokenType.PARENTHESIS_OPEN, "(", 29, 1),
            new ValueToken(TokenType.STRING, "TEST", 30, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 47, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 48, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator, "1.1");
    ASTNode firstAst = nodes.next();
    if (firstAst instanceof AssignationNode assignationNode) {
      assertInstanceOf(ReadEnvNode.class, assignationNode.getExpression());
    } else {
      fail();
    }
  }
}
