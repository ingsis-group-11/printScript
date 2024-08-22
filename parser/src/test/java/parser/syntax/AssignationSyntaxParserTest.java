package parser.syntax;

import static org.junit.jupiter.api.Assertions.*;

import AST.nodes.*;
import java.util.List;
import org.junit.jupiter.api.Test;
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
    SyntaxParser parser = new AssignationSyntaxParser();
    ASTNode ast = parser.syntaxParse(tokens);

    // THEN
    assertInstanceOf(AssignationNode.class, ast);
    AssignationNode assignationNode = (AssignationNode) ast;

    DeclarationNode declarationNode = assignationNode.getDeclaration();
    assertInstanceOf(DeclarationNode.class, declarationNode);
    assertEquals("name", (declarationNode.getNameToken().getValue()));
    assertEquals("string", (declarationNode.getTypeToken().getValue()));

    ASTNode literalNode = assignationNode.getExpression();
    assertInstanceOf(LiteralNode.class, literalNode);
    LiteralNode litNode = (LiteralNode) literalNode;
    assertEquals("John", (litNode.getValue()));
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
    SyntaxParser parser = new AssignationSyntaxParser();
    ASTNode ast = parser.syntaxParse(tokens);

    // THEN
    assertInstanceOf(AssignationNode.class, ast);
    AssignationNode assignationNode = (AssignationNode) ast;

    DeclarationNode declarationNode = assignationNode.getDeclaration();
    assertInstanceOf(DeclarationNode.class, declarationNode);
    assertEquals("age", (declarationNode.getNameToken().getValue()));
    assertEquals("number", (declarationNode.getTypeToken().getValue()));

    ASTNode literalNode = assignationNode.getExpression();
    assertInstanceOf(LiteralNode.class, literalNode);
    LiteralNode litNode = (LiteralNode) literalNode;
    assertEquals("25", (litNode.getValue()));
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

    SyntaxParser parser = new AssignationSyntaxParser();
    ASTNode ast = parser.syntaxParse(tokens);

    assertInstanceOf(AssignationNode.class, ast);
    AssignationNode assignationNode = (AssignationNode) ast;

    DeclarationNode declarationNode = assignationNode.getDeclaration();
    assertInstanceOf(DeclarationNode.class, declarationNode);
    assertEquals("name", (declarationNode.getNameToken().getValue()));
    assertEquals("string", (declarationNode.getTypeToken().getValue()));

    ASTNode operatorNode = assignationNode.getExpression();
    assertInstanceOf(OperatorNode.class, operatorNode);
    OperatorNode opNode = (OperatorNode) operatorNode;
    assertEquals("+", (opNode.getOperator()));
  }
}
