package parser.syntax;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import ast.nodes.AstNode;
import ast.nodes.IfNode;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import parser.iterator.AstIterator;
import parser.iterator.TestTokenIterator;
import token.Token;
import token.TokenType;
import token.ValueToken;

public class IfSyntaxParserTest {

  @Test
  public void testIfSyntaxParser() {
    // if (true) {
    //   println("Hello, World!");
    // }
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.IF_KEYWORD, "if", 0, 0),
            new ValueToken(TokenType.WHITESPACE, " ", 4, 0),
            new ValueToken(TokenType.PARENTHESIS_OPEN, "(", 8, 0),
            new ValueToken(TokenType.BOOLEAN, "true", 10, 0),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 17, 0),
            new ValueToken(TokenType.WHITESPACE, " ", 19, 0),
            new ValueToken(TokenType.BRACE_OPEN, ";", 25, 0),
            new ValueToken(TokenType.LINE_BREAK, "\n", 26, 0),
            new ValueToken(TokenType.PRINT_KEYWORD, "println", 0, 1),
            new ValueToken(TokenType.PARENTHESIS_OPEN, "(", 7, 1),
            new ValueToken(TokenType.STRING, "Hello, World!", 9, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 24, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 25, 1),
            new ValueToken(TokenType.LINE_BREAK, "\n", 26, 1),
            new ValueToken(TokenType.BRACE_CLOSE, "}", 0, 2));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<AstNode> nodes = new AstIterator(tokenIterator, "1.1");
    AstNode firstAst = nodes.next();
    assertInstanceOf(IfNode.class, firstAst);
  }
}
