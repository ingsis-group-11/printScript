package parser;

import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import AST.nodes.LiteralNode;
import org.junit.jupiter.api.Test;
import parser.syntax.AssignationSyntaxParser;
import parser.syntax.SyntaxParser;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssignationSyntaxParserTest {

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
        SyntaxParser parser = new AssignationSyntaxParser();
        ASTNode ast = parser.syntaxParse(tokens);

        // THEN
        assertInstanceOf(AssignationNode.class, ast);
        AssignationNode assignationNode = (AssignationNode) ast;

        DeclarationNode declarationNode = assignationNode.getDeclaration();
        assertInstanceOf(DeclarationNode.class, declarationNode);
        assertEquals("name", (declarationNode.getNameToken().getValue()));
        assertEquals("string", (declarationNode.getTypeToken().getValue()));

        ASTNode literalNode = assignationNode.getExpression();
        assertInstanceOf(LiteralNode.class, literalNode);
        LiteralNode litNode = (LiteralNode) literalNode;
        assertEquals("\"Olive\"", (litNode.getValue()));
    }
}