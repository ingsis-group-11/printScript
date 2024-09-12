package parser.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import ast.nodes.AssignationNode;
import ast.nodes.AstNode;
import ast.nodes.LiteralNode;
import ast.nodes.OperatorNode;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import parser.iterator.AstIterator;
import parser.iterator.TestTokenIterator;
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
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<AstNode> nodes = new AstIterator(tokenIterator, "1.0");
    AstNode firstAst = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAst);
    assertEquals("John", ((LiteralNode) ((AssignationNode) firstAst).getExpression()).getValue());
  }

  @Test
  public void testSyntaxParseNumber() {
    // GIVEN
    // let age: number = "25";
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 0, 0),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.IDENTIFIER, "age", 4, 0),
            new ValueToken(TokenType.COLON, ":", 7, 0),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.STRING_TYPE, "number", 9, 0),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.ASSIGN, "=", 16, 0),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.STRING, "25", 18, 0),
            new ValueToken(TokenType.SEMICOLON, ";", 22, 0));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<AstNode> nodes = new AstIterator(tokenIterator, "1.0");
    AstNode firstAst = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAst);
    assertEquals("25", ((LiteralNode) ((AssignationNode) firstAst).getExpression()).getValue());
  }

  @Test
  public void testSyntaxParseOperation() {
    // let name: string = "Olive" + "hello" + "bye";
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 1, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.IDENTIFIER, "name", 5, 1),
            new ValueToken(TokenType.COLON, ":", 8, 0),
            new ValueToken(TokenType.STRING_TYPE, "string", 11, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.ASSIGN, "=", 18, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.STRING, "Olive", 20, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.OPERATOR, "+", 27, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.STRING, "hello", 29, 1),
            new ValueToken(TokenType.OPERATOR, "+", 27, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.STRING, "bye", 29, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 27, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<AstNode> nodes = new AstIterator(tokenIterator, "1.0");
    AstNode firstAst = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAst);
    assertEquals("+", ((OperatorNode) ((AssignationNode) firstAst).getExpression()).getOperator());
    assertEquals(
        "bye",
        ((LiteralNode) ((OperatorNode) ((AssignationNode) firstAst).getExpression()).getRightNode())
            .getValue());
  }

  @Test
  public void testConstDeclaration() {

    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.CONST_KEYWORD, "const", 1, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.IDENTIFIER, "name", 5, 1),
            new ValueToken(TokenType.COLON, ":", 8, 0),
            new ValueToken(TokenType.STRING_TYPE, "string", 11, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.ASSIGN, "=", 18, 1),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.STRING, "Olive", 20, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 22, 0));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<AstNode> nodes = new AstIterator(tokenIterator, "1.1");
    AstNode firstAst = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAst);
    assertEquals("Olive", ((LiteralNode) ((AssignationNode) firstAst).getExpression()).getValue());
  }
}
