package interpreter;

import static org.junit.jupiter.api.Assertions.*;

import AST.nodes.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import providers.inputProvider.InputProvider;
import providers.inputProvider.TestInputProvider;
import providers.printProvider.TestPrintProvider;
import token.TokenType;
import token.ValueToken;

public class ReadInputTest {
  @Test
  public void testSimpleValidReadEnv() {
    // GIVEN
    // readInput("Test");
    List<ASTNode> astNodes =
        List.of(
            new ReadInputNode(
                new LiteralNode(new ValueToken(TokenType.STRING, "Test", 8, 2)), 1, 1));
    TestInputProvider inputProvider = new TestInputProvider(List.of("Hello"));
    Interpreter interpreter = new Interpreter(inputProvider);
    interpreter.interpret(astNodes.iterator());
    assertFalse(inputProvider.hasInputsToRead());
  }

  @Test
  public void testInvalidTypeReadInput() {
    // GIVEN
    // let a: number = readInput("Ingrese a: "); -> hola -> should fail
    // println(a);

    List<ASTNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                    new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueToken(TokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1),
            new PrintNode(new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)), 2, 1));

    InputProvider inputProvider = new TestInputProvider(List.of("hola"));
    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(inputProvider, printProvider);
    interpreter.interpret(astNodes.iterator());
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testValidSingleReadInput() {
    // GIVEN
    // let a: string = readInput("Ingrese a: "); -> hola
    // println(a); -> hola

    List<ASTNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                    new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                    new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueToken(TokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1),
            new PrintNode(new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)), 2, 1));

    InputProvider inputProvider = new TestInputProvider(List.of("hola"));
    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(inputProvider, printProvider);
    interpreter.interpret(astNodes.iterator());
    assertEquals("hola\n", printProvider.getMessages().next());
  }

  @Test
  public void testValidMultipleReadInput() {
    // GIVEN
    // let a: number = readInput("Ingrese a: "); -> 2
    // let b: number = readInput("Ingrese b: "); -> 2
    // println(a + b); -> 4

    List<ASTNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                    new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueToken(TokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueToken(TokenType.IDENTIFIER, "b", 4, 1),
                    new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueToken(TokenType.STRING, "Ingrese b: ", 8, 2)), 2, 1),
                2,
                1),
            new PrintNode(
                new OperatorNode(
                    "+",
                    new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)),
                    new VariableNode(new ValueToken(TokenType.IDENTIFIER, "b", 8, 2)),
                    2,
                    1),
                2,
                1));

    InputProvider inputProvider = new TestInputProvider(List.of("2", "2"));
    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(inputProvider, printProvider);
    interpreter.interpret(astNodes.iterator());
    assertEquals("4\n", printProvider.getMessages().next());
  }

  @Test
  public void testEmptyInput() {
    // GIVEN
    // let a: number = readInput("Ingrese a: "); -> ""
    // println(a); -> ""

    List<ASTNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                    new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueToken(TokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1),
            new PrintNode(new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)), 2, 1));

    InputProvider inputProvider = new TestInputProvider(List.of(""));
    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(inputProvider, printProvider);
    interpreter.interpret(astNodes.iterator());
    assertEquals("\n", printProvider.getMessages().next());
  }

  @Test
  public void testSpecialCharactersInput() {
    // Given
    // let a: string = readInput("Ingrese a: "); -> "\"hola\""
    // println(a);

    List<ASTNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                    new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                    new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueToken(TokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1),
            new PrintNode(new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)), 2, 1));

    InputProvider inputProvider = new TestInputProvider(List.of("\"hola\""));
    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(inputProvider, printProvider);
    interpreter.interpret(astNodes.iterator());
    assertEquals("\"hola\"\n", printProvider.getMessages().next());
  }

  @Test
  public void testReassignmentWithInput() {
    // Given
    // let a: number = readInput("Ingrese a: "); -> 5
    // a = readInput("Ingrese a: "); -> hola

    List<ASTNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                    new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueToken(TokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1),
            new ReassignmentNode(
                new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)),
                new ReadInputNode(
                    new LiteralNode(new ValueToken(TokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1));

    InputProvider inputProvider = new TestInputProvider(List.of("5", "hola"));
    Interpreter interpreter = new Interpreter(inputProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }
}
