package parser.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import AST.nodes.ASTNode;

import java.util.Iterator;
import java.util.List;

import AST.nodes.AssignationNode;
import AST.nodes.ReassignmentNode;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.iterator.ASTIterator;
import token.Token;
import token.TokenType;
import token.ValueToken;

public class ReassignmentSyntaxParserTest {
  @Test
  public void testReassignmentSyntaxParser() {
    // GIVEN
    // let name: string = "John";
    // name = "Doe";
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.LET_KEYWORD, "let", 0, 0),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
            new ValueToken(TokenType.COLON, ":", 8, 0),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.ASSIGN, "=", 17, 0),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.STRING, "John", 19, 0),
            new ValueToken(TokenType.SEMICOLON, ";", 25, 0),
            new ValueToken(TokenType.LINE_BREAK, "\n", 17, 1),
            new ValueToken(TokenType.IDENTIFIER, "name", 27, 0),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.ASSIGN, "=", 32, 0),
            new ValueToken(TokenType.WHITESPACE, " ", 17, 1),
            new ValueToken(TokenType.STRING, "Doe", 34, 0),
            new ValueToken(TokenType.SEMICOLON, ";", 38, 0));

    // WHEN
    Iterator<Token> tokenIterator = tokens.iterator();
    Iterator<ASTNode> nodes = new ASTIterator(new Parser(), tokenIterator);
    ASTNode firstAST = nodes.next();
    assertInstanceOf(AssignationNode.class, firstAST);
    ASTNode secondAST = nodes.next();
    assertInstanceOf(ReassignmentNode.class, secondAST);
  }
}
