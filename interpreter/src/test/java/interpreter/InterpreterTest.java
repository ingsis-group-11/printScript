package interpreter;

import AST.nodes.*;
import org.junit.jupiter.api.Test;
import token.TokenType;
import token.ValueToken;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InterpreterTest {

    @Test
    public void testInterpreter() {
        //GIVEN
        // let name: string = "Olive";
        // println(name);
        List<ASTNode> astNodes = List.of(
                new AssignationNode(
                        new DeclarationNode(new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                                new ValueToken(TokenType.IDENTIFIER, "name", 4, 0), 1, 0)
                        , new LiteralNode(new ValueToken(TokenType.STRING, "Olive", 19, 0)),
                        1,1
                ), new PrintNode(new VariableNode(new ValueToken(TokenType.IDENTIFIER, "name", 8, 1)), 1, 1)
        );

        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // WHEN
            Interpreter interpreter = new Interpreter();
            interpreter.interpret(astNodes);

            // THEN
            String output = outputStream.toString().trim();
            assertEquals("Olive", output);
        } finally {
            // Restore original System.out
            System.setOut(originalOut);
        }

    }

    @Test
    public void testCreationOfTheSameVariable() {
        //GIVEN
        // let name: string = "a";
        // let name: string = "a";
        List<ASTNode> astNodes = List.of(
                new AssignationNode(
                        new DeclarationNode(new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                                new ValueToken(TokenType.IDENTIFIER, "name", 4, 0), 0, 0)
                        , new LiteralNode(new ValueToken(TokenType.STRING, "a", 19, 0)),
                        1,1
                ), new AssignationNode(
                        new DeclarationNode(new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                                new ValueToken(TokenType.IDENTIFIER, "name", 4, 0), 1, 0)
                        , new LiteralNode(new ValueToken(TokenType.STRING, "a", 19, 0)),
                        1,1
                )
        );

        // WHEN & THEN
        Interpreter interpreter = new Interpreter();
        assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes));
    }
}