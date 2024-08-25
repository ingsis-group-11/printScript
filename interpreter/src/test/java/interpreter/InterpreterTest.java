package interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import AST.nodes.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.Test;
import token.TokenType;
import token.ValueToken;

public class InterpreterTest {

  @Test
  public void testInterpreter() {
    // GIVEN
    // let name: string = "Olive";
    // println(name);
    List<ASTNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                    new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
                    1,
                    0),
                new LiteralNode(new ValueToken(TokenType.STRING, "Olive", 19, 0)),
                1,
                1),
            new PrintNode(
                new VariableNode(new ValueToken(TokenType.IDENTIFIER, "name", 8, 1)), 1, 1));

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
  public void testOperationAtPrint() {
    // GIVEN
    // let a: number = 5;
    // let b: number = 3;
    // println(a + b);
    List<ASTNode> astNodes = List.of(
        new AssignationNode(
                new DeclarationNode(
                        new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 0),
                        new ValueToken(TokenType.IDENTIFIER, "a", 4, 0),
                        1,
                        0),
                new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 19, 0)),
                1,
                1),
        new AssignationNode(
                new DeclarationNode(
                        new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 0),
                        new ValueToken(TokenType.IDENTIFIER, "b", 4, 0),
                        1,
                        0),
                new LiteralNode(new ValueToken(TokenType.NUMBER, "3", 19, 0)),
                1,
                1),
        new PrintNode(
                new OperatorNode("+",
                        new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 1)),
                        new VariableNode(new ValueToken(TokenType.IDENTIFIER, "b", 8, 1)),
                        1,
                        1),
                1,
                1)
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
      assertEquals("8.0", output);
    } finally {
      // Restore original System.out
      System.setOut(originalOut);
    }
  }

  @Test
  public void testCreationOfTheSameVariable() {
    // GIVEN
    // let name: string = "a";
    // let name: string = "a";
    List<ASTNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                    new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
                    0,
                    0),
                new LiteralNode(new ValueToken(TokenType.STRING, "a", 19, 0)),
                1,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                    new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
                    1,
                    0),
                new LiteralNode(new ValueToken(TokenType.STRING, "a", 19, 0)),
                1,
                1));

    // WHEN & THEN
    Interpreter interpreter = new Interpreter();
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes));
  }

    @Test
    public void testReassignmentOfVariable() {
        //GIVEN
        // let name: string = "a";
        // name = "b";
        // println(name);
        List<ASTNode> astNodes = List.of(
                new AssignationNode(
                        new DeclarationNode(new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                                new ValueToken(TokenType.IDENTIFIER, "name", 4, 0), 0, 0)
                        , new LiteralNode(new ValueToken(TokenType.STRING, "a", 19, 0)),
                        1, 1
                ), new ReassignmentNode(
                        new VariableNode(new ValueToken(TokenType.IDENTIFIER, "name", 8, 1)),
                        new LiteralNode(new ValueToken(TokenType.STRING, "b", 19, 0)),
                        1, 1
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
            assertEquals("b", output);
        } finally {
            // Restore original System.out
            System.setOut(originalOut);
        }
    }

    @Test
    public void testReassignmentOfVariable2() {
        //GIVEN
        // let name: string = "a";
        // name = "b";
        List<ASTNode> astNodes = List.of(
                new AssignationNode(
                        new DeclarationNode(new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                                new ValueToken(TokenType.IDENTIFIER, "name", 4, 0), 0, 0)
                        , new LiteralNode(new ValueToken(TokenType.STRING, "a", 19, 0)),
                        1,1
                ), new ReassignmentNode(
                        new VariableNode(new ValueToken(TokenType.IDENTIFIER, "name", 8, 1)),
                        new LiteralNode(new ValueToken(TokenType.NUMBER, "8", 19, 0)),
                        1,1
                )
        );

        // WHEN & THEN
        Interpreter interpreter = new Interpreter();
        assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes));
    }
}
