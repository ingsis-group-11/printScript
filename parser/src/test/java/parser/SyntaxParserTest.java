package parser;

import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import AST.nodes.LiteralNode;
import org.junit.jupiter.api.Test;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SyntaxParserTest {

    @Test
    public void testSyntaxParse() {
        //GIVEN
        List<Token> tokens = List.of(
                new ValueToken(TokenType.LET_KEYWORD, "let"),
                new ValueToken(TokenType.IDENTIFIER, "name"),
                new ValueToken(TokenType.STRING_TYPE, "string"),
                new ValueToken(TokenType.ASSIGN, "="),
                new ValueToken(TokenType.STRING, "\"Olive\""),
                new ValueToken(TokenType.SEMICOLON, ";")
        );

        // WHEN
        SyntaxParser parser = new SyntaxParser();
        ASTNode ast = parser.syntaxParse(tokens);

        // THEN
        assertInstanceOf(AssignationNode.class, ast);
        AssignationNode assignationNode = (AssignationNode) ast;

        ASTNode declarationNode = assignationNode.getDeclaration();
        assertInstanceOf(DeclarationNode.class, declarationNode);
        DeclarationNode declNode = (DeclarationNode) declarationNode;
        assertEquals("name", ((ValueToken) declNode.getName()).getValue());
        assertEquals("string", ((ValueToken) declNode.getType()).getValue());

        ASTNode literalNode = assignationNode.getExpression();
        assertInstanceOf(LiteralNode.class, literalNode);
        LiteralNode litNode = (LiteralNode) literalNode;
        assertEquals("\"Olive\"", ((ValueToken) litNode.getValue()).getValue());
    }
}