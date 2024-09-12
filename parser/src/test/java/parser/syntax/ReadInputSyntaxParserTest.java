package parser.syntax;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import AST.nodes.ASTNode;
import AST.nodes.ReadInputNode;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import parser.iterator.ASTIterator;
import parser.iterator.TestTokenIterator;
import token.Token;
import token.TokenType;
import token.ValueToken;

public class ReadInputSyntaxParserTest {

  @Test
  public void testValidSyntaxParseReadInput() {
    // readInput("Test");
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.READ_INPUT, "readInput", 1, 1),
            new ValueToken(TokenType.PARENTHESIS_OPEN, "(", 8, 1),
            new ValueToken(TokenType.STRING, "Test", 9, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator, "1.1");
    ASTNode firstAst = nodes.next();
    assertInstanceOf(ReadInputNode.class, firstAst);
  }

  @Test
  public void testInvalidSyntaxParseReadInput() {
    // readInput"Hello");
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.READ_INPUT, "readInput", 1, 1),
            new ValueToken(TokenType.STRING, "Hello", 9, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator, "1.1");
    assertThrows(RuntimeException.class, () -> nodes.next());
  }
}
