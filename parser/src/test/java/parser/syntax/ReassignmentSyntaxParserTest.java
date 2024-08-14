package parser.syntax;

import AST.nodes.ASTNode;
import org.junit.jupiter.api.Test;
import parser.Parser;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReassignmentSyntaxParserTest {
    @Test
    public void testReassignmentSyntaxParser() {
        //GIVEN
        // let name: string = "John";
        // name = "Doe";
        List<Token> tokens = List.of(
                new ValueToken(TokenType.LET_KEYWORD, "let", 0, 0),
                new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
                new ValueToken(TokenType.COLON, ":", 8, 0),
                new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                new ValueToken(TokenType.ASSIGN, "=", 17, 0),
                new ValueToken(TokenType.STRING, "John", 19, 0),
                new ValueToken(TokenType.SEMICOLON, ";", 25, 0),
                new ValueToken(TokenType.IDENTIFIER, "name", 27, 0),
                new ValueToken(TokenType.ASSIGN, "=", 32, 0),
                new ValueToken(TokenType.STRING, "Doe", 34, 0),
                new ValueToken(TokenType.SEMICOLON, ";", 38, 0)
        );

        // WHEN
        Parser parser = new Parser();
        List<ASTNode> astNodes = parser.parse(tokens);
        // THEN
        assertEquals(2, astNodes.size());
    }
}