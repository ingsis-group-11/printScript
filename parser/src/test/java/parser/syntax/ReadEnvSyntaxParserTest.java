package parser.syntax;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ast.nodes.AstNode;
import ast.nodes.ReadEnvNode;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import parser.iterator.AstIterator;
import parser.iterator.TestTokenIterator;
import token.Token;
import token.TokenType;
import token.ValueToken;

public class ReadEnvSyntaxParserTest {
  @Test
  public void testValidSyntaxParseReadInput() {
    // readEnv("TEST");
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.READ_ENV, "readEnv", 1, 1),
            new ValueToken(TokenType.PARENTHESIS_OPEN, "(", 8, 1),
            new ValueToken(TokenType.STRING, "TEST", 9, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<AstNode> nodes = new AstIterator(tokenIterator, "1.1");
    AstNode firstAst = nodes.next();
    assertInstanceOf(ReadEnvNode.class, firstAst);
  }

  @Test
  public void testInvalidSyntaxParseReadInput() {
    // readEnv"TEST");
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.READ_ENV, "readEnv", 1, 1),
            new ValueToken(TokenType.STRING, "TEST", 9, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Iterator<AstNode> nodes = new AstIterator(tokenIterator, "1.1");
    assertThrows(RuntimeException.class, () -> nodes.next());
  }
}
