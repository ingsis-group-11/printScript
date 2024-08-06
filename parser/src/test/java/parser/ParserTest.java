package parser;

import AST.nodes.*;
import org.junit.jupiter.api.Test;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParser() {
        //GIVEN
        // let name: string = "Olive";
        // print(name)
        List<Token> tokens = List.of(
                new ValueToken(TokenType.LET_KEYWORD, "let", 1, 1),
                new ValueToken(TokenType.IDENTIFIER, "name", 5, 1),
                new ValueToken(TokenType.STRING_TYPE, "string", 11, 1),
                new ValueToken(TokenType.ASSIGN, "=", 18, 1),
                new ValueToken(TokenType.STRING, "\"Olive\"", 20, 1),
                new ValueToken(TokenType.SEMICOLON, ";", 27, 1),
                new ValueToken(TokenType.PRINT_KEYWORD, "print", 2, 1),
                new ValueToken(TokenType.IDENTIFIER, "name", 2, 7),
                new ValueToken(TokenType.SEMICOLON, ";", 2, 11)
        );

        // WHEN
        Parser parser = new Parser();
        List<ASTNode> astNodes = parser.parse(tokens);

        // THEN
        assertEquals(2, astNodes.size());
        AssignationNode assignationNode1 = (AssignationNode) astNodes.getFirst();

        DeclarationNode declarationNode = assignationNode1.getDeclaration();
        assertInstanceOf(DeclarationNode.class, declarationNode);
        assertEquals("name", (declarationNode.getNameToken().getValue()));
        assertEquals("string", (declarationNode.getTypeToken().getValue()));

        ASTNode literalNode = assignationNode1.getExpression();
        assertInstanceOf(LiteralNode.class, literalNode);
        LiteralNode litNode = (LiteralNode) literalNode;
        assertEquals("\"Olive\"", (litNode.getValue()));

        ASTNode printNode = astNodes.get(1);
        assertInstanceOf(PrintNode.class, printNode);
        PrintNode print = (PrintNode) printNode;
        assertEquals("name", ((VariableNode) print.getExpression()).getValue());
    }
}
