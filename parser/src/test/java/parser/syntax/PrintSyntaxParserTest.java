package parser.syntax;

import AST.nodes.*;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.iterator.ASTIterator;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrintSyntaxParserTest {

  @Test
  public void testSyntaxParsePrint() {
    // println("Hello");
    List<Token> tokens =
            List.of(
                    new ValueToken(TokenType.PRINT_KEYWORD, "println", 1, 1),
                    new ValueToken(TokenType.PARENTHESIS_OPEN, "(", 8, 1),
                    new ValueToken(TokenType.STRING, "Hello", 9, 1),
                    new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
                    new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    Iterator<Token> tokenIterator = tokens.iterator();
    Iterator<ASTNode> nodes = new ASTIterator(new Parser(), tokenIterator);
    ASTNode firstAST = nodes.next();
    assertInstanceOf(PrintNode.class, firstAST);
  }

  @Test
  public void testSyntaxParsePrintError() {
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
    assertThrows(RuntimeException.class, () -> parser.parse(tokenIterator));
  }
}
