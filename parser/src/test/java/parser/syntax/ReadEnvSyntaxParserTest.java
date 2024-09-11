package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.ReadEnvNode;
import AST.nodes.ReadInputNode;
import org.junit.jupiter.api.Test;
import parser.iterator.ASTIterator;
import parser.iterator.TestTokenIterator;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator,"1.1");
    ASTNode firstAST = nodes.next();
    assertInstanceOf(ReadEnvNode.class, firstAST);
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
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator,"1.1");
    assertThrows(RuntimeException.class, () -> nodes.next());
  }
}
