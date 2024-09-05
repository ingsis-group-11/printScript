package parser.syntax;

import AST.nodes.*;
import org.junit.jupiter.api.Test;
import parser.iterator.ASTIterator;
import token.Token;
import token.TokenType;
import token.ValueToken;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    Iterator<Token> tokenIterator = tokens.iterator();
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator);
    ASTNode firstAST = nodes.next();
    assertInstanceOf(ReadInputNode.class, firstAST);
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
    Iterator<Token> tokenIterator = tokens.iterator();
    Iterator<ASTNode> nodes = new ASTIterator(tokenIterator);
    assertThrows(RuntimeException.class, () -> nodes.next());
  }
}