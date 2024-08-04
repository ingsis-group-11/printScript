package parser.semantic;

import AST.nodes.ASTNode;
import org.junit.jupiter.api.Test;
import parser.syntax.AssignationSyntaxParser;
import parser.syntax.SyntaxParser;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SemanticAnalyzerTest {
    @Test
    public void testOneSemanticError() {
        //GIVEN
        List<Token> tokens = List.of(
                new ValueToken(TokenType.LET_KEYWORD, "let", 1, 1),
                new ValueToken(TokenType.IDENTIFIER, "name", 5, 1),
                new ValueToken(TokenType.STRING_TYPE, "string", 11, 1),
                new ValueToken(TokenType.ASSIGN, "=", 18, 1),
                new ValueToken(TokenType.NUMBER, "2", 20, 1),
                new ValueToken(TokenType.SEMICOLON, ";", 27, 1)
        );

        // WHEN
        SyntaxParser parser = new AssignationSyntaxParser();
        ASTNode ast = parser.syntaxParse(tokens);

        // THEN
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(List.of(ast));
        SemanticResult result = semanticAnalyzer.analyze();
        assertFalse(result.getResult());
    }

    @Test
    public void testOneSemanticValid() {
        //GIVEN
        List<Token> tokens = List.of(
                new ValueToken(TokenType.LET_KEYWORD, "let", 1, 1),
                new ValueToken(TokenType.IDENTIFIER, "name", 5, 1),
                new ValueToken(TokenType.STRING_TYPE, "string", 11, 1),
                new ValueToken(TokenType.ASSIGN, "=", 18, 1),
                new ValueToken(TokenType.STRING, "'Tomas'", 20, 1),
                new ValueToken(TokenType.SEMICOLON, ";", 27, 1)
        );

        // WHEN
        SyntaxParser parser = new AssignationSyntaxParser();
        ASTNode ast = parser.syntaxParse(tokens);

        // THEN
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(List.of(ast));
        SemanticResult result = semanticAnalyzer.analyze();
        assertTrue(result.getResult());
    }
}
